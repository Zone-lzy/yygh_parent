package com.lzy.yygh.hosp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.yygh.hosp.repository.HospitalRepository;
import com.lzy.yygh.hosp.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lzy.yygh.hosp.mapper.HospitalSetMapper;
import com.lzy.yygh.hosp.service.HospitalSetService;
import com.lzy.yygh.model.hosp.HospitalSet;



@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {

	//2 根据传递过来医院编码，查询数据库，查询签名
	@Override
	public String getSignKey(String hoscode) {
		QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
		wrapper.eq("hoscode", hoscode);
		HospitalSet hospitalSet = baseMapper.selectOne(wrapper);
		return hospitalSet.getSignKey();
	}
}
