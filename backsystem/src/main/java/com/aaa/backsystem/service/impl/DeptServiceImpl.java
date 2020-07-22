package com.aaa.backsystem.service.impl;

import com.aaa.backsystem.entity.Dept;
import com.aaa.backsystem.dao.DeptDao;
import com.aaa.backsystem.service.DeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}
