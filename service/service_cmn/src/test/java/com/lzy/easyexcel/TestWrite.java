package com.lzy.easyexcel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestWrite {
	public static void main(String[] args) {
		// 构建list集合，模拟数据
		List<UserData> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			UserData data = new UserData();
			data.setUid(i);
			data.setUsername("lzy" + i);
			list.add(data);
		}

		//设置wxcel文件路径和文件名称
		String fileName = "D:\\excel\\01.xlsx";

		//调用方法实现写操作
		EasyExcel.write(fileName, UserData.class).sheet("用户信息")
				.doWrite(list);

	}
}
