package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 客户Mapper层接口
public interface CustomerMapper {
	// 添加客户
	Integer insertCustomer(Customer customer);

	// 删除客户
	Integer deleteCustomer(Integer[] ids);

	// 修改客户
	Integer updateCustomer(Customer customer);

	// 查询全部客户 或 根据条件查询客户
	List<Customer> selectCustomer(@Param("username") String username, @Param("state") Integer state);

	// 查询客户列表
	List<Customer> selectAllCustomer();
}
