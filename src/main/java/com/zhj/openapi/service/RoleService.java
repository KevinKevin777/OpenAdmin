package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.domain.Role;

import java.util.List;

// 角色Service层接口
public interface RoleService {
	// 添加角色
	Integer insertRole(Role role);

	// 添加角色对应菜单关系; 先删除所有角色对应菜单关系,再添加角色对应菜单关系; 授权
	Integer insertRoleAndMenu(Integer roleId, Integer[] menuIds);

	// 删除角色
	Integer deleteRole(Integer[] ids);

	// 修改角色
	Integer updateRole(Role role);

	// 查询全部用户 或 根据名字模糊查询
	PageInfo<Role> selectAllRole(Integer page, Integer limit, String name);

	// 查询角色已授权的菜单列表,根据角色ID查询
	List<Integer> selectMenuListById(Integer roleId);

	// 查询全部菜单列表
	List<Menu> selectAllMenuList();
}
