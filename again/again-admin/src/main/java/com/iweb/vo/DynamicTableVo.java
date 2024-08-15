package com.iweb.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author：
 * @Package：com.iweb.vo
 * @Project：Test-Java
 * @name：DynamicTableVo
 * @Date：2024/8/11 23:30
 * @Description:
 */
@Data
public class DynamicTableVo {

    @JsonProperty("SID")
    private Integer SID;

    @JsonProperty("SNAME")
    private String SNAME;

    @JsonProperty("SAGE")
    private Integer SAGE;

    @JsonProperty("SSEX")
    private String SSEX;

    @JsonProperty("SNATIVEPLACE")
    private String SNATIVEPLACE;

    @JsonProperty("SMAJOR")
    private String SMAJOR;

    @JsonProperty("SCLASS")
    private String SCLASS;

    @JsonProperty("SNATIVE")
    private String SNATIVE;

    @JsonProperty("CNAME")
    private String CNAME;

    @JsonProperty("CVAL")
    private Integer CVAL;

    @JsonProperty("CTIME")
    private Integer CTIME;

    @JsonProperty("CMARK")
    private Integer CMARK;

    @JsonProperty("TID")
    private Integer TID;

    @JsonProperty("TNAME")
    private String TNAME;

    @JsonProperty("TSEX")
    private String TSEX;

    @JsonProperty("TAGE")
    private Integer TAGE;

    @JsonProperty("TLVL")
    private String TLVL;

    @JsonProperty("EMPNO")
    private Integer EMPNO;

    @JsonProperty("ENAME")
    private String ENAME;

    @JsonProperty("JOB")
    private String JOB;

    @JsonProperty("MGR")
    private Integer MGR;

    @JsonProperty("HIREDATE")
    private String HIREDATE;

    @JsonProperty("ESAL")
    private Double ESAL;

    @JsonProperty("ECOMM")
    private Double ECOMM;

    @JsonProperty("DEPTNO")
    private Integer DEPTNO;

    @JsonProperty("DNAME")
    private String DNAME;

    @JsonProperty("LOC")
    private String LOC;

    @JsonProperty("SAL")
    private Integer SAL;

    @JsonProperty("COMM")
    private Integer COMM;

    @JsonProperty("GRADE")
    private Integer GRADE;
    public Integer getSID() {
        return SID;
    }

    public void setSID(Integer SID) {
        this.SID = SID;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public Integer getSAGE() {
        return SAGE;
    }

    public void setSAGE(Integer SAGE) {
        this.SAGE = SAGE;
    }

    public String getSSEX() {
        return SSEX;
    }

    public void setSSEX(String SSEX) {
        this.SSEX = SSEX;
    }

    public String getSNATIVEPLACE() {
        return SNATIVEPLACE;
    }

    public void setSNATIVEPLACE(String SNATIVEPLACE) {
        this.SNATIVEPLACE = SNATIVEPLACE;
    }

    public String getSMAJOR() {
        return SMAJOR;
    }

    public void setSMAJOR(String SMAJOR) {
        this.SMAJOR = SMAJOR;
    }

    public String getSCLASS() {
        return SCLASS;
    }

    public void setSCLASS(String SCLASS) {
        this.SCLASS = SCLASS;
    }

    public String getSNATIVE() {
        return SNATIVE;
    }

    public void setSNATIVE(String SNATIVE) {
        this.SNATIVE = SNATIVE;
    }

    public String getCNAME() {
        return CNAME;
    }

    public void setCNAME(String CNAME) {
        this.CNAME = CNAME;
    }

    public Integer getCVAL() {
        return CVAL;
    }

    public void setCVAL(Integer CVAL) {
        this.CVAL = CVAL;
    }

    public Integer getCTIME() {
        return CTIME;
    }

    public void setCTIME(Integer CTIME) {
        this.CTIME = CTIME;
    }

    public Integer getCMARK() {
        return CMARK;
    }

    public void setCMARK(Integer CMARK) {
        this.CMARK = CMARK;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public String getTNAME() {
        return TNAME;
    }

    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }

    public String getTSEX() {
        return TSEX;
    }

    public void setTSEX(String TSEX) {
        this.TSEX = TSEX;
    }

    public Integer getTAGE() {
        return TAGE;
    }

    public void setTAGE(Integer TAGE) {
        this.TAGE = TAGE;
    }

    public String getTLVL() {
        return TLVL;
    }

    public void setTLVL(String TLVL) {
        this.TLVL = TLVL;
    }

    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer EMPNO) {
        this.EMPNO = EMPNO;
    }

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getJOB() {
        return JOB;
    }

    public void setJOB(String JOB) {
        this.JOB = JOB;
    }

    public Integer getMGR() {
        return MGR;
    }

    public void setMGR(Integer MGR) {
        this.MGR = MGR;
    }

    public String getHIREDATE() {
        return HIREDATE;
    }

    public void setHIREDATE(String HIREDATE) {
        this.HIREDATE = HIREDATE;
    }

    public Double getESAL() {
        return ESAL;
    }

    public void setESAL(Double ESAL) {
        this.ESAL = ESAL;
    }

    public Double getECOMM() {
        return ECOMM;
    }

    public void setECOMM(Double ECOMM) {
        this.ECOMM = ECOMM;
    }

    public Integer getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(Integer DEPTNO) {
        this.DEPTNO = DEPTNO;
    }

    public String getDNAME() {
        return DNAME;
    }

    public void setDNAME(String DNAME) {
        this.DNAME = DNAME;
    }

    public String getLOC() {
        return LOC;
    }

    public void setLOC(String LOC) {
        this.LOC = LOC;
    }

    public Integer getSAL() {
        return SAL;
    }

    public void setSAL(Integer SAL) {
        this.SAL = SAL;
    }

    public Integer getCOMM() {
        return COMM;
    }

    public void setCOMM(Integer COMM) {
        this.COMM = COMM;
    }

    public Integer getGRADE() {
        return GRADE;
    }

    public void setGRADE(Integer GRADE) {
        this.GRADE = GRADE;
    }
}

