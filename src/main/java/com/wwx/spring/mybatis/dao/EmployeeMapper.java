package com.wwx.spring.mybatis.dao;


import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 *
 *      增删改查
 */
public interface EmployeeMapper {

    /**
     *  1. 需要查询时有多个参数  可以使用注解的方式@Param  配置文件中使用id = #{id} 获取
     *  2.需要查询时有多个参数   并且符合业务数据模型 可以直接传入POJO  使用Map
     *  3.多个参数的方法需要经常使用 推荐使用TO(Transfer Object)
     *  4.当传入参数为集合时  也采用map的方式封装  key:Collection 的key（collection）
     *          List的key（list）  集合都可以使用collection  数组的key（array）
     * @param
     */
    void getEmployeeByParam(@Param("id")Integer id,@Param("lastName")String name);

    Employee getEmployeeMap(Map<String,Object> map);

    /**
     *   获取第一个id   id = #{list[0]}
     *   如果是数组获取第一个id  id = #{array[0]}
     *   其他数据一次类推
     * @param list
     */
    void getEmployeeByCollection(List<Integer> list);


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
