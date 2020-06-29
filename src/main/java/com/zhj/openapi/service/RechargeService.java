package com.zhj.openapi.service;

import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Recharge;

// 充值Service层接口
public interface RechargeService {
	// 添加充值
	Integer insertRecharge(Recharge recharge);

	// 删除充值
	Integer deleteRecharge(Integer[] ids);

	// 修改充值
	Integer updateRecharge(Recharge recharge);

	// 查询全部充值
	PageInfo<Recharge> selectRecharge(Integer page, Integer limit);
}
