package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 应用类
public class Application {
	private Integer id;    // 应用ID

	private String corpName;    // 公司名字

	private String appName; // 应用名字

	private String appKey;  // 应用Key

	private String appSecret;   //  应用秘钥

	private String redirectUrl; //  跳转网址

	private Integer limit;

	private String description; // 描述

	private Integer cusId; // 客户ID

	private Integer state; // 应用状态
}
