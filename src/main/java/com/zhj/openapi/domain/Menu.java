package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 菜单类
public class Menu {
	private Integer id; // 菜单ID

	private String name;    // 菜单名字

	private Integer parentId;   // 菜单的父级菜单ID

	private String url; // 菜单要访问的页面地址或接口地址

	private String icon;    // 菜单图标

	private String perms;   // 菜单要访问的页面地址或接口地址缩写

	private Integer type;   // 菜单类型; 比如: 一级菜单,二级菜单...

	private Integer sort;   // 菜单排序规则

//	private String parentName;  //父级菜单名称

	private List<Menu> children = new ArrayList<>();    //关联子菜单
}
