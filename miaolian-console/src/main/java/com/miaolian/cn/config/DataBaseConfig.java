package com.miaolian.cn.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Properties;

@Component
public class DataBaseConfig {

    @Autowired
    private YmlConfig ymlConfig;

    @Bean(destroyMethod = "close")
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(ymlConfig.getDatasource().get("url"));
        dataSource.setUsername(ymlConfig.getDatasource().get("username"));
        dataSource.setPassword(ymlConfig.getDatasource().get("password"));
        dataSource.setDriverClassName(ymlConfig.getDatasource().get("driver-class-name"));
        dataSource.setMaxActive(20);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxWait(60000);
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //sessionFactory.setConfigLocation(new ClassPathResource("/mybatis.xml"));
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }


    @Bean
    public SqlSessionTemplate SqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(getDataSource()));
    }
}
