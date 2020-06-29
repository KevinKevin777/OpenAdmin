package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.SystemParam;

// 系统参数Service层接口
public interface SystemParamService {
	// 添加系统参数
	Integer insertSystemParam(SystemParam systemParam);

	// 删除系统参数
	Integer deleteSystemParam(Integer[] ids);

	// 修改系统参数
	Integer updateSystemParam(SystemParam systemParam);

	// 查询全部系统参数 或 根据条件查询系统参数
	PageInfo<SystemParam>  selectSystemParam(Integer page, Integer limit, String name, Integer state);
}
