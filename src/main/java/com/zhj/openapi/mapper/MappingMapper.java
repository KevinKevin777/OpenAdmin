package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Mapping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 路由Mapper层接口
public interface MappingMapper {
	// 添加路由
	Integer insertMapping(Mapping mapping);

	// 删除路由
	Integer deleteMapping(Integer[] ids);

	// 修改路由
	Integer updateMapping(Mapping mapping);

	// 查询全部路由 或 根据条件查询路由
	List<Mapping> selectMapping(@Param("gatewayApiName") String gatewayApiName, @Param("state") Integer state);
}
