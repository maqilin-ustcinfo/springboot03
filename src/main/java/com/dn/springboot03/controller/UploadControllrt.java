package com.dn.springboot03.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/file")
public class UploadControllrt {

    private static final Logger logger = LoggerFactory.getLogger(UploadControllrt.class);

    /**
     * 进入文件下载页面
     * @return
     */
    @RequestMapping("/gotoUpload")
    public String gotoUpload(){
        logger.info("gotoUpload");
        return "uploadFile";
    }

    /**
     * 文件上传
     * @param request
     * @param mul
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request,
                           MultipartHttpServletRequest mul){
        // 全文件路径
        String filePath = mul.getFile("file1").getOriginalFilename();
        // 文件名
        String uploadFileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
        logger.info("文件名为{}",uploadFileName);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = (FileInputStream)mul.getFile("file1").getInputStream();
            fos = new FileOutputStream("D:/cf/"+uploadFileName);
            int temp = 0;
            while ((temp = fis.read())!=-1){
                fos.write(temp);
            }
            fos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "redirect:/file/gotoUpload";
    }

    @RequestMapping("/download")
    public void download(HttpServletResponse response){
        logger.info("download===========");
        String fileName = "info.log";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            bis = new BufferedInputStream(new FileInputStream("D:/cf/"+fileName));
            os = response.getOutputStream();
            int length = bis.read(buff);
            while (length!=-1){
                os.write(buff,0,buff.length);
                //os.flush();
                length = bis.read(buff);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bis!=null){
                try {
                    bis.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //return "redirect:/file/gotoUpload";
    }

}
