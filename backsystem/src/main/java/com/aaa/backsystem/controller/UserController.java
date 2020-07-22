package com.aaa.backsystem.controller;


import com.aaa.backsystem.entity.LayuiTable;
import com.aaa.backsystem.entity.Result;
import com.aaa.backsystem.entity.User;
import com.aaa.backsystem.entity.UserVo;
import com.aaa.backsystem.service.DeptService;
import com.aaa.backsystem.service.RoleService;
import com.aaa.backsystem.service.UserService;
import com.aaa.backsystem.util.MyConstants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;

    /**
     * 获取部门信息，角色信息，填充下拉列表
     * 页面跳转
     * @return
     */
    @RequestMapping("/toShowUser")
    public String toShowUser(Model model) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("del_flag",0);

        List deptList = deptService.selectList(wrapper);
        List roleList = roleService.selectList(wrapper);

        model.addAttribute("deptList",deptList);
        model.addAttribute("roleList",roleList);

        return "user/list";
    }


    /**
     * 获取用户信息（包含部门信息），分页等操作
     *
     * @param page
     * @param limit
     * @param searchLoginName
     * @param searchUserName
     * @param searchPhoneNumber
     * @return
     */
    @GetMapping("/userList")
    @ResponseBody
    public LayuiTable userList(Integer page, Integer limit, String searchLoginName, String searchUserName, String searchPhoneNumber) {
        LayuiTable table = new LayuiTable();

        Wrapper wrapper = new EntityWrapper();
        //多条件查询所需要的集合
        Map<String, Object> condition = new HashMap();

        if (null != searchLoginName && !"".equals(searchLoginName)) {
            wrapper.like("login_name", searchLoginName);
            condition.put("login_name", searchLoginName);
        }
        if (null != searchUserName && !"".equals(searchUserName)) {
            wrapper.like("user_name", searchUserName);
            condition.put("user_name", searchUserName);
        }
        if (null != searchPhoneNumber && !"".equals(searchPhoneNumber)) {
            wrapper.like("phonenumber", searchPhoneNumber);
            condition.put("phonenumber", searchPhoneNumber);
        }
        wrapper.eq("del_flag", 0);//存在的用户
        condition.put("del_flag", 0);


        int userListCount = userService.selectCount(wrapper);//查询存在用户的条数

        //如果表中没有数据。则不进行分页查询
        if (userListCount > 0) {

            Page<UserVo> pageInfo = new Page(page, limit);//分页

            List<UserVo> userVoList = userService.selectUserVoList(pageInfo, condition);
            //从分页结果中提取list集合
            table.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            table.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            table.setData(userVoList);
            table.setCount(userListCount);
        }
        return table;
    }

    /**
     * 单行删除(逻辑删除)
     *
     * @param user
     * @return
     */
    @DeleteMapping("/oddDelUser")
    @ResponseBody
    public Result oddDelUser(User user) {
        Result<Boolean> result = new Result<>();

        try {
            user.setDelFlag("2");
            boolean flag = userService.updateById(user);

            result.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            result.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            result.setData(flag);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(MyConstants.OPERATION_FAIL_CODE);
        result.setMsg(MyConstants.OPERATION_FAIL_MESSAGE);
        return result;
    }
}

