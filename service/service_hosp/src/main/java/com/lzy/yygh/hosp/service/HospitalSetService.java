package com.lzy.yygh.hosp.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.yygh.model.hosp.HospitalSet;

public interface HospitalSetService extends IService<HospitalSet> {

	String getSignKey(String hoscode);
}
