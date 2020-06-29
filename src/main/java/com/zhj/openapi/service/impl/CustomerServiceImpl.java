package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Customer;
import com.zhj.openapi.mapper.*;
import com.zhj.openapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// 客户Service层实现类
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;  // 客户的Mapper层接口

	@Autowired
	private ApplicationMapper applicationMapper;  // 客户的Mapper层接口

	@Autowired
	private TokenMapper tokenMapper;  // 标记的Mapper层接口

	@Autowired
	private RechargeMapper rechargeMapper;  // 充值的Mapper层接

	@Override
	// 添加客户
	public Integer insertCustomer(Customer customer) {
		return customerMapper.insertCustomer(customer); // 通过Mapper层向数据库添加数据
	}

	@Transactional  // 通过注解方式开启事务
	@Override
	// 删除客户
	public Integer deleteCustomer(Integer[] ids) {
		Integer num01 = customerMapper.deleteCustomer(ids);                     // 从数据库中删除客户数据,并返回影响行数
		Integer num02 = applicationMapper.deleteApplicationByCustomerId(ids);   // 从数据库中删除应用数据,根据约客户ID,并返回影响行数
		Integer num03 = tokenMapper.deleteTokenByCustomerId(ids);               // 从数据库中删除标记数据,根据约客户ID,并返回影响行数
		Integer num04 = rechargeMapper.deleteRechargeByCustomerId(ids);         // 从数据库中删除充值数据,根据约客户ID,并返回影响行数
		Integer num05 = num01 + num02 + num03 + num04;

		return num05;   // 返回影响行数
	}

	@Override
	// 修改客户
	public Integer updateCustomer(Customer customer) {
		return customerMapper.updateCustomer(customer); // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 查询全部客户 或 根据条件查询客户
	public PageInfo<Customer> selectCustomer(Integer page, Integer limit, String username, Integer state) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Customer> customers = customerMapper.selectCustomer(username, state);  // 得到全部客户信息 或 根据条件查询到的客户

		PageInfo<Customer> pageInfo = new PageInfo<>(customers);    // 通过把客户信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}

	@Override
	// 查询客户列表
	public List<Customer> selectAllCustomer() {
		return customerMapper.selectAllCustomer();  // 返回从数据查询到的数据
	}

}
