package com.example.service;

import com.example.pojo.Dept;

import java.time.LocalDateTime;
import java.util.List;

public interface DeptService {

    //查询全部部门的数据
    List<Dept> list();

    //删除部门数据
    void delList(Integer id);

    //部门添加数据
    void addList(Dept dept);

    //根据ID查询部门数据
    List<Dept> getById(Integer id);

    //修改部门数据
    void modify(Dept dept);
}
