package com.lzy.yygh.hosp.service;

import com.lzy.yygh.model.hosp.Hospital;

import java.util.Map;

public interface HospitalService {
	void save(Map<String, Object> paramMap);

	//调用service方法实现根据医院编号查询
	Hospital getByHoscode(String hoscode);
}
