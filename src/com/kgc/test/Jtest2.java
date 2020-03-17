package com.kgc.test;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.GradeDao;
import com.kgc.dao.Student2Dao;
import com.kgc.pojo.Grade;
import com.kgc.pojo.Student2;
import com.kgc.utli.MyBatisUtil;

public class Jtest2 {
	private static SqlSessionFactory factory;
	private SqlSession session;
	private Student2Dao studao;
	private GradeDao gdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = MyBatisUtil.getFactory();
	}

	@Before
	public void setUp() throws Exception {
		session = factory.openSession();	
		studao = session.getMapper(Student2Dao.class);
		gdao = session.getMapper(GradeDao.class);
	}
	//调用存储过程
	@Test
	public void demo() {
		Map<String,Object> map=new HashMap<>();
		map.put("p", 20);
		map.put("u", 0);
		//调用
		studao.demo(map);
		System.out.println(map.get("u"));
	}
	
	
	//一对多
	@Test
	public void select2() {
		List<Grade> list =gdao.select();
		System.out.println("size"+list.size());
		list.forEach(g->{
			System.out.println(g.getGradeName()+"\t"+g.getStu());
			Set<Student2> st=g.getStu();
			st.forEach(s->{
				System.out.println(s.getStudentName());
			});
		});
	}
	
	
	//多对一
	@Test
	public void select() {
		List<Student2> list=studao.select();
		System.out.println("size"+list.size());
		list.forEach(e->{
			System.out.println(e.getStudentNo()+"\t"+e.getStudentName()+"\t"+e.getBorndate()+"\t"+e.getGrade().getGradeName());
		});
	}
	//分页
	@Test
	public void select3() {
		Page<Student2> page=PageHelper.startPage(-1, 3,"studentno desc");
		
		studao.select();
		System.out.println("总记录数:"+page.getTotal());
		System.out.println("总页数:"+page.getPages());
		System.out.println("当前页码："+page.getPageNum());
		
		page.getResult().forEach(e->{
			System.out.println(e.getStudentNo()+"\t"+e.getStudentName()+"\t"+e.getBorndate());
		});
	}
	
	@Test
	public void select4() {
		PageHelper.startPage(9, 1,"studentno desc");
		
		List<Student2> list=studao.select();
		//Page<Student2> page = (Page) list;
		PageInfo<Student2> page=new PageInfo<>(list);
		System.out.println("总记录数:"+page.getTotal());
		System.out.println("总页数:"+page.getPages());
		System.out.println("当前页码："+page.getPageNum());
		int[] ps=page.getNavigatepageNums();
		for (int i : ps) {
			System.out.print(i+"\t");
		}
		System.out.println();
		list.forEach(e->{
			System.out.println(e.getStudentNo()+"\t"+e.getStudentName()+"\t"+e.getBorndate());
		});
	}
	
	@Test
	public void add() {
		Grade g=new Grade();
		g.setGradeName("销售部");
		
		
		Student2 stu=new Student2();
		stu.setStudentName("滴滴");
		stu.setSex("不详");
		stu.setBorndate(new Date());
		stu.setGrade(g);
		
		Student2 stu1=new Student2();
		stu1.setStudentName("哈利");
		stu1.setSex("不详");
		stu1.setBorndate(new Date());
		stu1.setGrade(g);
		
		gdao.insert(g);
		studao.insert(stu);
		studao.insert(stu1);
	}
	
	@After
	public void tearDown() throws Exception {
		//提交事务
		session.commit();
		session.close();
	}

}
