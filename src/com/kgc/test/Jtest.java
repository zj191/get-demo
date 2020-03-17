package com.kgc.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.kgc.dao.StudentDao;
import com.kgc.pojo.Student;
import com.kgc.utli.MyBatisUtil;

public class Jtest {
	
	private static SqlSessionFactory factory;
	private SqlSession session;
	private StudentDao sdao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		factory = MyBatisUtil.getFactory();
	}

	@Before
	public void setUp() throws Exception {
		session = factory.openSession();	
		sdao = session.getMapper(StudentDao.class);
	}
	
	//根据id查
	@Test
	public void selectById() {
		Student stu=sdao.selectByNo(2);
		System.out.println(stu.getStudentno()+"\t"+stu.getStudentname());
		
	}
	//增
	@Test
	public void insert() {
		Student stu =new Student();
		stu.setStudentname("方文琳");
		stu.setBorndate(new Date());
		int i=sdao.insert(stu);
		System.out.println(i);
		System.out.println(stu.getStudentno());
		
	}
	
	//增
		@Test
		public void insertOracle() {
			Student stu =new Student();
			stu.setStudentno(9);
			stu.setStudentname("方文琳");
			stu.setBorndate(new Date());
			int i=sdao.insertOracle(stu);
			if(i>0) {
				System.out.println("新增成功");
			}
		}
	//删除
	@Test
	public void delete() {
		Student stu =new Student();
		stu.setStudentno(6);
		int i=sdao.delete(stu);
		if(i>0) {
			System.out.println("删除成功");
		}
		
	}
	//修改
	@Test
	public void update() {
		Student stu =new Student();
		stu.setStudentname("方文林");
		stu.setBorndate(new Date());
		stu.setStudentno(5);
		int i=sdao.update(stu);
		if(i>0) {
			System.out.println("修改成功");
		}
	}
	//查所有
	@Test
	public void selectAll() {
		List<Student> list=sdao.selectAll();
		//lambda表达式
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname()+"\t"+s.getBorndate());
		});
	}
	
	//返回map
	@Test
	public void selectMap() {
		List<Map<String,Object>> list=sdao.selectMap();
		//lambda表达式
		list.forEach(m->{
			System.out.println(m.get("studentno")+"\t"+m.get("studentname")+"\t"+m.get("borndate"));
		});
	}
	//map传参
	@Test
	public void selectlike() {
		Map<String,Object> param=new HashMap<>();
		param.put("no", 1);
		param.put("name", "方%");
		
		List<Student> list=sdao.selectlike(param);
		//lambda表达式
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname());
		});
	}
	//多参
	@Test
	public void selectlike2() {
		Student stu =new Student();
		stu.setStudentname("方%");
		List<Student> list=sdao.selectlike2(1,stu);
		//lambda表达式
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname());
		});
	}
	
	@Test
	public void select() {
		Map<String,Object> param=new HashMap<>();
		param.put("studentno", 1);
		param.put("studentname", "方");
		param.put("borndate", new Date());
		param.put("c", "studentno");
		param.put("b", "desc");
		param.put("of", 2);
		param.put("size", 2);
		
		List<Student> list=sdao.select(param);
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname());
		});
	}
	
	@Test
	public void select2() {
		Map<String,Object> param=new HashMap<>();
		/*param.put("studentno", 1);
		param.put("studentname", "方");
		param.put("borndate", new Date());*/
		
		List<Student> list=sdao.select2(param);
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname());
		});
	}
	
	@Test
	public void selectlist() {
		List<Integer> nos=Arrays.asList(1,2,3,4);
		/*List<Integer> nos=new ArrayList<>();
		nos.add(1);
		nos.add(2);
		nos.add(3);
		nos.add(4);*/
		
		List<Student> list =sdao.selectlist(nos);
		list.forEach(s->{
			System.out.println(s.getStudentno()+"\t"+s.getStudentname()+"\t"+s.getBorndate());
		});
		
	}
	
	@After
	public void tearDown() throws Exception { 
		//提交事务
		session.commit();
		session.close();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}