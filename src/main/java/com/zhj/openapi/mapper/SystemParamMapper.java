package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.SystemParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemParamMapper {
	// 添加系统参数
	Integer insertSystemParam(SystemParam systemParam);

	// 删除系统参数
	Integer deleteSystemParam(Integer[] ids);

	// 修改系统参数
	Integer updateSystemParam(SystemParam systemParam);

	// 查询全部系统参数 或 根据条件查询系统参数
	List<SystemParam> selectSystemParam(@Param("name") String name, @Param("state") Integer state);
}
