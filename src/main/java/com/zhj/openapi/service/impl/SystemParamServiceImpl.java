package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.SystemParam;
import com.zhj.openapi.mapper.SystemParamMapper;
import com.zhj.openapi.service.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 系统参数Service层实现类
public class SystemParamServiceImpl implements SystemParamService {
	@Autowired
	private SystemParamMapper systemParamMapper;  // 客户的Mapper层接口

	@Override
	// 添加系统参数
	public Integer insertSystemParam(SystemParam systemParam) {
		return systemParamMapper.insertSystemParam(systemParam);    // 向数据库添数据,并返回影响行数
	}

	@Override
	// 删除系统参数
	public Integer deleteSystemParam(Integer[] ids) {
		return systemParamMapper.deleteSystemParam(ids);    // 从数据库中删除数据,并返回影响行数
	}

	@Override
	// 修改系统参数
	public Integer updateSystemParam(SystemParam systemParam) {
		return systemParamMapper.updateSystemParam(systemParam);    // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 查询全部系统参数 或 根据条件查询系统参数
	public PageInfo<SystemParam> selectSystemParam(Integer page, Integer limit, String name, Integer state) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<SystemParam> systemParams = systemParamMapper.selectSystemParam(name, state);  // 得到全部系统参数信息 或 根据条件查询到的系统参数

		PageInfo<SystemParam> pageInfo = new PageInfo<>(systemParams);    // 通过把系统参数信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}
}
