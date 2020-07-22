package com.aaa.backsystem.controller;


import com.aaa.backsystem.entity.Menu;
import com.aaa.backsystem.entity.TreeNode;
import com.aaa.backsystem.service.MenuService;
import com.aaa.backsystem.util.TreeUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author gyc
 * @since 2020-07-18
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("toShowMenu")
    public String toShowMenu(){
        return "menu/list";
    }

    @ResponseBody
    @RequestMapping("/findTreeJson")
    public List<TreeNode> findTreeJson(){
        Wrapper<Menu> wrapper = new EntityWrapper<>();
        wrapper.eq("menu_type","M");
        wrapper.or();
        wrapper.eq("menu_type","C");
        List<Menu> menuList = menuService.selectList(wrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu menu : menuList) {
            Integer id = menu.getMenuId();
            Integer pid = menu.getParentId();
            String title = menu.getMenuName();
            String icon = menu.getIcon();
            String href = menu.getUrl();
            Boolean spread = false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }
        return TreeUtil.layerTree(treeNodes);
    }

}

