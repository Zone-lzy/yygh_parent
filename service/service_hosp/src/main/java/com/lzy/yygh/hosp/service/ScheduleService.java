package com.lzy.yygh.hosp.service;

import com.lzy.yygh.model.hosp.Schedule;
import com.lzy.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface ScheduleService {
	//上传排班接口
	void save(Map<String, Object> paramMap);

	//查询排班接口
	Page<Schedule> findPageSchedule(int page, int limit, ScheduleQueryVo scheduleQueryVo);

	// 删除排版接口
	void remove(String hoscode, String hosScheduleId);
}
