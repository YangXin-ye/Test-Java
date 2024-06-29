package com.iweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class AddProductsDTO extends Products implements Serializable {

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 新增分类名称
     */
    private String newCategoryName;
}
