package com.wwx.spring.mybatis.dao;

import com.wwx.spring.mybatis.beans.Department;
import com.wwx.spring.mybatis.beans.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 *         1.获取SqlSessionFactory对象  线程不安全  每次使用都要重新创建新的对象
 *         2.获取SqlSession对象 使用getMapper方法获取代理对象
 *         3.若是增删改  采用SqlSession sqlSession = sqlSessionFactory.openSession()
 *            需要手动上传数据 调用SqlSession的commit方法通知
 *         还可以采用SqlSession sqlSession = sqlSessionFactory.openSession(true)
 *              的方法自动上传数据
 *
 */

public class EmployeeMapperTest {

    private SqlSessionFactory getSqlSessionFactory() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     *
     *     通过XML的 方式来进行增删改查
     *
     *
     * @throws Exception
     */

    @Test
    public void getEmpById() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //Employee employee =  mapper.getEmpById(1);

            /**
             *   key value 键值对与配置文件中的相同
             */
           /* Map<String,Object> map = new HashMap<>();
            map.put("id",1);
            map.put("lastName","Rose");
            Employee employee = mapper.getEmployeeMap(map);
            System.out.println(employee);*/
            /**
             *
             *    返回值为集合 的处理
             *
             */
            List<Employee> list =mapper.getEmpByLastName("%a%");
            for (Employee employee:list){
                System.out.println(employee);
            }
            /**
             *   返回map类型
             */
            //返回单个map类型
            Map<String,Object> map = mapper.getEmpByIdReturnMap(1);
            System.out.println(map);

            //返回一组map类型
            Map<Integer,Employee> empMap = mapper.getEmpByIdLikeReturnMap("%a%");
            System.out.println(empMap);
        }
    }

    @Test
    public void deleteEmployee() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //Employee employee = new Employee(3,"Jack","1",null);
            String email = "www.231312@wwx.com";
            Boolean bool = mapper.deleteEmpByEmail(email);
            sqlSession.commit();
            System.out.println("删除 " + bool);
        }
    }

    /**
     *
     *                  通过注解的方式进行增删改查
     *
     *
     * @throws Exception
     */

    @Test
    public void getEmpByIdAnnotation() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapperAnnotation annotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee =  annotation.getEmpById(2);
            System.out.println(employee);
        }
    }

    @Test
    public void deleteEmployeeAnnotation() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapperAnnotation annotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            //Employee employee = new Employee(null,null,null,"www.134124@wwx.com");
            String email = "www.134124@wwx.com";
            annotation.deleteEmpByEmail(email);
            sqlSession.commit();
        }
    }

    @Test
    public void test01() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapperPlus mapperPlus = sqlSession.getMapper(EmployeeMapperPlus.class);

          /*   Map<Integer,Employee> map = mapperPlus.getEmpByIdLikeReturnMap("%a%");
            System.out.println(map);*/

            Employee employee = mapperPlus.getEmpAndDeptByStep(11);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
    /*        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptById(1);
            System.out.println(department);*/
        }

    }

    @Test
    public void test02() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmp());
        }
    }

    @Test
    public void test03() throws Exception{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
/*
            List<Employee> employees = mapper.getEmpConditionByIf(
                    new Employee(null,"%a%",null,null));
            for (Employee emp:employees){
                System.out.println(emp);
            }

            List<Employee> employee = mapper.getEmpConditionByTrim(
                    new Employee(null,"%a%",null,null));
            for (Employee emp:employee){
                System.out.println(emp);
            }

            List<Employee> employees1 = mapper.getEmpConditionByChoose(
                    new Employee(null,"%a%",null,null));
            for(Employee emp:employees1){
                System.out.println(emp);
            }

            List<Employee> employees2 = mapper.getEmpConditionByForeach(Arrays.asList(7,8,9,10));
            for(Employee emp:employees2){
                System.out.println(emp);
            }*/

            /**
             *  批量插入
             */
            List<Employee> employee = new ArrayList<>();
            employee.add(new Employee(null,"jack","1","jack@wwx.com"
                    ,new Department(3)));
            employee.add(new Employee(null,"lyon","0","lyon@wwx.com"
                    ,new Department(2)));
            mapper.addEmp(employee);
            sqlSession.commit();
        }
    }

    @Test
    public void test04() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            EmployeeMapperDynamicSql mapper = sqlSession.getMapper(EmployeeMapperDynamicSql.class);
            mapper.updateEmp(new Employee(1,"tom","1","tom@wwx.com"));
            sqlSession.commit();
        }
    }



}