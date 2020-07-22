package com.aaa.backsystem.service.impl;

import com.aaa.backsystem.entity.User;
import com.aaa.backsystem.dao.UserDao;
import com.aaa.backsystem.entity.UserVo;
import com.aaa.backsystem.service.UserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVo> selectUserVoList(Page<UserVo> pageInfo, Map<String, Object> condition) {
        List<UserVo> userVoList = userDao.selectUserVoList(pageInfo, condition);
        return userVoList;
    }
}
