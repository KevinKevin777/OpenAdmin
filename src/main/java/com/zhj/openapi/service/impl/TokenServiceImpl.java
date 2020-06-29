package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Token;
import com.zhj.openapi.mapper.TokenMapper;
import com.zhj.openapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 标记Service层实现类
public class TokenServiceImpl implements TokenService {
	@Autowired
	private TokenMapper tokenMapper;  // 标记的Mapper层接口

	@Override
	// 修改标记
	public Integer updateToken(Token token) {
		return tokenMapper.updateToken(token);  // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 删除标记
	public Integer deleteToken(Integer[] ids) {
		return tokenMapper.deleteToken(ids);    // 从数据库中删除数据,并返回影响行数
	}

	@Override
	// 查询全部标记 或 根据条件查询标记
	public PageInfo<Token> selectToken(Integer page, Integer limit, String accessToken) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Token> tokens = tokenMapper.selectToken(accessToken);  // 得到全部标记信息 或 根据条件查询到的标记

		PageInfo<Token> pageInfo = new PageInfo<>(tokens);    // 通过把标记信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}
}
