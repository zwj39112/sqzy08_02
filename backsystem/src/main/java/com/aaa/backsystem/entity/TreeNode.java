package com.aaa.backsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * fileName:TreeNode
 * description:
 * author:gyc
 * createTime:2020/7/21 11:58
 * version:1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    private Integer id;
    private Integer pid;
    private String title;
    private String icon;
    private String href;
    private Boolean spread;

    private List<TreeNode> children;

    public TreeNode(Integer id, Integer pid, String title, String icon, String href,Boolean spread) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.icon = icon;
        this.href = href;
        this.spread = spread;
    }
}
