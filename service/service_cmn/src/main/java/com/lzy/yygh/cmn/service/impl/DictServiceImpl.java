package com.lzy.yygh.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzy.yygh.cmn.listener.DictListener;
import com.lzy.yygh.cmn.mapper.DictMapper;
import com.lzy.yygh.cmn.service.DictService;
import com.lzy.yygh.model.cmn.Dict;
import com.lzy.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

	//mapper不用自己注入，因为继承里面已经有了

	//根据数据id查询子数据列表
	@Override
	@Cacheable(value = "dict",keyGenerator = "keyGenerator")
	public List<Dict> findChlidData(Long id) {
		//根据id查询子数组
		QueryWrapper<Dict> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id", id);
		List<Dict> dicts = baseMapper.selectList(wrapper);
		//向list集合中每个dict对象设置hasChildren
		for(Dict dict : dicts) {
			Long dictId = dict.getId();
			boolean isChild = this.isChildren(dictId);
			dict.setHasChildren(isChild);
		}
		return dicts;
	}

	@Override
	@CacheEvict(value = "dict", allEntries=true)
	public void exportDictData(HttpServletResponse response) {
		// 设置下载的信息
		// 设置文件类型
		response.setContentType(("application/vnd.ms-excel"));
		// 设置字符编码
		response.setCharacterEncoding("utf-8");
		String fileName = "dict";
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
		// 以下载方式打开
		// 查询数据库
		List<Dict> dictList = baseMapper.selectList(null);
		// Dict --> DictEeVo
		List<DictEeVo> dictEeVoList = new ArrayList<>();
		for (Dict dict:	dictList) {
			DictEeVo dictEeVo = new DictEeVo();
			// dictEeVo.setId(dict.getId)
			BeanUtils.copyProperties(dict, dictEeVo);
			dictEeVoList.add(dictEeVo);
		}

		// 调用方法进行写操作
		try {
			EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict数据字典")
					.doWrite(dictEeVoList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void importDictData(MultipartFile file) {
		try {
			EasyExcel.read(file.getInputStream(), DictEeVo.class, new DictListener(baseMapper)).sheet().doRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//处理是否还有子节点
	private boolean isChildren(Long id) {
		QueryWrapper<Dict> wrapper = new QueryWrapper<>();
		wrapper.eq("parent_id", id);
		Integer count = baseMapper.selectCount(wrapper); // 查看是否还有子节点  0 wu  1 有
		return count > 0;  //直接返回判断

	}
}
