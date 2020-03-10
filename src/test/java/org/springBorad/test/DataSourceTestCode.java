package org.springBorad.test;

import java.sql.Connection;

import javax.sql.DataSource;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) 
//junit에 내장된 기본 테스트러너대신 지정된 클래를 이용해 테스트메소드를 수행.

@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//애플리케이션 자신이 가지고있는 자원을 참조하는 경로를 설정.
public class DataSourceTestCode {
	
	@Inject
	private DataSource ds;
	
	@Test
	public void textconection()throws Exception{
		
		try(Connection con = ds.getConnection()){
			
			System.out.println(con);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
