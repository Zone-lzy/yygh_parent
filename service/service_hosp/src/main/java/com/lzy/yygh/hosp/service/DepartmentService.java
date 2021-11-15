package com.lzy.yygh.hosp.service;

import com.lzy.yygh.model.hosp.Department;
import com.lzy.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface DepartmentService {
	// 添加科室信息
	void save(Map<String, Object> paramMap);

	// 查询科室接口
	Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo);

	//删除科室接口
	void remove(String hoscode, String depcode);
}
