package com.aaa.ckw.service.impl;

import com.aaa.ckw.entity.UserRole;
import com.aaa.ckw.dao.UserRoleDao;
import com.aaa.ckw.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author ckw
 * @since 2020-07-17
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
