package com.wwx.spring.mybatis.dao;

import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *      动态sql
 *
 */

public interface EmployeeMapperDynamicSql {

    List<Employee> getEmpConditionByIf(Employee employee);

    List<Employee> getEmpConditionByTrim(Employee employee);

    List<Employee> getEmpConditionByChoose(Employee employee);


    /**
     *
     *    使用foreach   collection 遍历员工  返回包装成集合
     * @param id
     * @return
     */
    List<Employee> getEmpConditionByForeach(@Param("id")List<Integer> id);

    void  updateEmp(Employee employee);

    void addEmp(@Param("emp")List<Employee> emp);
}
