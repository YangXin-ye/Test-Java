package com.iweb.entity;

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
public class CategoryTempalte implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 模板表id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String categoryname;

    /**
     * 所属季节
     */
    private String season;


}
