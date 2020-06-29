package com.zhj.openapi.service.impl;

import com.zhj.openapi.domain.Menu;
import com.zhj.openapi.mapper.MenuMapper;
import com.zhj.openapi.pojo.Constant;
import com.zhj.openapi.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
// 菜单Service层实现类
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuMapper menuMapper;  // 菜单Mapper层接口

	@Override
	// 添加菜单
	public Integer insertMenu(Menu menu) {
		return menuMapper.insertMenu(menu);
	}

	@Override
	// 删除菜单
	@Transactional    // 事务处理注解
	public Integer deleteMenu(Integer[] ids) {
		Integer num01 = menuMapper.deleteMenu(ids); // 删除菜单
		Integer num02 = menuMapper.deleteRoleAndMenu(ids);  // 删除角色对应菜单关系

		Integer num03 = num01 + num02;  // 总共影响行数

		return num03;   // 返回总共影响行数
	}

	@Override
	// 修改菜单
	public Integer updateMenu(Menu menu) {
		return menuMapper.updateMenu(menu);
	}

	@Override
	// 根绝用户ID得到左侧菜单列表
	public List<Menu> getUserMenusByUserId(Integer id) {
		List<Menu> menus = menuMapper.getUserMenusByUserId(id); // 得大用户权限对应的菜单列表

		return makeMenuTree(menus); // 返回制作后菜单树(制作后的所有一级菜单,其中一级菜单中包含二级菜单)
	}

	@Override
	// 查询全部菜单
	public List<Menu> getMenuList() {
		List<Menu> menus = menuMapper.getAllMenu();    // 得到菜单列表

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

		List<Menu> menuSortList = new ArrayList<>();    // 空集合,用于存放排序后的菜单列表

		return sortMenuList(firstMenus, menuSortList);   // 对菜单列表排序,按照父子关系从上到下添加到新集合中,返回排序后的新集合
	}

	@Override
	// 查询一级菜单二级菜单
	public List<Menu> selectOneAndTwoMenu() {
		List<Menu> menus = menuMapper.getAllMenu(); // 得到全部菜单

		return makeMenuTree(menus); // 返回制作后菜单树(制作后的所有一级菜单,其中一级菜单中包含二级菜单)
	}

	@Override
	// 查询一个菜单,根据ID
	public Menu selectMenuById(Integer id) {
		return menuMapper.selectMenuById(id);
	}

	// 对菜单列表排序; 按照父子关系从上到下添加到新集合中
	private List<Menu> sortMenuList(List<Menu> menus, List<Menu> menuSortList) {
		for (Menu menu : menus) {
			menuSortList.add(menu); // 把菜单添加到新集合

			if (0 < menu.getChildren().size()) {    // 如果该目录有子元素
				sortMenuList(menu.getChildren(), menuSortList); // 通过递归,将子元素添加到新集合中
			}
		}

		return menuSortList;    // 返回排序后的菜单列表
	}

	// 制作菜单树方法
	private List<Menu> makeMenuTree(List<Menu> menus) {
		List<Menu> firstMenus = new ArrayList<>();  // 存放所有一级菜单

		Map<Integer, Menu> menuMap = new HashMap<>();  // 将所有菜单的ID作为Key,菜单对象作为值,存放在map集合中

		// 得到所有的一级菜单
		for (Menu menu : menus) {   // 遍历菜单集合
			if (null == menu.getParentId()) {   // 判断是否是一级菜单
				firstMenus.add(menu);   // 存放一级菜单
			}

			menuMap.put(menu.getId(), menu); // 将所有菜单的ID作为Key,菜单对象作为值,存放在map集合中
		}

		// 为一级菜单添加子菜单
		for (Menu menu : menus) {
			if (null != menu.getParentId()) {   // 排除一级菜单
				if (Constant.BUTTON_TYPE != menu.getType()) {   // 排除三级菜单
					// 现在只剩下二级菜单

					Menu menu1 = menuMap.get(menu.getParentId());    // 得到二级菜单对应父级菜单对象
					menu1.getChildren().add(menu);  // 将本二级菜单添加到他所对应的父级菜单的children属性中;
													// 因为引用对象传的是地址,所以这样改变了菜单的children属性时,就等于在改变firstMenus集合中的一级菜单的children属性
				}
			}
		}

		return firstMenus;  // 返回所有一级菜单
	}
}
