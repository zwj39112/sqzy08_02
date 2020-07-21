package com.aaa.ckw.service.impl;

import com.aaa.ckw.entity.User;
import com.aaa.ckw.dao.UserDao;
import com.aaa.ckw.entity.UserVo;
import com.aaa.ckw.service.UserService;
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
 * @author ckw
 * @since 2020-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserVo> selectUserVoList(Page pageInfo, Map<String,Object> map) {

        return userDao.selectUserVoList(pageInfo,map);
    }
}
