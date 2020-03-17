package com.kgc.test;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.kgc.pojo.Student;
import com.kgc.utli.MyBatisUtil;

public class Test {
	
	public static void selectById(SqlSession session) {
		Student stu=session.selectOne("com.kgc.dao.StudentDao.selectByNo",1);
		System.out.println(stu.getStudentno()+"\t"+stu.getStudentname());
		
		session.close();
	}
	
	public static void selectAll(SqlSession session) {
		List<Student> list=session.selectList("com.kgc.dao.StudentDao.selectAll");
		//lambda表达式
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname());
		});
		
		session.close();
	}
	
	public static void insert(SqlSession session) {
		Student stu=new Student();
		stu.setStudentname("赵六");
		stu.setBorndate(new Date());
		
		int c=session.insert("com.kgc.dao.StudentDao.insert",stu);
		System.out.println(c);
		
		session.commit();
		session.close();
	}
	
	
	
	
	
	public static void main(String[] args) {
		SqlSessionFactory factory =MyBatisUtil.getFactory();
		SqlSession session=factory.openSession();
		
		//selectById(session);
		//selectAll(session);
		insert(session);
		
	}
}
