package com.kgc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kgc.pojo.Student;

public interface StudentDao {
	
	public int insert(Student stu);
	
	public int insertOracle(Student stu);
	
	public int update(Student stu);
	
	public int delete(Student stu);
	
	public Student selectByNo(int studentno);
	
	public List<Student> selectAll();
	
	//返回map
	public List<Map<String,Object>> selectMap();
	
	//map传参
	public List<Student> selectlike(Map<String,Object> param);
	
	//多参
	public List<Student> selectlike2(@Param("no") int no,
									@Param("stu") Student stu);
	
	public List<Student> select(Map<String,Object> param);
	
	public List<Student> select2(Map<String,Object> param);
	
	public List<Student> selectlist(List<Integer> list);
	
	
}
