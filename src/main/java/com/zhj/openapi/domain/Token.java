package com.zhj.openapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
// 标记类
public class Token {
	private Integer id;    // 标记ID

	private Integer appId; // 应用ID

	private Integer cusId; // 客户ID

	private String accessToken; // 访问令牌

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // 把前端传过来的时间字符串,转换成Date类型
	private Date expireTime;  // 超时时间

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")    // 把前端传过来的时间字符串,转换成Date类型
	private Date startTime;   // 开始时间
}
