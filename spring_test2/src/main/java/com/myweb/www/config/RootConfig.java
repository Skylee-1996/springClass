package com.myweb.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@EnableScheduling
@MapperScan(basePackages = {"com.myweb.www.repository"})
@ComponentScan(basePackages = {"com.myweb.www.service"})
@Configuration
@EnableTransactionManagement
public class RootConfig {
	//DB설정부분
	//hikariCP사용 / log4jdbc-log4j2 사용
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		//log4jdbc-log4j2
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/mywebdb");
		hikariConfig.setUsername("mywebUser");
		hikariConfig.setPassword("mysql");
		
		hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수(위의 값과 같은 값으로 설정)
		
		hikariConfig.setConnectionTestQuery("SELECT now()"); //test 쿼리문
		hikariConfig.setPoolName("springHikariCP");
		
		//추가 설정
		//cachePrepStmts : cache 사용 여부 설정
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		//mysql 드라이버가 연결당 cache statement의 수에 관한 설정 250~500 사이 권장 
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		//connection 당 캐싱할 최대 개수
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		//mysql 서버에서 최신 이슈가 있을 경우 지원받는 설정
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/*.xml"));
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		return sqlFactoryBean.getObject();
	}
	
	//트렌젝션 매니저 빈 설정
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
