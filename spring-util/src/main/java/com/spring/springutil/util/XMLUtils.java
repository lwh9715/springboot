package com.spring.springutil.util;

import com.spring.springutil.entity.NidInfo;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * [功能简述]： scp系统xml导出定制工具类
 *
 * @Author 梁文辉
 * @Date 2021/4/13 19:08
 * @Version 1.0
 */
public class XMLUtils {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        NidInfo nicInfo = new NidInfo();
        createXml(nicInfo);
        System.out.println("运行时间：" + (System.currentTimeMillis() - start));
    }


    /**
     * 生成xml方法
     */
    public static String createXml(NidInfo nidInfo) {

        try {
            // 1、生成一个根节点
            Element NidInfo = new Element("NidInfo");
            // 2、为节点添加属性
            //NidInfo.setAttribute("version", "2.0");
            // 3、生成一个document对象
            Document document = new Document(NidInfo);

            Element account = new Element("account");
            account.setText(nidInfo.getAccount());
            NidInfo.addContent(account);
            Element endEffectiveDate = new Element("endEffectiveDate");
            endEffectiveDate.setText(nidInfo.getEndEffectiveDate());
            NidInfo.addContent(endEffectiveDate);
            Element fileType = new Element("fileType");
            fileType.setText(nidInfo.getFileType());
            NidInfo.addContent(fileType);
            Element imageType = new Element("imageType");
            imageType.setText(nidInfo.getImageType());
            NidInfo.addContent(imageType);
            Element imageGroup = new Element("imageGroup");
            imageGroup.setText(nidInfo.getImageGroup());
            NidInfo.addContent(imageGroup);
            Element fileName = new Element("fileName");
            fileName.setText(nidInfo.getFileName());
            NidInfo.addContent(fileName);
            Element nidNbr = new Element("nidNbr");
            nidNbr.setText(nidInfo.getNidNbr());
            NidInfo.addContent(nidNbr);
            Element authorizedLetterSignedDate = new Element("authorizedLetterSignedDate");
            authorizedLetterSignedDate.setText(nidInfo.getAuthorizedLetterSignedDate());
            NidInfo.addContent(authorizedLetterSignedDate);
            Element dataSource = new Element("dataSource");
            dataSource.setText(nidInfo.getDataSource());
            NidInfo.addContent(dataSource);
            Element imageInfo = new Element("imageInfo");
            imageInfo.setText(nidInfo.getImageInfo());
            NidInfo.addContent(imageInfo);

            Format format = Format.getCompactFormat();
            // 设置换行Tab或空格
            format.setIndent("	");
            format.setEncoding("UTF-8");

            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String nowdate = simpleDateFormat.format(date);

            // 4、创建XMLOutputter的对象
            XMLOutputter outputer = new XMLOutputter(format);
            String xmlName = nidInfo.getAccount() + "-" + nidInfo.getFileType() + "-"
                    + nidInfo.getImageType() + nidInfo.getImageGroup() + "-" + nowdate;
            // 5、利用outputer将document转换成xml文档
            File file = new File("C:\\Users\\Administrator.USER-20190110FQ\\Desktop\\xml\\"
                    + xmlName + ".xml");
            outputer.output(document, new FileOutputStream(file));
            return file.toString();
        } catch (Exception e) {
            System.out.println("生成rssNew.xml失败");
            return "2";
        }
    }

}
