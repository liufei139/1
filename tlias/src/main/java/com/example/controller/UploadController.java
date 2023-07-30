package com.example.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.example.Utils.AliOSSUtils;
import com.example.pojo.Result;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
   /* @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传：{},{},{}",username,age,image);

        *//*
        * 本地存储
        *//*
        *//*
        String originalFilename = image.getOriginalFilename();
        int index=originalFilename.lastIndexOf(".");
        String extname=originalFilename.substring(index);
        String newFilename=UUID.randomUUID().toString()+extname;
        image.transferTo(new File("D:\\Documents\\IDEADocuments\\tlias\\src\\main\\resources\\image\\"+newFilename));
        *//*
        *//*
        阿里云OSS云服务存储
         *//*
        return Result.success();
    }*/
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传:{}",image.getOriginalFilename());
        //调用阿里云OSS工具类
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，url:{}",url);
        return Result.success(url);

    }


}
