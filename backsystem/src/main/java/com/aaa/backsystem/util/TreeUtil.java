package com.aaa.backsystem.util;

import com.aaa.backsystem.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * fileName:TreeUtil
 * description:
 * author:gyc
 * createTime:2020/7/21 13:00
 * version:1.0.0
 */
public class TreeUtil {
    public static List<TreeNode> layerTree(List<TreeNode> treeNodes){
        //查询出所有节点的数据
        //List<TreeNode> treeNodes = powerDao.treeNodes();
        //实例化拼装数据
        List<TreeNode> tempTreeNode = new ArrayList<>();
        //判断集合防止空表
        if (treeNodes!=null&&treeNodes.size()>0){
            //循环查一级节点
            for (TreeNode treeNode : treeNodes) {
                if (treeNode.getPid()==0){
                    tempTreeNode.add(treeNode);
                    bindChildren(treeNode,treeNodes);
                }
            }
        }
        return tempTreeNode;
    }

    /**
     * 子节点的子节点
     * @param currentNode
     * @param treeNodes
     */
    public static void bindChildren(TreeNode currentNode, List<TreeNode> treeNodes){
        //循环查找子节点
        for (TreeNode treeNode : treeNodes) {
            //如果一个节点说当前节点的子节点
            if (treeNode.getPid().equals(currentNode.getId())){
                //获取当前节点的子节点集合
                List<TreeNode> children = currentNode.getChildren();
                if (children==null){//如果以前没有孩子，就要初始化孩子结合
                    children = new ArrayList<>();
                }
                children.add(treeNode); //添加当前节点到孩子集合
                currentNode.setChildren(children); //设置为孩子集合
                //每个孩子节点也可能有其他孩子，要重复绑定  递归
                bindChildren(treeNode,treeNodes);
            }
        }
    }
}
