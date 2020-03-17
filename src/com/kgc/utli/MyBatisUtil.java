package com.kgc.utli;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisUtil {
	//单例模式
	private MyBatisUtil() {}
	
	private static SqlSessionFactory factory;
	
	//加载配置文件
	//InputStream in =Resources.getResourceAsStream("mybatis-config.xml");
	//创建sqlSessionFactory对象
	//SqlSessionFactory factory =new SqlSessionFactoryBuilder().build(in);
	//创建SqlSession对象
	//SqlSession session =factory.openSession();
	//创建到代理对象
	//StudentDao sdao = session.getMapper(StudentDao.class);
	
	
	static {
		
		try {
			//获取mybatis-config.xml的输入流
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//创建SqlSessionFactory对象，完成对配置文件的读取
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
	
	
}
	
