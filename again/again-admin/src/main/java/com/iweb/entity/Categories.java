package com.iweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类表 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品表 id
     */
    private Integer pId;

    /**
     * 分类名称
     */
    private String categoryname;

    /**
     * 所属季节
     */
    private String season;


}
