package com.kgc.dao;

import java.util.List;
import java.util.Map;

import com.kgc.pojo.Student2;

public interface Student2Dao {
	
	public int insert(Student2 stu);
	
	public List<Student2> select();
	
	public void demo(Map<String,Object> map);
}
