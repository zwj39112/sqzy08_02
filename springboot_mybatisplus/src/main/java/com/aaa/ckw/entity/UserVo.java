package com.aaa.ckw.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    /**
     * 部门ID
     */
    @TableField("dept_id")
    private Integer deptId;
    /**
     * 登录账号
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 用户昵称
     */
    @TableField("user_name")
    private String userName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phonenumber;
    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;
    private String avatar;
    /**
     * 密码
     */
    private String password;
    /**
     * 盐加密
     */
    private String salt;
    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;
    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

    //多表查询
    private Dept dept;
}
