package com.iweb.service.impl;

import cn.hutool.core.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iweb.mapper.DynamicTableMapper;
import com.iweb.service.DistrictService;
import com.iweb.service.DynamicTableService;
import com.iweb.vo.DynamicTableVo;
import com.iweb.vo.EmpVo;
import com.iweb.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：
 * @Package：com.iweb.service.impl
 * @Project：Test-Java
 * @name：DynamicTableServiceImpl
 * @Date：2024/8/10 23:04
 * @Description:
 */
@Service
public class DynamicTableServiceImpl implements DynamicTableService {


    @Autowired
    private DynamicTableMapper dynamicTableMapper;


    @Override
    public Map<String, Object> list(Integer pageNum,Integer pageSize,String searchKey) {
        Map<String, Object> dataResult = new HashMap<>();
        // 定义表格的列名
        List<Map<String, String>> columns = getColumns();
        int offset = (pageNum - 1) * pageSize;
        List<DynamicTableVo> dynamicTableVos = dynamicTableMapper.getData(pageSize,offset,searchKey);
        Integer count = dynamicTableMapper.getCount(searchKey);
        dataResult.put("columns", columns);
        dataResult.put("data",dynamicTableVos);
        dataResult.put("total",count);
        return dataResult;

    }


    private List<Map<String, String>> getColumns(){
        List<Map<String, String>> columns = new ArrayList<>();

        Map<String, String> sIdColumn = new HashMap<>();
        sIdColumn.put("label", "学生Id");
        sIdColumn.put("prop", "SID");
        columns.add(sIdColumn);

        Map<String, String> sNameColumn = new HashMap<>();
        sNameColumn.put("label", "学生名称");
        sNameColumn.put("prop", "SNAME");
        columns.add(sNameColumn);

        Map<String, String> sAgeColumn = new HashMap<>();
        sAgeColumn.put("label", "学生年龄");
        sAgeColumn.put("prop", "SAGE");
        columns.add(sAgeColumn);

        Map<String, String> sSexColumn = new HashMap<>();
        sSexColumn.put("label", "学生性别");
        sSexColumn.put("prop", "SSEX");
        columns.add(sSexColumn);

        Map<String, String> sNativePlaceColumn = new HashMap<>();
        sNativePlaceColumn.put("label", "学生地址");
        sNativePlaceColumn.put("prop", "SNATIVEPLACE");
        columns.add(sNativePlaceColumn);

        Map<String, String> sMajorColumn = new HashMap<>();
        sMajorColumn.put("label", "学生专业");
        sMajorColumn.put("prop", "SMAJOR");
        columns.add(sMajorColumn);

        Map<String, String> sClassColumn = new HashMap<>();
        sClassColumn.put("label", "学生班级");
        sClassColumn.put("prop", "SCLASS");
        columns.add(sClassColumn);

        Map<String, String> sNativeColumn = new HashMap<>();
        sNativeColumn.put("label", "学生族");
        sNativeColumn.put("prop", "SNATIVE");
        columns.add(sNativeColumn);

        Map<String, String> cNameColumn = new HashMap<>();
        cNameColumn.put("label", "考试科目");
        cNameColumn.put("prop", "CNAME");
        columns.add(cNameColumn);

        Map<String, String> cValColumn = new HashMap<>();
        cValColumn.put("label", "科目学分");
        cValColumn.put("prop", "CVAL");
        columns.add(cValColumn);

        Map<String, String> cTimeColumn = new HashMap<>();
        cTimeColumn.put("label", "课时");
        cTimeColumn.put("prop", "CTIME");
        columns.add(cTimeColumn);

        Map<String, String> cMarkColumn = new HashMap<>();
        cMarkColumn.put("label", "成绩");
        cMarkColumn.put("prop", "CMARK");
        columns.add(cMarkColumn);

        Map<String, String> tIdColumn = new HashMap<>();
        tIdColumn.put("label", "教师Id");
        tIdColumn.put("prop", "TID");
        columns.add(tIdColumn);

        Map<String, String> tNameColumn = new HashMap<>();
        tNameColumn.put("label", "教师名称");
        tNameColumn.put("prop", "TNAME");
        columns.add(tNameColumn);

        Map<String, String> tSexColumn = new HashMap<>();
        tSexColumn.put("label", "教师性别");
        tSexColumn.put("prop", "TSEX");
        columns.add(tSexColumn);

        Map<String, String> tAgeColumn = new HashMap<>();
        tAgeColumn.put("label", "教师年龄");
        tAgeColumn.put("prop", "TAGE");
        columns.add(tAgeColumn);

        Map<String, String> tLvlColumn = new HashMap<>();
        tLvlColumn.put("label", "教师级别");
        tLvlColumn.put("prop", "TLVL");
        columns.add(tLvlColumn);



        Map<String, String> empNoColumn = new HashMap<>();
        empNoColumn.put("label", "员工编号");
        empNoColumn.put("prop", "EMPNO");
        columns.add(empNoColumn);

        Map<String, String> empNameColumn = new HashMap<>();
        empNameColumn.put("label", "员工姓名");
        empNameColumn.put("prop", "ENAME");
        columns.add(empNameColumn);

        Map<String, String> jobColumn = new HashMap<>();
        jobColumn.put("label", "员工职位");
        jobColumn.put("prop", "JOB");
        columns.add(jobColumn);

        Map<String, String> mgrColumn = new HashMap<>();
        mgrColumn.put("label", "经理编号");
        mgrColumn.put("prop", "MGR");
        columns.add(mgrColumn);

        Map<String, String> hireDateColumn = new HashMap<>();
        hireDateColumn.put("label", "入职日期");
        hireDateColumn.put("prop", "HIREDATE");
        columns.add(hireDateColumn);

        Map<String, String> salColumn = new HashMap<>();
        salColumn.put("label", "工资");
        salColumn.put("prop", "ESAL");
        columns.add(salColumn);

        Map<String, String> commColumn = new HashMap<>();
        commColumn.put("label", "佣金");
        commColumn.put("prop", "ECOMM");
        columns.add(commColumn);

        Map<String, String> deptNoColumn = new HashMap<>();
        deptNoColumn.put("label", "部门编号");
        deptNoColumn.put("prop", "DEPTNO");
        columns.add(deptNoColumn);

        Map<String, String> dNameColumn = new HashMap<>();
        dNameColumn.put("label", "部门名称");
        dNameColumn.put("prop", "DNAME");
        columns.add(dNameColumn);

        Map<String, String> locColumn = new HashMap<>();
        locColumn.put("label", "部门位置");
        locColumn.put("prop", "LOC");
        columns.add(locColumn);


        Map<String, String> bonusSalColumn = new HashMap<>();
        bonusSalColumn.put("label", "奖金金额");
        bonusSalColumn.put("prop", "SAL");
        columns.add(bonusSalColumn);

        Map<String, String> bonusCommColumn = new HashMap<>();
        bonusCommColumn.put("label", "奖金佣金");
        bonusCommColumn.put("prop", "COMM");
        columns.add(bonusCommColumn);

        Map<String, String> gradeColumn = new HashMap<>();
        gradeColumn.put("label", "工资等级");
        gradeColumn.put("prop", "GRADE");
        columns.add(gradeColumn);

        return columns;

    }
    /**
     * 获取学生相关组合数据
     * @return
     */
    private Map<String,Object> getStudentData(Integer pageNum,Integer pageSize){
        Map<String, Object> studentData = new HashMap<>();
        // 定义表格的列名
        List<Map<String, String>> columns = new ArrayList<>();

        Map<String, String> sIdColumn = new HashMap<>();
        sIdColumn.put("label", "学生Id");
        sIdColumn.put("prop", "sId");
        columns.add(sIdColumn);

        Map<String, String> sNameColumn = new HashMap<>();
        sNameColumn.put("label", "学生名称");
        sNameColumn.put("prop", "sName");
        columns.add(sNameColumn);

        Map<String, String> sAgeColumn = new HashMap<>();
        sAgeColumn.put("label", "学生年龄");
        sAgeColumn.put("prop", "sAge");
        columns.add(sAgeColumn);

        Map<String, String> sSexColumn = new HashMap<>();
        sSexColumn.put("label", "学生性别");
        sSexColumn.put("prop", "sSex");
        columns.add(sSexColumn);

        Map<String, String> sNativePlaceColumn = new HashMap<>();
        sNativePlaceColumn.put("label", "学生地址");
        sNativePlaceColumn.put("prop", "sNativePlace");
        columns.add(sNativePlaceColumn);

        Map<String, String> sMajorColumn = new HashMap<>();
        sMajorColumn.put("label", "学生专业");
        sMajorColumn.put("prop", "sMajor");
        columns.add(sMajorColumn);

        Map<String, String> sClassColumn = new HashMap<>();
        sClassColumn.put("label", "学生班级");
        sClassColumn.put("prop", "sClass");
        columns.add(sClassColumn);

        Map<String, String> sNativeColumn = new HashMap<>();
        sNativeColumn.put("label", "学生族");
        sNativeColumn.put("prop", "sNative");
        columns.add(sNativeColumn);

        Map<String, String> cNameColumn = new HashMap<>();
        cNameColumn.put("label", "考试科目");
        cNameColumn.put("prop", "cName");
        columns.add(cNameColumn);

        Map<String, String> cValColumn = new HashMap<>();
        cValColumn.put("label", "科目学分");
        cValColumn.put("prop", "cVal");
        columns.add(cValColumn);

        Map<String, String> cTimeColumn = new HashMap<>();
        cTimeColumn.put("label", "课时");
        cTimeColumn.put("prop", "cTime");
        columns.add(cTimeColumn);

        Map<String, String> cMarkColumn = new HashMap<>();
        cMarkColumn.put("label", "成绩");
        cMarkColumn.put("prop", "cMark");
        columns.add(cMarkColumn);

        Map<String, String> tIdColumn = new HashMap<>();
        tIdColumn.put("label", "教师Id");
        tIdColumn.put("prop", "tId");
        columns.add(tIdColumn);

        Map<String, String> tNameColumn = new HashMap<>();
        tNameColumn.put("label", "教师名称");
        tNameColumn.put("prop", "tName");
        columns.add(tNameColumn);

        Map<String, String> tSexColumn = new HashMap<>();
        tSexColumn.put("label", "教师性别");
        tSexColumn.put("prop", "tSex");
        columns.add(tSexColumn);

        Map<String, String> tAgeColumn = new HashMap<>();
        tAgeColumn.put("label", "教师年龄");
        tAgeColumn.put("prop", "tAge");
        columns.add(tAgeColumn);

        Map<String, String> tLvlColumn = new HashMap<>();
        tLvlColumn.put("label", "教师级别");
        tLvlColumn.put("prop", "tLvl");
        columns.add(tLvlColumn);

        Map<String, String> empNoColumn = new HashMap<>();
        empNoColumn.put("label", "员工编号");
        empNoColumn.put("prop", "empNo");
        columns.add(empNoColumn);

        Map<String, String> empNameColumn = new HashMap<>();
        empNameColumn.put("label", "员工姓名");
        empNameColumn.put("prop", "eName");
        columns.add(empNameColumn);

        Map<String, String> jobColumn = new HashMap<>();
        jobColumn.put("label", "员工职位");
        jobColumn.put("prop", "job");
        columns.add(jobColumn);

        Map<String, String> mgrColumn = new HashMap<>();
        mgrColumn.put("label", "经理编号");
        mgrColumn.put("prop", "mgr");
        columns.add(mgrColumn);

        Map<String, String> hireDateColumn = new HashMap<>();
        hireDateColumn.put("label", "入职日期");
        hireDateColumn.put("prop", "hireDate");
        columns.add(hireDateColumn);

        Map<String, String> salColumn = new HashMap<>();
        salColumn.put("label", "工资");
        salColumn.put("prop", "sal");
        columns.add(salColumn);

        Map<String, String> commColumn = new HashMap<>();
        commColumn.put("label", "佣金");
        commColumn.put("prop", "comm");
        columns.add(commColumn);

        Map<String, String> deptNoColumn = new HashMap<>();
        deptNoColumn.put("label", "部门编号");
        deptNoColumn.put("prop", "deptNo");
        columns.add(deptNoColumn);

        Map<String, String> dNameColumn = new HashMap<>();
        dNameColumn.put("label", "部门名称");
        dNameColumn.put("prop", "dName");
        columns.add(dNameColumn);

        Map<String, String> locColumn = new HashMap<>();
        locColumn.put("label", "部门位置");
        locColumn.put("prop", "loc");
        columns.add(locColumn);


        Map<String, String> bonusSalColumn = new HashMap<>();
        bonusSalColumn.put("label", "奖金金额");
        bonusSalColumn.put("prop", "bonusSal");
        columns.add(bonusSalColumn);

        Map<String, String> bonusCommColumn = new HashMap<>();
        bonusCommColumn.put("label", "奖金佣金");
        bonusCommColumn.put("prop", "bonusComm");
        columns.add(bonusCommColumn);

        Map<String, String> gradeColumn = new HashMap<>();
        gradeColumn.put("label", "工资等级");
        gradeColumn.put("prop", "grade");
        columns.add(gradeColumn);

        PageHelper.startPage(pageNum, pageSize);
        List<StudentVo> studentVos = dynamicTableMapper.getStudentData();
        PageInfo pageInfo = new PageInfo<>(studentVos);
        studentData.put("columns", columns);
        studentData.put("data",pageInfo.getList());
        studentData.put("total",pageInfo.getTotal());
        return studentData;
    }

    /**
     * 获取员工相关数据
     * @return
     */
    private Map<String,Object> getEmpData(Integer pageNum,Integer pageSize){
        Map<String, Object> empData = new HashMap<>();
        // 定义表格的列名
        List<Map<String, String>> columns = new ArrayList<>();

        Map<String, String> empNoColumn = new HashMap<>();
        empNoColumn.put("label", "员工编号");
        empNoColumn.put("prop", "empNo");
        columns.add(empNoColumn);

        Map<String, String> empNameColumn = new HashMap<>();
        empNameColumn.put("label", "员工姓名");
        empNameColumn.put("prop", "eName");
        columns.add(empNameColumn);

        Map<String, String> jobColumn = new HashMap<>();
        jobColumn.put("label", "员工职位");
        jobColumn.put("prop", "job");
        columns.add(jobColumn);

        Map<String, String> mgrColumn = new HashMap<>();
        mgrColumn.put("label", "经理编号");
        mgrColumn.put("prop", "mgr");
        columns.add(mgrColumn);

        Map<String, String> hireDateColumn = new HashMap<>();
        hireDateColumn.put("label", "入职日期");
        hireDateColumn.put("prop", "hireDate");
        columns.add(hireDateColumn);

        Map<String, String> salColumn = new HashMap<>();
        salColumn.put("label", "工资");
        salColumn.put("prop", "sal");
        columns.add(salColumn);

        Map<String, String> commColumn = new HashMap<>();
        commColumn.put("label", "佣金");
        commColumn.put("prop", "comm");
        columns.add(commColumn);

        Map<String, String> deptNoColumn = new HashMap<>();
        deptNoColumn.put("label", "部门编号");
        deptNoColumn.put("prop", "deptNo");
        columns.add(deptNoColumn);

        Map<String, String> dNameColumn = new HashMap<>();
        dNameColumn.put("label", "部门名称");
        dNameColumn.put("prop", "dName");
        columns.add(dNameColumn);

        Map<String, String> locColumn = new HashMap<>();
        locColumn.put("label", "部门位置");
        locColumn.put("prop", "loc");
        columns.add(locColumn);


        Map<String, String> bonusSalColumn = new HashMap<>();
        bonusSalColumn.put("label", "奖金金额");
        bonusSalColumn.put("prop", "bonusSal");
        columns.add(bonusSalColumn);

        Map<String, String> bonusCommColumn = new HashMap<>();
        bonusCommColumn.put("label", "奖金佣金");
        bonusCommColumn.put("prop", "bonusComm");
        columns.add(bonusCommColumn);

        Map<String, String> gradeColumn = new HashMap<>();
        gradeColumn.put("label", "工资等级");
        gradeColumn.put("prop", "grade");
        columns.add(gradeColumn);


        PageHelper.startPage(pageNum, pageSize);
        List<EmpVo> empVos = dynamicTableMapper.getEmpData();
        PageInfo pageInfo = new PageInfo<>(empVos);
        empData.put("columns", columns);
        empData.put("data",pageInfo.getList());
        empData.put("total",pageInfo.getTotal());
        return empData;
    }
}
