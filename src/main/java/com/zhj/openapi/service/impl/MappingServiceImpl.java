package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Mapping;
import com.zhj.openapi.mapper.MappingMapper;
import com.zhj.openapi.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 路由Service层实现类
public class MappingServiceImpl implements MappingService {
	@Autowired
	private MappingMapper mappingMapper;  // 路由的Mapper层接口

	@Override
	// 添加路由
	public Integer insertMapping(Mapping mapping) {
		return mappingMapper.insertMapping(mapping);    // 向数据库添数据,并返回影响行数
	}

	@Override
	// 删除路由
	public Integer deleteMapping(Integer[] ids) {
		return mappingMapper.deleteMapping(ids);    // 从数据库中删除数据,并返回影响行数
	}

	@Override
	// 修改路由
	public Integer updateMapping(Mapping mapping) {
		return mappingMapper.updateMapping(mapping);    // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 查询全部路由 或 根据条件查询路由
	public PageInfo<Mapping> selectMapping(Integer page, Integer limit, String gatewayApiName, Integer state) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Mapping> mappings = mappingMapper.selectMapping(gatewayApiName, state);  // 得到全部路由信息 或 根据条件查询到的路由

		PageInfo<Mapping> pageInfo = new PageInfo<>(mappings);    // 通过把路由信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}
}
