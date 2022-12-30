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
@MapperScan(value = {"com.spring.batch.mapper.mysql"},
        sqlSessionFactoryRef = "mysqlSqlSessionFactory")
@EnableTransactionManagement
@RequiredArgsConstructor
public class MysqlDataSourceConfig {

    private final ApplicationContext applicationContext;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.config-locations}")
    private String configLocations;

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.mysql.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory mysqlSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources( mapperLocations));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(configLocations));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    public DataSourceTransactionManager mysqlTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(mysqlDataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name = "mysqlSqlSessionTemplate")
    public SqlSessionTemplate mysqlSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSqlSessionFactory());
    }

    @Bean(name = "mysqlSqlSessionBatchTemplate")
    public SqlSessionTemplate mysqlSqlSessionBatchTemplate() throws Exception {
        return new SqlSessionTemplate(mysqlSqlSessionFactory(), ExecutorType.BATCH);
    }
}
