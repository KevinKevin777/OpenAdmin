package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 用户类
public class User {
	private Integer id; // 用户ID

	private String password;    // 用户密码

	private String email;   // 用户邮箱

	private String realName;    // 用户名字

	private Integer status; // 用户激活状态
}
