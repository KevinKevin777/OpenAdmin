package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Token;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 标记Mapper层接口
public interface TokenMapper {
	// 修改标记
	Integer updateToken(Token token);

	// 删除标记
	Integer deleteToken(Integer[] ids);

	// 删除标记,根据客户ID
	Integer deleteTokenByCustomerId(@Param("customerIds") Integer[] customerIds);

	// 删除标记,根据应用ID
	Integer deleteTokenByApplicationId(@Param("ApplicationIds") Integer[] ApplicationIds);

	// 查询全部标记 或 根据条件查询标记
	List<Token> selectToken(@Param("accessToken") String accessToken);
}
