package com.iweb.mapper;

import com.iweb.vo.DynamicTableVo;
import com.iweb.vo.EmpVo;
import com.iweb.vo.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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


    List<DynamicTableVo> getData(@Param("pageSize") Integer pageSize,@Param("offset") Integer offset,@Param("searchKey") String searchKey);

    Integer getCount(@Param("searchKey") String searchKey);
}
