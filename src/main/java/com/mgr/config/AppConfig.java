package com.mgr.config;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import oracle.jdbc.pool.OracleDataSource;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Klasa konfigurujaca aplikacje. Zawiera wskazanie na plik z propertisami oraz
 * odpowiednie Beany
 *
 * @author michal
 */
@Configuration
@ComponentScan({"com.mgr.common"})
@PropertySource({"file:${cfg.path:config/}app.properties"})
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);
    @Value("${db.serverName}")
    private String serverName;
    @Value("${db.portNumber}")
    private int portNumber;
    @Value("${db.name}")
    private String name;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private String showSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    /**
     * Obiekt pomagajacy we wstrzykiwaniu wartosci propertisow w odpowiednie
     * pola
     *
     * @return
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Rejest metryk
     *
     * @return
     */
    @Bean
    public static MetricRegistry metricRegistry() {
        return new MetricRegistry();
    }

    /**
     * Mapper z JSONa na obiekty javowe
     *
     * @return
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * Obiekt mapujacy z formatu XML na klasy javowe za pomoca pakietu Jaxb
     *
     * @return
     */
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.mgr.rtdm.domain");
        return marshaller;
    }

    /**
     * Fabryka sesji Hibernate
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory
                .setPackagesToScan(new String[]{"com.mgr.common.scenario.data"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    /**
     * Polaczenie do bazy danych
     *
     * @return
     */
    @Bean
    public DataSource dataSource() {

        OracleDataSource ods;
        try {
            ods = new OracleDataSource();
            ods.setDriverType("thin");
            ods.setServerName(serverName);
            ods.setPortNumber(portNumber);
            ods.setDatabaseName(name);
            ods.setUser(user);
            ods.setPassword(password);
            return ods;
        } catch (SQLException e) {
            LOG.error("Blad przy tworzeniu polaczenia z baza");
            LOG.error(e.toString());
            return null;
        }
    }

    /**
     * Manager tranzakcji
     *
     * @param sessionFactory
     * @return
     */
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);

        return txManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
                setProperty("hibernate.dialect", dialect);
                setProperty("hibernate.globally_quoted_identifiers", "true");
                setProperty("hibernate.show_sql", showSql);
                setProperty("hibernate.current_session_context_class", "thread");
            }
        };
    }

}
