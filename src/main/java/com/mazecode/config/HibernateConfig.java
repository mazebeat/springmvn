package com.mazecode.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.mazecode.config"})
@PropertySource(value = {"classpath:config.properties"})
public class HibernateConfig {
	@Autowired
	private Environment env;
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		try {
			return new HibernateTemplate(sessionFactory());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
		lsf.setDataSource(dataSource());
		lsf.setPackagesToScan(new String[]{"com.mazecode.model"});
		lsf.setHibernateProperties(hibernateProperties());
		try {
			lsf.afterPropertiesSet();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return lsf.getObject();
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
		p.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		p.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		p.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		p.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//		p.put("hibernate.initialSize", env.getRequiredProperty("hibernate.initialSize"));
//		p.put("hibernate.maxActive", env.getRequiredProperty("hibernate.maxActive"));
//		p.put("hibernate.maxWait", env.getRequiredProperty("hibernate.maxWait"));
//		p.put("initialSize", "10");
//		p.put("maxActive", "5");
//		p.put("maxWait", "5000");
		return p;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory s) {
		HibernateTransactionManager tm = new HibernateTransactionManager();
		tm.setSessionFactory(s);
		return tm;
	}
}
