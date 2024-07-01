package com.iweb.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxy
 * @since 2024-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
     * 商品分类
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 新增时选择商品分类
     */
    @TableField(exist = false)
    private List<String> categoryNames;


}
