package com.zhj.openapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 封账表格返回数据类
public class TableData<T> {
	private Integer code;   // 返回数据状态码

	private long count; // 数据总量

	private String msg; // 提示信息

	private List<T> data;   // 数据
}
