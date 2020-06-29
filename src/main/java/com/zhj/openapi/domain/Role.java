package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 角色类
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Role {
	private Integer id; // 角色ID

	private String name;    // 角色名字

	private String remark;  // 角色备注

	private Integer status; // 角色状态
}
