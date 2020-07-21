package com.aaa.ckw.service;

import com.aaa.ckw.entity.User;
import com.aaa.ckw.entity.UserVo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ckw
 * @since 2020-07-17
 */
public interface UserService extends IService<User> {
    /**
     * user表与dept的name一对一查询
     * @return
     */
    List<UserVo> selectUserVoList(Page pageInfo, Map<String,Object> map);
}
