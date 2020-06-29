package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Recharge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 充值Mapper层接口
public interface RechargeMapper {
	// 添加充值
	Integer insertRecharge(Recharge recharge);

	// 删除充值
	Integer deleteRecharge(Integer[] ids);

	// 删除充值,根据客户ID
	Integer deleteRechargeByCustomerId(@Param("customerIds") Integer[] customerIds);

	// 修改充值
	Integer updateRecharge(Recharge recharge);

	// 查询全部充值
	List<Recharge> selectRecharge();
}
