package com.aaa.backsystem.entity;

import java.util.List;

/**
 * fileName:LayuiTable
 * description:
 * author:gyc
 * createTime:2020/7/16 15:22
 * version:1.0.0
 */
public class LayuiTable {

    /**
     * code : 0
     * msg :
     * count : 1000
     * data : []
     */
    private int code;
    private String msg;
    private int count;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
