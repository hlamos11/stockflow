package com.wifi.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Properties;

@SpringBootApplication()
public class AppApplication  {

    public static void main(String[] args) {

       SpringApplication.run(AppApplication.class, args);

/*

       SpringApplication app = new SpringApplication(AppApplication.class);


        final Properties properties = new Properties();
        properties.putIfAbsent("server.address", "10.230.40.122");
        properties.putIfAbsent("server.port", "2794");
        properties.putIfAbsent("spring.datasource.url", "jdbc:mariadb://10.230.40.123:3306/wifipro?useSSL=false");
        properties.putIfAbsent("spring.datasource.username", "opwifi");
        properties.putIfAbsent("spring.datasource.password", "Cqb73OndqW!f!");
        properties.putIfAbsent("spring.datasource.driver-class-name","org.mariadb.jdbc.Driver");
//		properties.putIfAbsent("spring.jpa.hibernate.ddl-auto","update");
        properties.putIfAbsent("spring.jpa.show-sql","false");
        properties.putIfAbsent("spring.jpa.properties.hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        properties.putIfAbsent("spring.thymeleaf.mode","HTML5");
        properties.putIfAbsent("spring.thymeleaf.encoding","UTF-8");
        properties.putIfAbsent("spring.thymeleaf.cache","false");
        properties.putIfAbsent("spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults","false");



        app.setDefaultProperties(properties);
        app.run(args);
*/


    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

}
