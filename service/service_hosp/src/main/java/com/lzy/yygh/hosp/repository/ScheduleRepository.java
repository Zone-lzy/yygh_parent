package com.lzy.yygh.hosp.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
	// 排版+医院不能重复，根据排版编号和医院编号确定
	Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);
}
