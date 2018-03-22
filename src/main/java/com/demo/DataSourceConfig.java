package com.demo;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
//指定扫描的mapper接口所在的包
@MapperScan(basePackages = "com.demo", sqlSessionFactoryRef = "DBDataSqlSessionFactory")
public class DataSourceConfig {
	@Bean(name = "DBDataSource")	
	@ConfigurationProperties(prefix="spring.datasource") //告诉自动加载配置的属性
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "DBDataSqlSessionFactory")
	public SqlSessionFactory  sqlSessionFactory(@Qualifier("DBDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/dbMapper.xml"));
		return bean.getObject();
	}

	@Bean(name = "DBDataTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("DBDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "DBDataSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("DBDataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
