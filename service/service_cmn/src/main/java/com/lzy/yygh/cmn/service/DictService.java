package com.lzy.yygh.cmn.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.lzy.yygh.model.cmn.Dict;
import com.lzy.yygh.model.hosp.HospitalSet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictService extends IService<Dict> {

	//根据数据id查询子数据列表
	List<Dict> findChlidData(Long id);

	void exportDictData(HttpServletResponse response);

	void importDictData(MultipartFile file);
}
