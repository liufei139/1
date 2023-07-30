package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeptMapper {

    /*
        查询所有部门的数据
     */
    @Select("select * from dept")
    List<Dept> list();

    /*
        根据ID删除部门数据
     */
    @Delete("delete from dept where id = #{id}")
    void delList(Integer id);

    /*
        添加部门数据
     */
    @Options(keyProperty = "id", useGeneratedKeys = true)
    @Insert("insert into dept (name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void addList(Dept dept);

    /*
        根据ID查询部门数据
     */
    @Select("select * from dept where id=#{id}")
    List<Dept> getById(Integer id);

    /*
        修改部门数据
     */
    @Update("update dept set name=#{name} where id=#{id}")
    void modify(Dept dept);
}
