/**
 * 
 */
package it.satelsrl.privacyback.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Maurizio Carlotti
 *
 */
@Configuration
@ComponentScan(basePackages = { "it.satelsrl.privacyback.dto" })
@EnableTransactionManagement
public class HibernateConfig {

	// Change the below based on the DBMS you choose
	// Utilizzo DB H2
//	private final static String DATABASE_URL = "jdbc:h2:tcp://localhost/~/privacy";
//	private final static String DATABASE_DRIVER = "org.h2.Driver";
//	private final static String DATABASE_DIALECT = "org.hibernate.dialect.H2Dialect";
//	private final static String DATABASE_USERNAME = "sa";
//	private final static String DATABASE_PASSWORD = "";
//	private final static int DATABASE_CONN_POOL_SIZE = 5;

	
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/privacy";
	private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
//	private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	private final static String DATABASE_USERNAME = "privacy";
	private final static String DATABASE_PASSWORD = "Privacy2000";
	private final static int DATABASE_CONN_POOL_SIZE = 5;

	// Datasource bean
	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		// impostare i parametri di accesso al database
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setConnectionProperties("serverTimezone=UTC");
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		dataSource.setInitialSize(DATABASE_CONN_POOL_SIZE);

		return dataSource;
	}

	// sessionFactory bean
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

		builder.addProperties(getHibernateProperties());
		builder.scanPackages("it.satelsrl.privacyback.dto");

		return builder.buildSessionFactory();
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");

//		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.hbm2ddl.auto", "create");
		
		return properties;

	}

	// transaction manager Bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;

	}
}
