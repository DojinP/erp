package com.multi.erp;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring-config.xml"})
@WebAppConfiguration
public class DataSourceTest {
	@Autowired
	private DataSource ds;
	@Autowired
	private JdbcTemplate template;
	@Test
	public void test() {
		try {
			StopWatch stopwatch = new StopWatch();
			stopwatch.start();
			Connection con1 = ds.getConnection();
			Connection con2 = ds.getConnection();
			Connection con3 = ds.getConnection();
			Connection con4 = ds.getConnection();
			Connection con5 = ds.getConnection();
			Connection con6 = ds.getConnection();
			Connection con7 = ds.getConnection();
			Connection con8 = ds.getConnection();
			Connection con9 = ds.getConnection();
			Connection con10 = ds.getConnection();
			System.out.println(con1);
			System.out.println(con2);
			System.out.println(con3);
			System.out.println(con4);
			System.out.println(con5);
			System.out.println(con6);
			System.out.println(con7);
			System.out.println(con8);
			System.out.println(con9);
			System.out.println(con10);
			stopwatch.stop();
			System.out.println("=================================");
//			Connection con11 = ds.getConnection();
//			System.out.println(con11);
			System.out.println("ds==>" + ds);
			System.out.println("template==>" + template);
			System.out.println(stopwatch.prettyPrint()); 
		} catch (SQLException e) {
			e.printStackTrace();
		} // Connection Pool 에서 Connection 만들기
		
	}
}
