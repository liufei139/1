package com.example.service;

import com.example.pojo.DeptLog;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface DeptLogService {


    void insert(DeptLog deptLog);
}
