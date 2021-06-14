package com.example.spring_file.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @描述 : 文件上传
 * @Author : 梁文辉
 * @Date: 2021-05-01 10:50
 */
@Controller
public class UploadFileController {

    /**
     * 单文件上传
     *
     * @param name 文件名称
     * @param file 上传文件
     * @return
     * @throws IOException
     */
    @PostMapping("file")
    @ResponseBody
    public Object uploadFile(String name, MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (file != null) {
            map.put("name-param", name);
            map.put("file-name", file.getName());
            map.put("Content-Type", file.getContentType());
            map.put("file-Size", file.getSize());
            String FileName = UUID.randomUUID() + "." + file.getContentType().
                    substring(file.getContentType().lastIndexOf("/") + 1);
            String FilePath = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getServletContext().getRealPath("/") + FileName;
            map.put("file-path", FilePath);
            File saveFile = new File(FilePath);
            //文件保存
            file.transferTo(saveFile);
            return map;
        } else {
            return null;
        }
    }

    /**
     * 多文件上传
     *
     * @param name    文件名称
     * @param request
     * @return
     */
    @PostMapping("files")
    @ResponseBody
    public Object upload(String name, HttpServletRequest request) {
        List<String> result = new ArrayList<>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest request1 = (MultipartHttpServletRequest) request;
            List<MultipartFile> files = request1.getFiles("file");
            Iterator<MultipartFile> iterator = files.iterator();
            while (iterator.hasNext()) {
                MultipartFile file = iterator.next();
                try {
                    result.add(this.saveFile(file));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }


    /**
     *  文件下载封装方法
     * @param file
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile file) throws IOException {
        String path = "nothing";
        if (file != null) {
            if (file.getSize() > 0) {
                //创建文件名称
                String FileName = UUID.randomUUID() + "." + file.getContentType().
                        substring(file.getContentType().lastIndexOf("/") + 1);
                //文件路径
                path = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                        .getRequest().getServletContext().getRealPath("/") + FileName;
                File saveFile = new File(path);
                //文件保存
                file.transferTo(saveFile);
            }
        }
        return path;
    }
}
