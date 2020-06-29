package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.domain.Role;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

// 角色Mapper层接口
public interface RoleMapper {
	// 添加角色
	Integer insertRole(Role role);

	// 添加角色对应菜单关系
	Integer insertRoleAndMenu(@Param("roleId") Integer roleId,@Param("menuId") Integer menuId);

	// 删除用户对应角色表中数据,根据角色ID
	Integer deleteUserAndRolByRoleId(Integer[] roleIds);

	// 删除角色,根据角色ID
	Integer deleteRoleById(Integer[] ids);

	// 删除角色对应菜单表中数据,根据角色ID
	Integer deleteRoleAndMenuByRoleId(Integer[] roleIds);

	// 修改角色
	Integer updateRole(Role role);

	// 查询全部用户 或 根据名字模糊查询
	List<Role> selectAllRole(@Param("name") String name);

	// 查询角色已授权的菜单列表,根据角色ID查询
	List<Integer> selectMenuListById(Integer roleId);

	// 查询全部菜单列表
	List<Menu> selectAllMenuList();
}
