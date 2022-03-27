package com.duoduo.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.UUID;

public class FileUtil {
    //文件上传
    public static String uploadFile(HttpServletRequest request, MultipartFile prodFile){
        //获取当前项目得绝对路径
        HttpSession session = request.getSession();
        String basePath = session.getServletContext().getRealPath("/");
        File target = new File("/uploads");
        //创建服务器上文件上传的文件夹
        File destFolder = new File(target, DateUtil.getRiqi());

        //获取文件原名
        String fileOriginalName = prodFile.getOriginalFilename();
        //获取上传的文件名后缀
        String suffix = fileOriginalName.substring(fileOriginalName.lastIndexOf("."));
        //用UUID生成一个随机字符串作为上传到服务器的文件名
        String uploadFileName = UUID.randomUUID().toString()+suffix;
        //文件从uploads开始的路径（要保存到数据库的路径）
        File destFile = new File(destFolder,uploadFileName);
        //生成所有文件夹
        File fileUploadPath=new File(basePath+destFile.getPath());
        if(!fileUploadPath.getParentFile().exists()){
            fileUploadPath.getParentFile().mkdirs();
        }

        DigestInputStream dis=null;
        FileOutputStream fos=null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //创建DigestInputStream对象
            dis = new DigestInputStream(prodFile.getInputStream(), md);
            //创建输入流对象
            fos = new FileOutputStream(fileUploadPath);
            //上传文件
            byte[] bys = new byte[1024];
            int len = 0;
            while((len=dis.read(bys))>0){
                fos.write(bys, 0, len);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(fos!=null)try {fos.close();} catch (Exception e) {e.printStackTrace();}

            if(dis!=null)try {dis.close();} catch (Exception e) {e.printStackTrace();}

        }

        //获取文件上传成功在服务器上的相对路径
        String filePath = destFile.getPath();

        return filePath;

    }

    public static String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes(StandardCharsets.ISO_8859_1);
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
}
