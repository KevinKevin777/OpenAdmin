package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Application;
import com.zhj.openapi.domain.Customer;

import java.util.List;

// 应用Service层接口
public interface ApplicationService {
	// 添加应用
	Integer insertApplication(Application application);

	// 删除应用
	Integer deleteApplication(Integer[] ids);

	// 修改应用
	Integer updateApplication(Application application);

	// 查询全部应用 或 根据条件查询应用
	PageInfo<Application> selectApplication(Integer page, Integer limit, String appName);
}
