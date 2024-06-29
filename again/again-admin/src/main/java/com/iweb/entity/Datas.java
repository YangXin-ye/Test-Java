package com.iweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yxy
 * @since 2024-06-29
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Datas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货地址 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 收货地址
     */
    private String address;


}
