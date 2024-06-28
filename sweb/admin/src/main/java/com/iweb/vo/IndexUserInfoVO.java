package com.iweb.vo;

import lombok.Data;

import java.util.Date;

/**
 * 首页用户数据
 *
 * @Author 杨芯叶
 * @Create 2024-06-07-14:01
 * @Message
 **/
@Data
public class IndexUserInfoVO {

    private Integer uId;
    private String username;
    private String avatar;
    private String role;
    private Date time;
    private String address;

}
