package com.zhj.openapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 充值类
public class Recharge {
	private Integer id;    // 充值ID

	private Integer cusId; // 客户ID

	private BigInteger orderId;   // 订单ID

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;  // 创建时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date updateTime;  // 更新时间

	private Integer money; // 余额

	private Integer state; // 充值状态

	private Integer paymentType;   // 付款类型
}
