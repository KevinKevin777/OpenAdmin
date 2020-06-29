package com.zhj.openapi.service;

import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.pojo.R;

import java.util.List;

// 菜单Service层接口
public interface MenuService {
	// 添加菜单
	Integer insertMenu(Menu menu);

	// 删除菜单
	Integer deleteMenu(Integer[] ids);

	// 修改菜单
	Integer updateMenu(Menu menu);

	// 根绝用户ID得到左侧菜单列表
	List<Menu> getUserMenusByUserId(Integer id);

	// 查询全部菜单
	List<Menu> getMenuList();

	// 查询一级菜单二级菜单
	List<Menu> selectOneAndTwoMenu();

	// 查询一个菜单,根据ID
	Menu selectMenuById(Integer id);
}
