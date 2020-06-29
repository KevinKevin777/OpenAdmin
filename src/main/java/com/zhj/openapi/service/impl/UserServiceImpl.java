package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Role;
import com.zhj.openapi.domain.User;
import com.zhj.openapi.mapper.UserMapper;
import com.zhj.openapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 用户Service层实现类
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;  // 用户的Mapper层接口

	@Override
	// 登录
	public User login(String email, String password) {
		User user = userMapper.login(email, password);  // 得到对应邮箱用户

		if (null == user || !user.getPassword().equals(password)) { // 判断得到用户是否为空; 判断得到的用户的密码和前端传入的密码是否相等
			return null;    // 密码不相等,表示账号或密码错误,登录失败返回null
		}

		return user;    // 密码相等返回用户
	}

	@Override
	// 添加用户
	public Integer insertUser(User user) {
		return userMapper.insertUser(user);
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 添加用户对应角色关系; 先删除所有用户对应角色关系,再添加用户对应角色关系; 授权
	public Integer insertUserAndRole(Integer userId, Integer[] roleIds) {
		Integer[] ids = new Integer[1]; // 用于存储用户ID的数组
		ids[0] = userId;    // 把用户ID存储到长度为一的数组中; 因为Mapper层deleteUserAndRoleByUserId()方法接受参数是一个数组类型

		Integer num01 = userMapper.deleteUserAndRoleByUserId(ids);  // 删除用户对应角色关系表中用户ID对应的用户对应角色关系,并返回影响行数

		int num02 = 0;    // 用来记录影响行数
		for (Integer menuId : roleIds) {    // 把角色ID数组中的值逐个取出,逐条向数据库添加
			num02 += userMapper.insertUserAndRole(userId, menuId);  // 记录向数据库添加影响的行数
		}

		Integer num03 = num01 + num02;  // 合并删除影响行数,和添加影响行数

		return num03; // 返回影响行数
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 删除用户
	public Integer deleteUser(Integer[] ids) {
		Integer num01 = userMapper.deleteUser(ids); // 删除用户
		Integer num02 = userMapper.deleteUserAndRoleByUserId(ids);  // 删除用户对应角色关系表中用户ID对应的用户对应角色关系,并返回影响行数
		Integer num03 = num01 +num02;   // 删除用户影响行数和删除用户对应角色关系影像行的和

		return num03;   // 返回总影响行
	}

	@Override
	// 修改用户
	public Integer updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	// 查询全部用户 或 根据条件查询用户
	public PageInfo<User> selectUser(Integer page, Integer limit, String realName, String email, Integer status) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<User> users = userMapper.selectUser(realName, email, status);  // 得到全部用户信息 或 根据条件查询到的用户

		PageInfo<User> pageInfo = new PageInfo<>(users);    // 通过把全部用户信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}

	@Override
	// 查询用户对应角色ID,根据用户ID查询
	public List<Integer> selectRoleIdByUserId(Integer userId) {
		return userMapper.selectRoleIdByUserId(userId);
	}

	@Override
	// 查询全部角色列表
	public List<Role> selectAllRole() {
		return userMapper.selectAllRole();
	}
}
