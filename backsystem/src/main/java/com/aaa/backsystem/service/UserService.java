package com.aaa.backsystem.service;

import com.aaa.backsystem.entity.User;
import com.aaa.backsystem.entity.UserVo;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
public interface UserService extends IService<User> {
    List<UserVo> selectUserVoList(Page<UserVo> pageInfo, Map<String ,Object> condition);
}
