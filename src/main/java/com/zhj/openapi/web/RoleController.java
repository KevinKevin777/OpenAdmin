package com.zhj.openapi.web;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.domain.Role;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/role")
// 角色处理器
public class RoleController {
	@Autowired
	RoleService roleService;    // 角色Service层接口

	@PostMapping("/add")
	// 添加角色
	public R insertRole(Role role) {
		Integer num = roleService.insertRole(role); // 向数据库添加角色,返回影响行数

		return 0 < num ? new R(true, "添加角色成功", null) : new R(false, "添加角色失败", null);    // 根据影响行数判断添加角色是否成功
	}

	@PostMapping("/assignMenu")
	// 添加角色对应菜单关系; 先删除所有角色对应菜单关系,再添加角色对应菜单关系; 授权
	public R insertRoleAndMenu(String roleId, String[] menuIds) {
		Integer[] idsArray = new Integer[menuIds.length];

		for (int i = 0; i < menuIds.length; i++) {  // 把字符串数组转换成整型数组
			idsArray[i] = Integer.parseInt(menuIds[i]);
		}

		Integer num = roleService.insertRoleAndMenu(Integer.parseInt(roleId), idsArray);  // 得到删除角色对应菜单影响行数和添加角色对应菜单影响行数总和

		return 0 < num ? new R(true, "角色授权菜单成功", null) : new R(false, "角色授权菜单失败", null);
	}

	@PostMapping("/delete")
	// 删除角色
	public R deleteRole(String[] ids) {
		Integer[] idsArray = new Integer[ids.length];

		for (int i = 0; i < ids.length; i++) {  // 把字符串数组转换成整型数组
			idsArray[i] = Integer.parseInt(ids[i]);
		}

		Integer num = roleService.deleteRole(idsArray);  // 得到删除角色影响行数

		return 0 < num ? new R(true, "删除角色成功", null) : new R(false, "删除角色失败", null);
	}

	@PostMapping("/update")
	// 修改角色
	public R updateRole(Role role) {
		Integer num = roleService.updateRole(role);   // 得到修改角色影响数据库表行数

		return 0 < num ? new R(true, "修改角色成功", null) : new R(false, "修改角色失败", null);
	}

	@GetMapping("/table")
	// 查询全部角色
	public TableData<Role> selectAllRole(String page, String limit, String name) {
		PageInfo<Role> rolePageInfo = roleService.selectAllRole(Integer.parseInt(page), Integer.parseInt(limit), name);   // 得到 全部角色信息 或 根据名字模糊查询结果 的PageInfo对象

		return new TableData<Role>(0, rolePageInfo.getTotal(), "全部角色数据", rolePageInfo.getList());   // 返回表格需要格式JSON数据,状态码,数据总量,提示信息,数据
	}

	@GetMapping("/roleMenu")
	// 查询角色已授权的菜单列表,根据角色ID查询
	public List<Integer> selectMenuListById(String roleId) {
		return roleService.selectMenuListById(Integer.parseInt(roleId));   // 返回角色已授权菜单列表
	}

	@GetMapping("/menuTree")
	// 查询全部菜单列表
	public List<Menu> selectMenuList() {
		return roleService.selectAllMenuList();   // 返回全部菜单列表
	}
}
