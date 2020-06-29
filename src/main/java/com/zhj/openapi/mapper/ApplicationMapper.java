package com.zhj.openapi.mapper;

import com.zhj.openapi.domain.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


// 应用Mapper层接口
public interface ApplicationMapper {
	// 添加应用
	Integer insertApplication(Application application);

	// 删除应用
	Integer deleteApplication(Integer[] ids);

	// 删除应用,根据客户ID
	Integer deleteApplicationByCustomerId(@Param("customerIds") Integer[] customerIds);

	// 修改应用
	Integer updateApplication(Application application);

	// 查询全部应用 或 根据条件查询应用
	List<Application> selectApplication(@Param("appName") String appName);
}
