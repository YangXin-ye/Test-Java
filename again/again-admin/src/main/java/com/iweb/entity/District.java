package com.iweb.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 省份区域信息表
 * </p>
 *
 * @author yxy
 * @since 2024-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class District implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 上级行政区id
     */
    private String parentId;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 区域编码
     */
    private String adcode;

    /**
     * 行政区名称
     */
    private String name;

    /**
     * 行政区边界坐标点
     */
    private String polyline;

    /**
     * 区域中心点
     */
    private String center;

    /**
     * 行政区划级别
     * country:国家
     * province:省份（直辖市会在province和city显示）
     * city:市（直辖市会在province和city显示）
     * district:区县
     * street:街道
     */
    private String level;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Boolean deleted;

}
