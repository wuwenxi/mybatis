package com.wwx.spring.mybatis.dao;


import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapperPlus {

    @MapKey("id")
    Map<Integer,Employee> getEmpByIdLikeReturnMap(String lastName);

    //进行级联查询
    Employee getEmpAndDept(Integer id);

    Employee getEmpAndDeptByStep(Integer id);

    List<Employee> getEmpByIdStep(Integer deptId);

}
