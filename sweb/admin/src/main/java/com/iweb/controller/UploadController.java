package com.iweb.controller;

import com.iweb.config.Result;
import com.iweb.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author 杨芯叶
 * @Create 2024-06-25-8:51
 * @Message
 **/
@Slf4j
@CrossOrigin
@RestController
public class UploadController {


    @PostMapping("/upload")
    public Result upload(@RequestBody MultipartFile file) throws IOException {
        String username = "";
        log.info("文件上传" + username +  file);
        AliOSSUtils aliOSSUtils = new AliOSSUtils();

        String url = aliOSSUtils.upload(file);
        log.info("文件上传成功" + file);
        return Result.success(url);
    }

}
