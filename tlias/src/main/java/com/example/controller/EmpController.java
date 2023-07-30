package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 请求路径：/emps
     * 请求方式：GET
     * 接口描述：该接口用于员工列表数据的条件分页查询
     *
     * @RequestParam 中defaultValue为设置该参数的默认值
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询,参数{},{},{},{},{},{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     *请求路径：/emps/{ids}
     * 请求方式：DELETE
     * 接口描述：该接口用于批量删除员工的数据信息
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delList(@PathVariable List<Integer> ids){
        log.info("批量删除,ids{}",ids);
        empService.delList(ids);
        return Result.success();
    }

    /**
     * 请求路径：/emps
     * 请求方式：POST
     * 接口描述：该接口用于添加员工的信息
     */
    @Log
    @PostMapping
    public Result insert(@RequestBody Emp emp){
        log.info("添加员工,{}",emp);
        empService.insert(emp);
        return Result.success();
    }

    /**
     * 请求路径：/emps/{id}
     * 请求方式：GET
     * 接口描述：该接口用于根据主键ID查询员工的信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工{}",id);
        Emp emp=empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 请求路径：/emps
     * 请求方式：PUT
     * 接口描述：该接口用于修改员工的数据信息
     */
    @Log
    @PutMapping
    public Result modify(@RequestBody Emp emp){
        log.info("修改员工数据");
        empService.modify(emp);
        return Result.success();
    }



}
