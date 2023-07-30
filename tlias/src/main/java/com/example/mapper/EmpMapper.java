package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询总记录数
     *
     * @return ：总记录数
     */
    /*
    @Select("select count(*) from emp")
    public Long count();

    *//**
     * 分页查询获取列表数据
     *//*
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
    */

    //PageHelper写法
    //@Select("select * from emp")
    public List<Emp> list(@Param("name") String name,
                          @Param("gender") Short gender,
                          @Param("begin") LocalDate begin,
                          @Param("end") LocalDate end);

    //批量删除
    void delList(@Param("ids") List<Integer> ids);

    //添加员工
    @Options(keyProperty = "id",useGeneratedKeys = true)
    void insert(Emp emp);

    //根据ID查询员工数据
    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

    //修改员工数据
    void modify(Emp emp);

    //员工登录
    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    //根据部门id删除员工
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);


}
