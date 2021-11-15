package com.lzy.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;

public class TestRead {
	public static void main(String[] args) {
		 // 读取文件路径
		String fileName = "D:\\excel\\01.xlsx";

		// 调用方法实现读取操作
		EasyExcel.read(fileName, UserData.class, new ExcelListener()).sheet().doRead();
	}
}
