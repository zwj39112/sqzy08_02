package com.aaa.ckw.dao;

import com.aaa.ckw.entity.User;
import com.aaa.ckw.entity.UserVo;
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
 * @author ckw
 * @since 2020-07-17
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * user表与dept的name一对一查询
     * @return
     */
    List<UserVo> selectUserVoList(@Param("PageInfo") Page pageInfo, @Param("condition") Map<String,Object> map);
}
