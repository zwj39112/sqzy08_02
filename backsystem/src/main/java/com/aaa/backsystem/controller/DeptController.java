package com.aaa.backsystem.controller;


import com.aaa.backsystem.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("toShowDept")
    public String toShowDept(){
        return "dept/list";
    }

}

