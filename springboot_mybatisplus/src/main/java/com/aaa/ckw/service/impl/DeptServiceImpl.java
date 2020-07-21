package com.aaa.ckw.service.impl;

import com.aaa.ckw.entity.Dept;
import com.aaa.ckw.dao.DeptDao;
import com.aaa.ckw.service.DeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ckw
 * @since 2020-07-17
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

}
