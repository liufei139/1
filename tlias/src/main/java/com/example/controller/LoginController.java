package com.example.controller;

import com.example.Utils.JwtUtils;
import com.example.pojo.Emp;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT加密
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    EmpService empService;

    /**
     * 请求路径：/login
     * 请求方式：POST
     * 接口描述：该接口用于员工登录Tlias智能学习辅助系统，登录完毕后，系统下发JWT令牌。
     */
    @PostMapping("/login")
    public Result Login(@RequestBody Emp emp) {
        log.info("员工登录,用户名{},密码{}", emp.getUsername(), emp.getPassword());
        Emp emp1 = empService.Login(emp);
        if (emp1 != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", emp1.getId());
            map.put("name", emp1.getName());
            map.put("username", emp1.getUsername());
            String jwt = JwtUtils.generateJwt(map);
            return Result.success(jwt);
        } else {
            return Result.error("用户名或密码错误");
        }

    }
}
