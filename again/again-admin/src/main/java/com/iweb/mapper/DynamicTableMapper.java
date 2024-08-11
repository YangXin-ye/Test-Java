package com.iweb.mapper;

import com.iweb.vo.EmpVo;
import com.iweb.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author：
 * @Package：com.iweb.mapper
 * @Project：Test-Java
 * @name：DynamicTableMapper
 * @Date：2024/8/10 23:05
 * @Description:
 */
@Mapper
public interface DynamicTableMapper {
    List<StudentVo> getStudentData();

    List<EmpVo> getEmpData();
}
