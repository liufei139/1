package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//设置作用域
//@Scope("prototype")
@Slf4j/*记录日志的注解*/
@RestController
@RequestMapping("/depts")
public class DeptController {

    //创建日志对象，等同于注解@Slf4j
    //private static Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 请求路径：/depts
     * 请求方式：GET
     * 接口描述：该接口用于部门列表数据查询
     */
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    //@MyLog
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.list();
        return Result.success(deptList);
    }

    /**
     * 请求路径：/depts/{id}
     * 请求方式：DELETE
     * 接口描述：该接口用于根据ID删除部门数据
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delList(@PathVariable Integer id){
        log.info("根据ID删除部门数据");
        deptService.delList(id);
        return Result.success();
    }

    /**
     * 请求路径：/depts
     * 请求方式：POST
     * 接口描述：该接口用于添加部门数据
     */
    @Log
    @PostMapping
    public Result addList(@RequestBody Dept dept){
        log.info("部门添加数据");
        deptService.addList(dept);
        return Result.success();
    }

    /**
     * 请求路径：/depts/{id}
     * 请求方式：GET
     * 接口描述：该接口用于根据ID查询部门数据
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询部门数据");
        List<Dept> deptList=deptService.getById(id);
        return Result.success(deptList);
    }

    /**
     * 请求路径：/depts
     * 请求方式：PUT
     * 接口描述：该接口用于修改部门数据
     */
    @Log
    @PutMapping
    public Result modify(@RequestBody Dept dept){
        log.info("修改部门数据");
        deptService.modify(dept);
        return Result.success();
    }
}
