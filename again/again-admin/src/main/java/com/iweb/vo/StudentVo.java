package com.iweb.vo;

import lombok.Data;

/**
 * @Author：
 * @Package：com.iweb.vo
 * @Project：Test-Java
 * @name：StudentVo
 * @Date：2024/8/10 23:10
 * @Description:
 */
public class StudentVo {

    private Integer sId;

    private String sName;

    private Integer sAge;

    private String sSex;

    private String sNativePlace;

    private String sMajor;

    private String sClass;

    private String sNative;

    private String cName;

    private Integer cVal;

    private Integer cTime;

    private Integer cMark;

    private Integer tId;

    private String tName;

    private String tSex;

    private Integer tAge;

    private String tLvl;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getsAge() {
        return sAge;
    }

    public void setsAge(Integer sAge) {
        this.sAge = sAge;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    public String getsNativePlace() {
        return sNativePlace;
    }

    public void setsNativePlace(String sNativePlace) {
        this.sNativePlace = sNativePlace;
    }

    public String getsMajor() {
        return sMajor;
    }

    public void setsMajor(String sMajor) {
        this.sMajor = sMajor;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getsNative() {
        return sNative;
    }

    public void setsNative(String sNative) {
        this.sNative = sNative;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getcVal() {
        return cVal;
    }

    public void setcVal(Integer cVal) {
        this.cVal = cVal;
    }

    public Integer getcTime() {
        return cTime;
    }

    public void setcTime(Integer cTime) {
        this.cTime = cTime;
    }

    public Integer getcMark() {
        return cMark;
    }

    public void setcMark(Integer cMark) {
        this.cMark = cMark;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public Integer gettAge() {
        return tAge;
    }

    public void settAge(Integer tAge) {
        this.tAge = tAge;
    }

    public String gettLvl() {
        return tLvl;
    }

    public void settLvl(String tLvl) {
        this.tLvl = tLvl;
    }
}
