package com.aaa.ckw.controller;


import com.aaa.ckw.entity.PageTable;
import com.aaa.ckw.entity.Result;
import com.aaa.ckw.entity.User;
import com.aaa.ckw.entity.UserVo;
import com.aaa.ckw.service.UserService;
import com.aaa.ckw.util.MyConstants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ckw
 * @since 2020-07-17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 跳转页面
     * @return
     */
    @GetMapping("/toUser")
    public String toUser() {
        return "html/user/userList";
    }

    /**
     * 获取用户列表，分页等操作
     * @param page
     * @param limit
     * @param searchLoginName
     * @param searchUserName
     * @param searchPhoneNumber
     * @return
     */
    @GetMapping("/userList")
    @ResponseBody
    public PageTable userList(Integer page, Integer limit,String searchLoginName,String searchUserName,String searchPhoneNumber) {
        PageTable table = new PageTable();

        Wrapper wrapper = new EntityWrapper();
        //多条件查询所需要的集合
        Map<String ,Object> condition= new HashMap();

        if (null != searchLoginName && !"".equals(searchLoginName)){
            wrapper.like("login_name", searchLoginName);
            condition.put("login_name", searchLoginName);
        }
        if (null != searchUserName && !"".equals(searchUserName)){
            wrapper.like("user_name", searchUserName);
            condition.put("user_name", searchUserName);
        }
        if (null != searchPhoneNumber && !"".equals(searchPhoneNumber)){
            wrapper.like("phonenumber", searchPhoneNumber);
            condition.put("phonenumber", searchPhoneNumber);
        }
        wrapper.eq("del_flag", 0);//存在的用户
        condition.put("del_flag", 0);


        int userListCount = userService.selectCount(wrapper);//查询存在用户的条数

        //如果表中没有数据。则不进行分页查询
        if(userListCount>0){

            Page<UserVo> pageInfo = new Page(page, limit);//分页

            List<UserVo> userVoList = userService.selectUserVoList(pageInfo,condition);
            //从分页结果中提取list集合
            table.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            table.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            table.setData(userVoList);
            table.setCount(userListCount);

            return table;
        }else{

            return table;
        }
    }

    /**
     * 修改用户
     * @return
     */
    @PutMapping("/updateUser")
    @ResponseBody
    public Result updateUser(User user){
        Result<Boolean> result= new Result<>();

        try {
            boolean insert = userService.updateById(user);

            result.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            result.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            result.setData(insert);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(MyConstants.OPERATION_FAIL_CODE);
        result.setMsg(MyConstants.OPERATION_FAIL_MESSAGE);
        return result;
    }

    /**
     * 单行删除(假性删除)
     * @param user
     * @return
     */
    @DeleteMapping("/oddDelUser")
    @ResponseBody
    public Result oddDelUser(User user){
        Result<Boolean> result= new Result<>();

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

