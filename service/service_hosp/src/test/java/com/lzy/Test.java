package com.lzy;

import lombok.extern.slf4j.Slf4j;


public class Test {
	public static void main(String[] args) {
		try {
			Class.forName("com.lzy.yygh.hosp.service.impl.ScheduleServiceImp");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
