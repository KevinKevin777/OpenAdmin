package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Token;

// 标记Service层接口
public interface TokenService {
	// 修改标记
	Integer updateToken(Token token);

	// 删除标记
	Integer deleteToken(Integer[] ids);

	// 查询全部标记 或 根据条件查询标记
	PageInfo<Token> selectToken(Integer page, Integer limit, String accessToken);
}
