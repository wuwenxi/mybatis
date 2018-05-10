package com.wwx.spring.mybatis.dao;


import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface EmployeeMapperAnnotation {

    @Select("select * from employee where id = #{id}")
    Employee getEmpById(Integer id);

    @Insert("insert into employee(last_name,gender,email) " +
            "values (#{lastName},#{gender},#{email})")
    void insertEmployee(Employee employee);

    @Update("update employee set last_name = #{lastName},gender = #{gender},email = #{email}" +
            "where id = #{id}")
    void updateEmp(Employee  employee);

    @Delete("delete from employee where email = #{email}")
    void deleteEmpByEmail(String  email);
}
