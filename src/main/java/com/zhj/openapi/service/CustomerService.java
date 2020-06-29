package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Customer;

import java.util.List;

// 客户Service层接口
public interface CustomerService {
	// 添加客户
	Integer insertCustomer(Customer customer);

	// 删除客户
	Integer deleteCustomer(Integer[] ids);

	// 修改客户
	Integer updateCustomer(Customer customer);

	// 查询全部客户 或 根据条件查询客户
	PageInfo<Customer> selectCustomer(Integer page, Integer limit, String username, Integer state);

	// 查询客户列表
	List<Customer> selectAllCustomer();
}
