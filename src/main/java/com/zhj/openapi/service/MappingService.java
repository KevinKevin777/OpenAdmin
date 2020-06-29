package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Mapping;

// 路由Service层接口
public interface MappingService {
	// 添加路由
	Integer insertMapping(Mapping mapping);

	// 删除路由
	Integer deleteMapping(Integer[] ids);

	// 修改路由
	Integer updateMapping(Mapping mapping);

	// 查询全部路由 或 根据条件查询路由
	PageInfo<Mapping> selectMapping(Integer page, Integer limit, String gatewayApiName, Integer state);
}
