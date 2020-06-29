package com.zhj.openapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhj.openapi.domain.Recharge;
import com.zhj.openapi.mapper.RechargeMapper;
import com.zhj.openapi.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 充值Service层实现类
public class RechargeServiceImpl implements RechargeService {
	@Autowired
	private RechargeMapper rechargeMapper;  // 充值的Mapper层接

	@Override
	// 添加充值
	public Integer insertRecharge(Recharge recharge) {
		return rechargeMapper.insertRecharge(recharge); // 向数据库添数据,并返回影响行数
	}

	@Override
	// 删除充值
	public Integer deleteRecharge(Integer[] ids) {
		return rechargeMapper.deleteRecharge(ids);  // 从数据库中删除数据,并返回影响行数
	}

	@Override
	// 修改充值
	public Integer updateRecharge(Recharge recharge) {
		return rechargeMapper.updateRecharge(recharge); // 对数据库中数据进行修改,并返回影响行数
	}

	@Override
	// 查询全部充值
	public PageInfo<Recharge> selectRecharge(Integer page, Integer limit) {
		PageHelper.startPage(page, limit);  // 设置页码和一页最大行数

		List<Recharge> recharges = rechargeMapper.selectRecharge();  // 得到全部充值信息

		PageInfo<Recharge> pageInfo = new PageInfo<>(recharges);    // 通过把充值信息添加到PageInfo对象中,创建一个PageInfo对象

		return pageInfo;    // 返回PageInfo对象
	}
}
