package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;

@SpringBootApplication
@ComponentScan({"org.example"})
public class Application {
    public static void main(String[]args){
        SpringApplication.run(Application.class,args);
    }
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {

        return DataSourceBuilder.create().build();
    }
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/vistas/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
