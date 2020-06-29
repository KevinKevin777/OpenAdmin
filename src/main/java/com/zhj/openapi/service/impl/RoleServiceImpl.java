package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.domain.Role;
import com.zhj.openapi.mapper.RoleMapper;
import com.zhj.openapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
// 角色Service层实现类
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;  // 角色Mapper层接口

	@Override
	// 添加角色
	public Integer insertRole(Role role) {
		Integer num = roleMapper.insertRole(role);  // 向数据库添加角色,返回影响行数

		return num; // 返回影响行数
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 添加角色对应菜单关系; 先删除所有角色对应菜单关系,再添加角色对应菜单关系; 授权
	public Integer insertRoleAndMenu(Integer roleId, Integer[] menuIds) {
		Integer[] ids = new Integer[1]; // 用于存储角色ID的数组
		ids[0] = roleId;    // 把角色ID存储到长度为一的数组中; 因为Mapper层deleteRoleAndMenuByRoleId()方法接受参数是一个数组类型

		Integer num01 = roleMapper.deleteRoleAndMenuByRoleId(ids);  // 删除角色对应菜单关系表中角色ID对应的角色对应菜单关系,并返回影响行数

		int num02 = 0;    // 用来记录影响行数
		for (Integer menuId : menuIds) {    // 把菜单ID数组中的值逐个取出,逐条向数据库添加
			num02 += roleMapper.insertRoleAndMenu(roleId, menuId);  // 记录向数据库添加影响的行数
		}

		Integer num03 = num01 + num02;  // 合并删除影响行数,和添加影响行数

		return num03; // 返回影响行数
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 删除角色
	public Integer deleteRole(Integer[] ids) {
		Integer num01 = roleMapper.deleteUserAndRolByRoleId(ids);   // 删除用户对应角色关系表中对应ID的角色,并返回影响行数
		Integer num02 = roleMapper.deleteRoleById(ids);             // 删除角色表中对应ID的角色,并返回影响行数
		Integer num03 = roleMapper.deleteRoleAndMenuByRoleId(ids);  // 删除角色对应菜单关系表中对应ID的角色,并返回影响行数

		Integer num04 = num01 + num02 + num03;  // 得到三张表中总共影响的行数

		return num04;   // 返回影响总行数
	}

	@Override
	// 修改角色
	public Integer updateRole(Role role) {
		return roleMapper.updateRole(role); // 返回修改角色影响行数
	}

	@Override
	// 查询全部用户 或 根据名字模糊查询
	public PageInfo<Role> selectAllRole(Integer page, Integer limit, String name) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Role> roles = roleMapper.selectAllRole(name);  // 得到全部角色信息 或 根据名字模糊查询结果

		PageInfo<Role> pageInfo = new PageInfo<>(roles);    // 通过把查询结果添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}

	@Override
	// 查询角色已授权的菜单列表,根据角色ID查询
	public List<Integer> selectMenuListById(Integer roleId) {
		List<Integer> ids = roleMapper.selectMenuListById(roleId);    // 得到角色对应菜单ID列表

		return ids; // 返回角色对应菜单ID列表
	}

	@Override
	// 查询全部菜单列表
	public List<Menu> selectAllMenuList() {
		List<Menu> menus = roleMapper.selectAllMenuList();    // 得到菜单列表

		return mergeMenu(menus);
	}

	// 把子级菜单合并到父级菜单中
	private List<Menu> mergeMenu(List<Menu> menus) {
		List<Menu> firstMenus = new ArrayList<>();  // 存放所有一级菜单

		Map<Integer, Menu> menuMap = new HashMap<>();  // 将所有菜单的ID作为Key,菜单对象作为值,存放在map集合中

		// 得到所有的一级菜单
		for (Menu menu : menus) {   // 遍历菜单集合
			if (null == menu.getParentId()) {   // 判断是否是一级菜单
				firstMenus.add(menu);   // 存放一级菜单
			}

			menuMap.put(menu.getId(), menu); // 将所有菜单的ID作为Key,菜单对象作为值,存放在map集合中
		}

		// 把所有的子级菜单添加到他所对应的父级菜单的children属性中
		for (Menu menu : menus) {
			if (null != menu.getParentId() && menuMap.containsKey(menu.getParentId())) {   // 如果菜单没有父级菜单ID,那么该菜单是一个子级菜单
				Menu menu1 = menuMap.get(menu.getParentId());   // 得到子级菜单对应父级菜单对象
				menu1.getChildren().add(menu);  // 将本子级菜单添加到对应父级菜单的children属性中
			}
		}

		return firstMenus;
	}

}
