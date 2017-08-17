package com.mazecode.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mazecode.config"})
@PropertySource(value = {"classpath:config.properties"})
public class HibernateConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public HibernateTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		try {
			tm.setSessionFactory(sessionFactory().getObject());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return tm;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IOException {
		LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
		lsf.setDataSource(dataSource());
		lsf.setPackagesToScan(new String[]{"com.mazecode.model"});
		lsf.setHibernateProperties(hibernateProperties());
//		 try {
//		 lsf.afterPropertiesSet();
//		 } catch (IOException e) {
//		 System.err.println(e.getMessage());
//		 }
		return lsf;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getRequiredProperty("javax.persistence.jdbc.driverClassName"));
		ds.setUrl(env.getRequiredProperty("javax.persistence.jdbc.url"));
		ds.setUsername(env.getRequiredProperty("javax.persistence.jdbc.user"));
		ds.setPassword(env.getRequiredProperty("javax.persistence.jdbc.password"));
		return ds;
	}
	
	@Bean
	public Properties hibernateProperties() {
		Properties p = new Properties();
		// Setting Hibernate properties
		p.put(DIALECT, env.getRequiredProperty("hibernate.dialect"));
		p.put(FORMAT_SQL, env.getRequiredProperty("hibernate.format_sql"));
		p.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
		p.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
		
		// Setting C3P0 properties
		p.put(C3P0_MIN_SIZE, env.getProperty("hibernate.c3p0.min_size"));
		p.put(C3P0_MAX_SIZE, env.getProperty("hibernate.c3p0.max_size"));
		p.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("hibernate.c3p0.acquire_increment"));
		p.put(C3P0_TIMEOUT, env.getProperty("hibernate.c3p0.timeout"));
		p.put(C3P0_MAX_STATEMENTS, env.getProperty("hibernate.c3p0.max_statements"));
		return p;
	}
}
