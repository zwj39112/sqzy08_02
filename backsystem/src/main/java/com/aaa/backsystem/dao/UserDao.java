package com.aaa.backsystem.dao;

import com.aaa.backsystem.entity.User;
import com.aaa.backsystem.entity.UserVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
public interface UserDao extends BaseMapper<User> {
    List<UserVo> selectUserVoList(Page<UserVo> pageInfo, @Param("condition") Map<String ,Object> condition);
}
