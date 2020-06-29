package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Role;
import com.zhj.openapi.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 用户Mapper层接口
public interface UserMapper {
	// 登录
	User login(@Param("email") String email, String password);

	// 添加用户
	Integer insertUser(User user);

	// 添加用户对应角色
	Integer insertUserAndRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

	// 删除用户
	Integer deleteUser(Integer[] ids);

	// 删除用户对应角色表中数据,根据用户ID
	Integer deleteUserAndRoleByUserId(Integer[] userIds);

	// 修改用户
	Integer updateUser(User user);

	// 查询全部用户 或 根据条件查询用户
	List<User> selectUser(@Param("realName") String realName, @Param("email") String email, @Param("status") Integer status);

	// 查询用户对应角色ID,根据用户ID查询
	List<Integer> selectRoleIdByUserId(@Param("userId") Integer userId);

	// 查询全部角色列表
	List<Role> selectAllRole();
}
