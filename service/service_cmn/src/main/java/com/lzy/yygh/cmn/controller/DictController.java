package com.lzy.yygh.cmn.controller;

import com.lzy.yygg.common.result.Result;
import com.lzy.yygh.cmn.service.DictService;
import com.lzy.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api("数据字典接口")
@RestController
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

	@Autowired
	private DictService dictService;

	// 导出数据字典
	@PostMapping("importData")
	public Result importDict(MultipartFile file) {
		dictService.importDictData(file);
		return Result.ok();
	}

	// 导出数据接口字典
	@GetMapping("exportData")
	public void exportDict(HttpServletResponse response) {
		dictService.exportDictData(response);
	}


	@ApiOperation(value = "根据数据id查询子数据列表")
	@GetMapping("findChildData/{id}")
	public Result findChildData(@PathVariable Long id) {
		List<Dict> list = dictService.findChlidData(id);
		return Result.ok(list);
	}

}
