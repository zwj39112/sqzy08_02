package com.aaa.ckw.controller;


import com.aaa.ckw.entity.Dept;
import com.aaa.ckw.entity.Result;
import com.aaa.ckw.service.DeptService;
import com.aaa.ckw.util.MyConstants;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author ckw
 * @since 2020-07-17
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    DeptService deptService;

    /**
     * 获取部门列表
     * @return
     */
    @GetMapping("/DeptList")
    @ResponseBody
    public Result DeptList(){
        Result result= new Result<>();

        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("del_flag",0);
        wrapper.eq("status",0);

        try {
            List<Dept> depts = deptService.selectList(wrapper);

            result.setCode(MyConstants.OPERATION_SUCCESS_CODE);
            result.setMsg(MyConstants.OPERATION_SUCCESS_MESSAGE);
            result.setData(depts);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(MyConstants.OPERATION_FAIL_CODE);
        result.setMsg(MyConstants.OPERATION_FAIL_MESSAGE);
        return result;
    }

}

