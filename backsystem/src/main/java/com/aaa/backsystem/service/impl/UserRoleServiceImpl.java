package com.aaa.backsystem.service.impl;

import com.aaa.backsystem.entity.UserRole;
import com.aaa.backsystem.dao.UserRoleDao;
import com.aaa.backsystem.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
