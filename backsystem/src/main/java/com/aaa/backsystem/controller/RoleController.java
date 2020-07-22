package com.aaa.backsystem.controller;


import com.aaa.backsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("toShowRole")
    public String toShowRole(){
        return "role/list";
    }

}

