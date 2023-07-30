package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void delList(List<Integer> ids);

    void insert(Emp emp);

    Emp getById(Integer id);

    void modify(Emp emp);

    Emp Login(Emp emp);
}
