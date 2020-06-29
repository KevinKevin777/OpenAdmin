package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Role;
import com.zhj.openapi.domain.User;

import java.util.List;

// 用户Service层接口
public interface UserService {
	// 登录
	User login(String email, String password);

	// 添加用户
	Integer insertUser(User user);

	// 添加用户对应角色关系; 先删除所有用户对应角色关系,再添加用户对应角色关系; 授权
	Integer insertUserAndRole(Integer userId, Integer[] roleIds);

	// 删除用户
	Integer deleteUser(Integer[] ids);

	// 修改用户
	Integer updateUser(User user);

	// 查询全部用户 或 根据条件查询用户
	PageInfo<User> selectUser(Integer page, Integer limit, String realName, String email, Integer status);

	// 查询用户对应角色ID,根据用户ID查询
	List<Integer> selectRoleIdByUserId(Integer userId);

	// 查询全部角色列表
	List<Role> selectAllRole();
}
