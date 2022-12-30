package com.spring.batch.common.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class BatchManageDataSourceConfig {

    @Primary
    @Bean(name = "batchManageDataSource")
    @ConfigurationProperties(prefix = "spring.batch-manage.datasource")
    public DataSource batchManageDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "batchManageSqlSessionFactory")
    public SqlSessionFactory batchManageSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(batchManageDataSource());
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "batchManageTransactionManager")
    public DataSourceTransactionManager batchManageTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(batchManageDataSource());
        return dataSourceTransactionManager;
    }

    @Primary
    @Bean(name = "batchManageSqlSessionTemplate")
    public SqlSessionTemplate batchManageSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(batchManageSqlSessionFactory());
    }
}
