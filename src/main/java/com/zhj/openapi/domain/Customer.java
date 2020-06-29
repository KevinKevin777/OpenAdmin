package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 客户类
public class Customer {
	private Integer id; // 客户ID

	private String username;    // 客户名字

	private String password;    // 客户密码

	private String nickname;    // 昵称

	private BigDecimal money;   // 余额

	private String address; // 地址

	private Integer state;  // 状态
}
