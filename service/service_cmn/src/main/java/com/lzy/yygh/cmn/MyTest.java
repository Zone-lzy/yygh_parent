package com.lzy.yygh.cmn;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyTest {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		map.put(null, null);
		map.put(null, null);
		map.put(null, 1);
		map.put("1", 1);
		map.put("2", 1);
		map.put("1", 2);
		HashSet<String> hashSet = new HashSet<>();
		hashSet.add(null);
		hashSet.add("2");
		hashSet.add("1");
		new TreeMap<>();
		System.out.println(map.toString());
		System.out.println(hashSet.toString());

		int[] arrInt = new int[]{1,2,3,4};
		List<Integer> arrList = Arrays.asList(1, 2, 3, 4);

		// 获取list的迭代器
		Iterator<Integer> iterator = arrList.iterator();

		// 只对List可以使用，并且可以有向前遍历的方法，而iterator没有，并且iterator还可以对set集合进行遍历
		ListIterator<Integer> iterator1 = (ListIterator<Integer>) arrList.iterator();
		boolean b = iterator1.hasPrevious();

		new Vector<String>();
		new CopyOnWriteArrayList<Integer>();

		Collections.synchronizedList(new ArrayList<>());

		Thread t = new Thread();
		// 设置当前线程为守护线程
		t.setDaemon(true);

	}
}
