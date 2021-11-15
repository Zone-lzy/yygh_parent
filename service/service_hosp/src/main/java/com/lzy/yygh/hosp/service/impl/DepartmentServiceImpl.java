package com.lzy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzy.yygh.hosp.repository.DepartmentRepository;
import com.lzy.yygh.hosp.service.DepartmentService;
import com.lzy.yygh.model.hosp.Department;
import com.lzy.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	// 注入bean,MongoDB的操作
	@Autowired
	private DepartmentRepository departmentRepository;

	// 上传科室信息
	@Override
	public void save(Map<String, Object> paramMap) {
		// paramMap转化为Department对象
		String paramMapString = JSONObject.toJSONString(paramMap);
		Department department = JSONObject.parseObject(paramMapString, Department.class);

		// 科室+医院不能重复，根据科室编号和医院编号确定
		Department departmentExist = departmentRepository.
				getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getDepcode());

		if(departmentExist != null) {
			departmentExist.setUpdateTime(new Date());
			departmentExist.setIsDeleted(0);
			departmentRepository.save(departmentExist);
		} else {
			department.setCreateTime(new Date());
			department.setUpdateTime(new Date());
			department.setIsDeleted(0);
			departmentRepository.save(department);
		}
	}

	// 查询科室接口
	@Override
	public Page<Department> findPageDepartment(int page, int limit, DepartmentQueryVo departmentQueryVo) {
		// 创建Pageable对象，设置当前页和每页记录数
		// 0是第一页
		Pageable pageable = PageRequest.of(page - 1, limit);
		//创建Example对象
		Department department = new Department();
		BeanUtils.copyProperties(departmentQueryVo, department);
		department.setIsDeleted(0);

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				.withIgnoreCase(true);
		Example<Department> example = Example.of(department, matcher);

		Page<Department> all = departmentRepository.findAll(example, pageable);
		return all;
	}

	//删除科室接口
	@Override
	public void remove(String hoscode, String depcode) {
		// 先根据医院编号和科室编号进行查询，有的话就进行删除
		Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
		if(department != null) {
			// 调用删除方法
			departmentRepository.deleteById(department.getId());
		}
	}
}
