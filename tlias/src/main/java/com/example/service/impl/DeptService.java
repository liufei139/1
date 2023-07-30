package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptService implements com.example.service.DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional
    @Override
    //@Transactional(rollbackFor = Exception.class)
    public void delList(Integer id) {
        try {
            deptMapper.delList(id);//根据id删除部门数据
            //如果这里发生异常，则会产生上面的对数据库操作成功，但是下面的员工没有删除。所以需要使用事务管理
            empMapper.deleteByDeptId(id);//根据部门id删除该部门下的员工
        } finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此次解散的是" + id + "部门");
            deptLogService.insert(deptLog);
        }

    }

    @Override
    public void addList(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addList(dept);
    }

    @Override
    public List<Dept> getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void modify(Dept dept) {
        deptMapper.modify(dept);
    }
}
