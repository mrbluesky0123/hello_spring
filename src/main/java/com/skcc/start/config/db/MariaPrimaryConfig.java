package com.skcc.start.config.db;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MariaPrimaryConfig {

    @Primary
    @Bean(name = "mariaPrimaryDataSource")
    public DataSource dataSource(@Value("${maria.primary.driverClassName}")  String driverClassName,
                                 @Value("${maria.primary.url}")  String url,
                                 @Value("${maria.primary.userName}")  String userName,
                                 @Value("${maria.primary.password}")  String password,
                                 @Value("${maria.primary.maxActive}")  int maxActive,
                                 @Value("${maria.primary.maxIdle}")  int maxIdle,
                                 @Value("${maria.primary.minIdle}")  int minIdle,
                                 @Value("${maria.primary.maxWait}")  int maxWait) {

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("select 1");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("mariaPrimaryDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        transactionManager.setGlobalRollbackOnParticipationFailure(false);
        return transactionManager;
    }

}