package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Application;
import com.zhj.openapi.mapper.ApplicationMapper;
import com.zhj.openapi.mapper.TokenMapper;
import com.zhj.openapi.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 应用Service层实现类
public class ApplicationServiceImpl implements ApplicationService {
	@Autowired
	private ApplicationMapper applicationMapper;  // 客户的Mapper层接口

	@Autowired
	private TokenMapper tokenMapper;  // 标记的Mapper层接口

	@Override
	// 添加应用
	public Integer insertApplication(Application application) {
		return applicationMapper.insertApplication(application);    // 通过Mapper层向数据库添加数据
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 删除应用
	public Integer deleteApplication(Integer[] ids) {
		Integer num01 = applicationMapper.deleteApplication(ids);       // 从数据库中删除应用,并返回影响行数
		Integer num02 = tokenMapper.deleteTokenByApplicationId(ids);    // 从数据库中删除标记,根据应用ID,并返回影响行数
		Integer num03 =  num01 + num02;

		return num03;   // 返回影响行数
	}

	@Override
	// 修改应用
	public Integer updateApplication(Application application) {
		return applicationMapper.updateApplication(application);    // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 查询全部应用 或 根据条件查询应用
	public PageInfo<Application> selectApplication(Integer page, Integer limit, String appName) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Application> applications = applicationMapper.selectApplication(appName);  // 得到全部应用信息 或 根据条件查询到的应用

		PageInfo<Application> pageInfo = new PageInfo<>(applications);    // 通过把应用信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}
}
