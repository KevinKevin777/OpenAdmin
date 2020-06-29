package com.zhj.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 路由类
public class Mapping {
	private Integer id;    // 路由ID

	private String gatewayApiName;  // 网关Api名称

	private String insideApiUrl;    // 内部Api地址

	private Integer state; // 路由状态

	private String description; // 描述

	private String serviceId;   // 服务ID

	private String idempotents; // 幂等元

	private String needfee; // 需求费
}
