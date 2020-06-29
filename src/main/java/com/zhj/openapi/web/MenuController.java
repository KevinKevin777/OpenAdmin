package com.zhj.openapi.web;

import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.domain.User;
import com.zhj.openapi.pojo.Constant;
import com.zhj.openapi.pojo.R;
import com.zhj.openapi.pojo.TableData;
import com.zhj.openapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
// 菜单处理器
public class MenuController {
	@Autowired
	MenuService menuService;    // 菜单Service层接口

	@PostMapping("/sys/menu/add")
	// 添加菜单
	public R insertMenu(Menu menu) {
		Integer num = menuService.insertMenu(menu);   // 得到添加菜单影响数据库表行数

		return 0 < num ? new R(true, "添加菜单成功", null) : new R(false, "添加菜单失败", null);
	}

	@PostMapping("/sys/menu/delete")
	// 删除菜单
	public R deleteMenu(String[] ids) {
		Integer[] idsArray = new Integer[ids.length];

		for (int i = 0; i < ids.length; i++) {  // 把字符串数组转换成整型数组
			idsArray[i] = Integer.parseInt(ids[i]);
		}

		Integer num = menuService.deleteMenu(idsArray);  // 得到删除菜单影响行数

		return 0 < num ? new R(true, "删除菜单成功", null) : new R(false, "删除菜单失败", null);
	}

	@PostMapping("/sys/menu/update")
	// 修改菜单
	public R updateMenu(Menu menu) {
		Integer num = menuService.updateMenu(menu);   // 得到修改菜单影响数据库表行数

		return 0 < num ? new R(true, "修改菜单成功", null) : new R(false, "修改菜单失败", null);
	}

	@GetMapping("/menus")
	// 得到左侧菜单列表; 根据用户ID,得到用户对应角色,根据对应角色权限得到可以使用的菜单列表
	public R getMenus(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute(Constant.SESSION_USER); // 得到已登录用户

		List<Menu> menus = menuService.getUserMenusByUserId(1);  //(测试代码,下一行为正确代码) 通过用户ID获得对应菜单列表
//		List<Menu> menus = menuService.getUserMenusByUserId(user.getId());  // 通过用户ID获得对应菜单列表

		return new R(true, "得到菜单列表成功", menus);  // 返回菜单列表
	}

	@GetMapping("/sys/menu/list")
	// 查询全部菜单
	public TableData<Menu> getMenuList() {

		List<Menu> menus = menuService.getMenuList();   // 得到PageInfo对象

		return new TableData<Menu>(0, menus.size(), "返回菜单列表成功", menus);
	}

	@PostMapping("/sys/menu/tree")
	// 查询一级菜单二级菜单
	public List<Menu> selectOneAndTwoMenu() {
		return menuService.selectOneAndTwoMenu();   // 返回所有一级菜单,其中一级菜单中包含二级菜单
	}

	@PostMapping("/sys/menu/info")
	// 查询一个菜单,根据ID
	public Menu selectMenuById(String id) {
		return menuService.selectMenuById(Integer.parseInt(id));
	}
}
