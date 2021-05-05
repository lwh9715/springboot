import requests
import sys
import re
import io
import urllib
from bs4 import BeautifulSoup
sys.stderr = io.TextIOWrapper(sys.stderr.buffer, encoding='utf-8')
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8')
class Result:
    isFailed=False
    #商店名称
    shop_name=""
    #URL地址
    shop_url=""
    #商业注册号
    shop_phone=""
    #业务类型
    shop_yw=""
    #增值税
    shop_name2=""
    #地址
    shop_position=""
    def __init__(self,shop_name:str,shop_url:str,shop_phone:str,shop_yw:str,shop_name2:str,shop_position:str,isFailed=False):
        self.shop_name=shop_name.replace("&amp;","&")
        self.shop_url=shop_url.replace("&amp;","&")
        self.shop_name2=shop_name2.replace("&amp;","&")
        self.shop_position=shop_position.replace("&amp;","&")
        self.shop_yw=shop_yw.replace("&amp;","&")
        self.shop_phone=shop_phone.replace("&amp;","&")
        self.isFailed=isFailed
    def print(self):
        if(self.isFailed):
            print("无,法,获,取,信,息==")
            return
        print(f"{[self.shop_url,self.shop_name,self.shop_yw,self.shop_phone,self.shop_name2,self.shop_position]}==")
        #print(f"商店名称：{self.shop_name}")
        #print(f"业务类型：{self.shop_yw}")
        #print(f"商业注册号：{self.shop_phone}")
        #print(f"增值税：{self.shop_name2}")
        #print(f"地址：{self.shop_position}")
############################################
def progressBar(incount:int) -> str:
    incount=int(incount)
    s                     ="[          ]"
    if(0<=incount<=10):  s="[-         ]"
    elif(10<incount<=20):s="[--        ]"
    elif(20<incount<=30):s='[---       ]'
    elif(30<incount<=40):s='[----      ]'
    elif(40<incount<=50):s='[-----     ]'
    elif(50<incount<=60):s='[------    ]'
    elif(60<incount<=70):s='[-------   ]'
    elif(70<incount<=80):s='[--------  ]'
    elif(80<incount<=90):s='[--------- ]'
    elif(90<incount<=100):s='[----------]'
    return f"{s} {incount}%"
############################################
class main:
    result=[]
    price=""
    isRunFinish=True
    failedMessage=""
    session=None
    _header={"User-Agent":"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
             "Cookie":'cookie: session-id=258-9231329-5178648; ubid-acbde=262-7265214-3934410; lc-acbde=de_DE; x-acbde="E3WFP8NSDsYvg250frU8V1nbvXtPG2fbur7ZqJCavVzTSP3AU?lmrFFsjlDtnhz6"; at-acbde=Atza|IwEBIJv6EAE35G3aIcpg5Kkbyxq6eadDPbu2ppwNK3w6CEu4iU9zRwSDmtov2qyEDZmLqEkVKKr4FixuHFy8K092Y0aeO5c7pIore7krorMf75lbwy1JrObzRFiK8CO3nQzKlgewGLylA27IuyTJk4NZ_WTDX5iyWYi48gDzuZiS-Z6C-GQrAXbv3Lkb3pHgiaDPS9NqfPNyFzuRrvJnDtSSfEo1n-ifZ1Yq1JM7GsL7UOU-WA; sess-at-acbde="cynoAK9xDL2yjByYtPW7H+3F/J4ud/zcrKoE4Lk7Yko="; sst-acbde=Sst1|PQF7vPQbiteBax6u7i1bE9ijCYOUwSVqZUFbR7E0aOj-ycv_UEMHM7_hrbejW41dcGg_ZYsEJI-94HAzFiPCGPPGYr8XJQfgBYHlROihXn4CUHN96P9z0A-MnHL15cwEGlD7QGfCVkAXwfzQBM3QZaHhyCD1GSWoBg5Nl3L3w9nP1mbKKgyxtHeAuT2dUHIlyzz8Qn46f3lx716SrMktilNs2Kb1yjTC2Okxc8Tibv1ht4i5X7H3Hfcj5tRF878C2wxekA_gLUpDOyopA3hkeZWBMlK5LGr35RriXC5wE4J0FHg; i18n-prefs=EUR; s_nr=1607091332850-New; s_dslv=1607091332851; s_vnum=2039091332850%26vn%3D1; session-token="9eQbNOI69IKHWR26e0RRmlt4AfwLgz5YEQBkJaNnxALwJmsvVvl+gVVajLupucCk5euHHqDDE2mwwIxXkmCa1ky7TIwuEXdqFO2MYvd1Kk7NsD3pRX1UkpliA5g9In9+e22teWqo7M9F+b3Fz2UebqdsjseDre4J58OrWVL5rF+2/pjrlUTNfg6mF9q4mARLt8kFMQF8pj+4+SkLu71aIw=="; session-id-time=2082754801l; csm-hit=tb:F3KAY3QHDC1JBZF1F04F+s-F3KAY3QHDC1JBZF1F04F|1607485952570&t:1607485952570&adb:adblk_no'}
    def __init__(self,arg:list,t:bool):
        self.session=requests.Session()
        # 本地访问
        #self.load_shop("E:\\test.html",True)
        # 联机访问
        #self.load_shop("https://www.amazon.de/sp?_encoding=UTF8&asin=&isAmazonFulfilled=1&isCBA=&marketplaceID=A1PA6795UKMFR9&orderID=&protocol=current&seller=A2TXBA4ZE0TMVP",1,1)

        if t:r=self.load_home()
        else:r=self.search(arg[1])
        if r[0]=="没有\"已宣布优惠\"的物品":
            self.isRunFinish=False
            self.failedMessage=r[0]
            return
        for i in range(len(r)):
            self.load(r[i],len(r)+1,i+1)
    def load_home(self) -> list:
        url="https://www.amazon.de"
        print(f"进度：{progressBar(10)} # info: {url}")
        text=requests.get(url,timeout=(40,40),headers=self._header).text
        print(f"进度：{progressBar(15)} # info: Loading")
        with open("E:\\home.html","w+",encoding="UTF-8") as f:f.write(text)
        #with open("E:\\home.html",encoding="UTF-8") as f:text=f.read()
        b=BeautifulSoup(text,features="html.parser")
        try:
            lis=b.find("div",attrs={"class":"a-section a-spacing-none shogun-widget deals-shoveler fresh-shoveler"}).find_all("span",attrs={"class":"a-list-item"})
        except AttributeError:
            return ["没有\"已宣布优惠\"的物品"]
        returnlist=[]
        print(f"进度：{progressBar(20)} # info: load home {len(lis)} items")
        for i in range(len(lis)):
            if(i<=20):print(f"进度：{progressBar(20+(20/len(lis))*(i+1))} # info: try load({i+1}/{len(lis)})")
            returnlist.append(f'https://www.amazon.de{lis[i].find("a")["href"]}')
        return returnlist
    ############################################
    def search(self, s:str) -> list:
        url=f"https://www.amazon.de/s?k={urllib.parse.quote(s)}&ref=nb_sb_noss"
        #print(f"进度：{progressBar(10)} # info: {url}")
        head=self._header.copy()

        # 本地访问
        #with open("E:\\aa.html",encoding="UTF-8") as f:t=f.read()
        # 在线访问
        t=self.session.get(url,timeout=(40,40),headers=head).text

        b=BeautifulSoup(t,features="html.parser")

        with open("E:\\aa.html","w+",encoding="UTF-8") as f:f.write(t)
        r=b.find_all("div",attrs={"data-component-type":"s-search-result"})
        #print(f"进度：{progressBar(20)} # info: load {len(r)} items")
        o=[]
        try:
            for ii in range(len(r)):
                #print(f"进度：{progressBar(20+(20/len(r))*(ii+1))} # info: try load({ii+1}/{len(r)})")
                o.append("https://www.amazon.de"+(r[ii].find("h2").a["href"]))
        except:
            try:
                imageurl=b.find("div",attrs={"class":"a-row a-spacing-large"}).find("img")["src"]
            except BaseException as e:
                raise e
            else:
                print("\n需要验证码")
                print(f"图片地址：{imageurl}")
            check=input("请根据图片输入验证码：")
            f=b.find("form")
            print(f==None)
            i=f.find_all("input",attrs={"type":"hidden"})
            url=f"https://www.amazon.de/{f['action']}?amzn={i[0]['value']}&amzn-r={i[1]['value']}&field-keywords={check}"
            print(url)
            ttt=self.session.get(url,headers=head,timeout=(40,40)).text
            r=BeautifulSoup(ttt,features="html.parser").find_all("div",attrs={"data-component-type":"s-search-result"})
            with open("E:\\aa.html","w+",encoding="UTF-8") as f:f.write(b)
            for ii in range(i):
                o.append("https://www.amazon.de"+(r[ii].find("h2",attrs={"class":"a-size-mini a-spacing-none a-color-base s-line-clamp-4"}).a["href"]))
        return o
    ############################################
    def load(self,s:str,tryint:int,trycount:int):
        if(s==None):return
        #print(f"进度：{progressBar(40+(60/tryint)*trycount)} # info: load commodity({trycount}/{tryint})->{s}")
        b=BeautifulSoup(self.session.get(s,headers=self._header,timeout=(40,40)).text,features="html.parser")
        with open("E:\\aaa.html","w+",encoding="UTF-8") as f:f.write(str(b))
        try:
            shop=b.find("div",attrs={"class":"a-section a-spacing-mini","id":"merchant-info"}).find("a",attrs={"id":"sellerProfileTriggerId"})
            self.shop_url="https://www.amazon.de"+shop["href"]
        except TypeError:
            self.shop_url="Failed"
        except AttributeError:
            self.shop_url="Failed"
        self.load_shop(self.shop_url,tryint,trycount)
    ############################################
    def load_shop(self,url:str,tryint:int,trycount:int,isLocal=False):
        if url=="Failed":
            self.result.append(Result("","","","","","",True))
            #print(f"进度：{progressBar(40+((60/tryint)*(trycount)))} # info:load shop({trycount}/{tryint})->Failed get the shop url")
        else:
            #print(f"进度：{progressBar(40+((60/tryint)*(trycount)))} # info:load shop({trycount}/{tryint})->{url}")
            if isLocal:
                with open(url,encoding="UTF-8") as f:b=BeautifulSoup(f.read(),features="html.parser")
            else: b=BeautifulSoup(self.session.get(url,timeout=(40,40),headers=self._header).text,features="html.parser")
            #with open("E:\\aaaa.html","w+",encoding="UTF-8") as f:f.write(str(b))
            t=b.find_all("ul",attrs={"class":"a-unordered-list a-nostyle a-vertical"})[0]
            l=t.find_all("li")
            #---------
            try:
                shop_name=l[0].span.span.string+str(l[0].span).split("</span>")[1]
            except:
                shop_name="No Match"
            #---------
            try:
                shop_yw=l[1].span.span.string+str(l[1].span).split("</span>")[1]
            except:
                shop_yw="No Match"
            #---------
            try:
                shop_phone=l[2].span.span.string+str(l[2].span).split("</span>")[1]
            except:
                shop_phone="No Match"
            #---------
            try:
                shop_name2=l[3].span.span.string+str(l[3].span).split("</span>")[1]
            except:
                shop_name2="No Match"
            #---------
            try:
                t=l[4].span.span.string+"\n"
                for i in l[4].span.ul.find_all("li"):
                    t+=i.span.string+"\n"
                shop_position=t[:-1]
            except:
                shop_position="No Match"
            #---------
            self.result.append(Result(shop_name,url,shop_phone,shop_yw,shop_name2,shop_position))
############################################
if __name__ == "__main__":
    #inp=input("首页还是搜索？回复(搜索关键词开始搜索，回复1或“首页”搜索首页)->")
    inp=sys.argv
    tryFailed=0
    if(len(inp)==1):t=True
    elif(inp[1]=="1"):t=True
    elif(inp[1]=="首页"):t=True
    else:t=False
    o=main(inp,t)
    if o.isRunFinish==False:
        print(o.failedMessage)
    else:
        try:
            #print("\n")
            for i in range(len(o.result)):
                if o.result[i].isFailed:
                    tryFailed+=1
                    continue
                #print(f"\n# 第{i+1}个结果：")
                o.result[i].print()
        except BaseException as e:
            print(e)
