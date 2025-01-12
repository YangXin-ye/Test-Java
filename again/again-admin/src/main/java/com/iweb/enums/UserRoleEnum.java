package com.iweb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举
 * @author zhangguohao
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {

  USER(1,"普通用户"),
  SUPER_ADMIN(2,"超级管理员"),
  ADMIN(3,"管理员");
  private Integer roleCode;

  private String remark;

  public static UserRoleEnum getByCode(Integer roleCode) {
    for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
      if (roleEnum.getRoleCode().equals(roleCode)) {
        return roleEnum;
      }
    }
    return null;
  }

}
