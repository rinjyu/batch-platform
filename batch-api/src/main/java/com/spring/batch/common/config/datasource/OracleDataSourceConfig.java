package com.spring.batch.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = {"com.spring.batch.mapper.oracle"},
        sqlSessionFactoryRef = "oracleSqlSessionFactory")
@EnableTransactionManagement
@RequiredArgsConstructor
public class OracleDataSourceConfig {

    private final ApplicationContext applicationContext;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.config-locations}")
    private String configLocations;

    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "spring.oracle.datasource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory oracleSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(oracleDataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources( mapperLocations));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(configLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "oracleTransactionManager")
    public DataSourceTransactionManager oracleTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(oracleDataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name = "oracleSqlSessionTemplate")
    public SqlSessionTemplate oracleSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(oracleSqlSessionFactory());
    }

    @Bean(name = "oracleSqlSessionBatchTemplate")
    public SqlSessionTemplate oracleSqlSessionBatchTemplate() throws Exception {
        return new SqlSessionTemplate(oracleSqlSessionFactory(), ExecutorType.BATCH);
    }
}
