package com.kgc.dao;

import java.util.List;

import com.kgc.pojo.Grade;

public interface GradeDao {

	public int insert(Grade grade);
	
	public List<Grade> select();
}
