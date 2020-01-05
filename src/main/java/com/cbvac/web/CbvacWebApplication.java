package com.cbvac.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author : Tiger
 * @date : 2020-01-05 19:48
 */
@SpringBootApplication
public class CbvacWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbvacWebApplication.class, args);
    }

    /**
     * 初始化数据源
     * 1、创建properties文件
     * 2、创建DataSource
     * 3、创建template
     *
     * @return
     */

    @Bean
    @Primary
    @ConfigurationProperties("spring.masterdatasource")
    public DataSourceProperties masterDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource masterDatasource() {
        return masterDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public JdbcTemplate masterJdbcTemplate() {
        return new JdbcTemplate(masterDatasource());
    }

    @Bean
    @ConfigurationProperties("spring.slavedatasource")
    public DataSourceProperties slaveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource slaveDatasource() {
        return slaveDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean
    public JdbcTemplate slaveJdbcTemplate() {
        return new JdbcTemplate(slaveDatasource());
    }
}
