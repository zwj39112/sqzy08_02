package com.aaa.backsystem.entity;

import lombok.Data;

/**
 * 包装返回类
 * @param <T>
 */
@Data
public class Result<T> {
    /**
     * 返回的状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private T data;
}
