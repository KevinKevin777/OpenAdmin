package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Role;
import com.zhj.openapi.domain.User;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/user")
// 用户处理器
public class UserController {
	@Autowired
	UserService userService;    // 用户Service层接口

	@PostMapping("/add")
	// 添加用户
	public R insertUser(User user) {
		Integer num = userService.insertUser(user); // 向数据库添加用户,返回影响行数

		return 0 < num ? new R(true, "添加用户成功", null) : new R(false, "添加用户失败", null);    // 根据影响行数判断添加用户是否成功
	}

	@PostMapping("/assignRole")
	// 添加用户对应角色关系; 先删除所有用户对应角色关系,再添加用户对应角色关系; 授权
	public R insertUserAndRole(String userId, String[] roleIds) {
		Integer[] idsArray = new Integer[roleIds.length];

		for (int i = 0; i < roleIds.length; i++) {  // 把字符串数组转换成整型数组
			idsArray[i] = Integer.parseInt(roleIds[i]);
		}

		Integer num = userService.insertUserAndRole(Integer.parseInt(userId), idsArray);  // 得到删除用户对应角色影响行数和添加用户对应角色影响行数

		return 0 < num ? new R(true, "用户授权角色成功", null) : new R(false, "用户授权角色失败", null);
	}

	@PostMapping("/del")
	// 删除用户
	public R deleteUser(String[] ids) {
		Integer[] idsArray = new Integer[ids.length];

		for (int i = 0; i < ids.length; i++) {  // 把字符串数组转换成整型数组
			idsArray[i] = Integer.parseInt(ids[i]);
		}

		Integer num = userService.deleteUser(idsArray);  // 得到删除用户影响行数

		return 0 < num ? new R(true, "删除用户成功", null) : new R(false, "删除用户失败", null);
	}

	@PostMapping("/update")
	// 修改用户
	public R updateUser(User user) {
		Integer num = userService.updateUser(user);   // 得到修改角色影响数据库表行数

		return 0 < num ? new R(true, "修改用户成功", null) : new R(false, "修改用户失败", null);
	}

	@GetMapping("/table")
	// 查询全部用户 或 根据条件查询用户
	public TableData<User> selectUser(String page, String limit, String realName, String email, String status) {
		if (null == status || "".equals(status)) {  // 如果status没有传值,把status赋值为-1,以便Mapper映射文件中进行判断是否拼接查询条件: 0 == status or 1 == status
			status = "-1";
		}

		PageInfo<User> userPageInfo = userService.selectUser(Integer.parseInt(page), Integer.parseInt(limit), realName, email, Integer.parseInt(status));   // 得到全部用户信息的PageInfo对象 或 根据条件查询到的用户信息的PageInfo对象

		return new TableData<User>(0, userPageInfo.getTotal(), "全部用户数据", userPageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}

	@GetMapping("/userRole")
	// 查询用户对应角色ID,根据用户ID查询
	public List<Integer> selectRoleIdByUserId(String userId) {
		return userService.selectRoleIdByUserId(Integer.parseInt(userId));   // 返回用户对应角色ID
	}

	@GetMapping("/roleTree")
	// 查询全部角色列表
	public List<Role> selectAllRole() {
		return userService.selectAllRole();   // 返回全部角色列表
	}
}
