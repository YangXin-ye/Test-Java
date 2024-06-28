package com.iweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author 杨芯叶
 * @Create 2024-06-25-10:53
 * @Message
 **/
@Data
@Component
public class AliOss {

    private String endpoint = "https://oss-cn-nanjing.aliyuncs.com";
    private String accessKeyId = "LTAI5tKsJtiRmt7F1sFBLdiV";
    private String accessKeySecret = "BIQsoZUAdwPeYBhv7bTOOJkxNOUhVw";
    private String bucketName = "yxy-study";


}
