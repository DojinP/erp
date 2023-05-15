package com.multi.erp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

// 단위테스트를 Spring과 연동하기
//@RunWith 는 단위테스트의 실행방법을 확장할 때 사용하는 어노테이션
// => ()안에 정의하는 클래스를 실행해서 단위테스트
// => Spring과 연동하여 단위테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/config/spring-config.xml"})
@WebAppConfiguration
public class SpringJUnitTest01 {
	@Autowired
	DataSource ds;
	
	@Test
	public void test() {
		assertNotNull(ds);
	}

}
