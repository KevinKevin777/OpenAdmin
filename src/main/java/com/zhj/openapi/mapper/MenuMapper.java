package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Menu;

import java.util.List;

// 菜单Mapper层接口
public interface MenuMapper {
	// 添加菜单
	Integer insertMenu(Menu menu);

	// 删除菜单
	Integer deleteMenu(Integer[] ids);

	// 删除角色对应菜单关系
	Integer deleteRoleAndMenu(Integer[] ids);

	// 修改菜单
	Integer updateMenu(Menu menu);

	// 根绝用户ID得到左侧菜单列表
	List<Menu> getUserMenusByUserId(Integer id);

	// 查询全部菜单
	List<Menu> getAllMenu();

	// 查询一个菜单,根据ID
	Menu selectMenuById(Integer id);
}
