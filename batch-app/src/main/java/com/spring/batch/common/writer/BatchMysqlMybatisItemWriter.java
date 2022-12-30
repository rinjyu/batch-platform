package com.spring.batch.common.writer;

import com.spring.batch.common.component.ApplicationContextHolder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;

public class BatchMysqlMybatisItemWriter <T> extends MyBatisBatchItemWriterBuilder<T> {

    @Override
    public MyBatisBatchItemWriter<T> build() {
        super.sqlSessionFactory(ApplicationContextHolder.getBean("mysqlSqlSessionFactory", SqlSessionFactory.class));
        super.sqlSessionTemplate(ApplicationContextHolder.getBean("mysqlSqlSessionBatchTemplate", SqlSessionTemplate.class));
        return super.build();
    }
}