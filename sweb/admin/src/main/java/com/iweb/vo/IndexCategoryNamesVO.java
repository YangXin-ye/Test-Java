package com.iweb.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author 杨芯叶
 * @Create 2024-06-13-16:43
 * @Message
 **/
@Data
public class IndexCategoryNamesVO {

    /**
     * 商品 id
     */
    private Integer id;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品评分
     */
    private BigDecimal rating;

    /**
     * 商品收藏量
     */
    private Integer favorites;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品销量
     */
    private Integer sales;

    /**
     * 商品创建日期
     */
    private LocalDateTime createTime;

    /**
     * 商品库存
     */
    private Integer inventory;

    /**
     * 商品分类名称
     */

    private String categoryname;
}
