package com.saber.spring_boot_web_demo.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.AllArgsConstructor;
import org.apache.camel.component.sql.SqlComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {

    private final DataSource hikariDateSource;
    private final EntityManagerFactory entityManagerFactory;

    @Bean
    public SqlComponent sql() {
        SqlComponent sqlComponent = new SqlComponent();
        sqlComponent.setDataSource(hikariDateSource);
        sqlComponent.setUsePlaceholder(true);
        return sqlComponent;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
