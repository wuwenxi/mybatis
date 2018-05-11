package com.wwx.spring.mybatis.dao;

import com.wwx.spring.mybatis.beans.Department;

public interface DepartmentMapper {

    Department getDeptById(Integer id);
}
