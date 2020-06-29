package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 系统参数类
public class SystemParam {
	private Integer id;    // 系统参数ID

	private String name;    // 系统参数名字

	private String description; // 描述

	private Integer state; // 系统参数状态
}
