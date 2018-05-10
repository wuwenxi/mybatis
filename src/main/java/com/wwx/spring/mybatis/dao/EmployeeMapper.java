package com.wwx.spring.mybatis.dao;


import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 *
 *      增删改查
 */
public interface EmployeeMapper {

    /**
     *  1. 需要查询时有多个参数  可以使用注解的方式@Param  配置文件中使用id = #{id} 获取
     *  2.需要查询时有多个参数   并且符合业务数据模型 可以直接传入POJO
     * @param
     */
    void getEmployeeByParam(@Param("id")Integer id,@Param("lastName")String name);

    void getEmployeeMap(Map<String,Object> map);


    /**
     *
     *    增删改查
     *
     * @param id
     * @return
     */
    Employee getEmpById(Integer id);

    Boolean insertEmployee(Employee employee);

    Boolean updateEmp(Employee  employee);

    Boolean deleteEmpByEmail(String  email);

}
