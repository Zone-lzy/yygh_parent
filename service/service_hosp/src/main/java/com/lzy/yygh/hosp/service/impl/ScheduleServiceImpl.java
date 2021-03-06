package com.lzy.yygh.hosp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lzy.yygh.hosp.repository.ScheduleRepository;
import com.lzy.yygh.hosp.service.ScheduleService;
import com.lzy.yygh.model.hosp.Department;
import com.lzy.yygh.model.hosp.Schedule;
import com.lzy.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	/**
	 * 上传排班接口
	 *
	 * 先查看数据库中是否存在，存在更新，不存在添加
	 * @param paramMap
	 */
	@Override
	public void save(Map<String, Object> paramMap) {
		// paramMap转化为Department对象
		String paramMapString = JSONObject.toJSONString(paramMap);
		Schedule schedule = JSONObject.parseObject(paramMapString, Schedule.class);

		// 排版+医院不能重复，根据排版编号和医院编号确定
		Schedule scheduleExist = scheduleRepository.
				getScheduleByHoscodeAndHosScheduleId(schedule.getHoscode(), schedule.getHosScheduleId());

		if(scheduleExist != null) {
			scheduleExist.setUpdateTime(new Date());
			scheduleExist.setIsDeleted(0);
			scheduleExist.setStatus(1);
			scheduleRepository.save(scheduleExist);
		} else {
			schedule.setCreateTime(new Date());
			schedule.setUpdateTime(new Date());
			schedule.setIsDeleted(0);
			schedule.setStatus(1);
			scheduleRepository.save(schedule);
		}
	}

	//查询排班接口
	@Override
	public Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo) {
		// 创建Pageable对象，设置当前页和每页记录数
		// 0是第一页
		Pageable pageable = PageRequest.of(page - 1, limit);
		//创建Example对象
		Schedule schedule = new Schedule();
		BeanUtils.copyProperties(scheduleQueryVo, schedule);
		schedule.setIsDeleted(0);
		schedule.setStatus(1);

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
				.withIgnoreCase(true);
		Example<Schedule> example = Example.of(schedule, matcher);

		Page<Schedule> all = scheduleRepository.findAll(example, pageable);
		return all;
	}

	// 删除排班接口 先查数据库在进行依据id进行删除
	@Override
	public void remove(String hoscode, String hosScheduleId) {
		// 根据医院编号和排班编号查询信息
		Schedule schedule = scheduleRepository.getScheduleByHoscodeAndHosScheduleId(hoscode, hosScheduleId);
		if(schedule != null) {
			scheduleRepository.deleteById(schedule.getId());
		}
	}
}
