package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpService implements com.example.service.EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {
        /*
        Integer start = (page - 1) * pageSize;
        List<Emp> empList = empMapper.page(start, pageSize);
        Long count = empMapper.count();
        return new PageBean(count, empList);
        */

        //PageHelper方法：
        PageHelper.startPage(page, pageSize);

        List<Emp> empList = empMapper.list(name, gender, begin, end);

        Page<Emp> empPage = (Page<Emp>) empList;

        return new PageBean(empPage.getTotal(), empPage.getResult());

    }

    @Override
    public void delList(List<Integer> ids) {
        empMapper.delList(ids);
    }

    @Override
    public void insert(Emp emp) {
        LocalDateTime createTime=LocalDateTime.now();
        LocalDateTime updateTime=LocalDateTime.now();
        emp.setCreateTime(createTime);
        emp.setUpdateTime(updateTime);
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void modify(Emp emp) {
        LocalDateTime updateTime=LocalDateTime.now();
        emp.setUpdateTime(updateTime);
        empMapper.modify(emp);
    }

    @Override
    public Emp Login(Emp emp) {
        return empMapper.getByUsernameAndPassword(emp);
    }
}
