package com.inspur.auto;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.inspur.auto.dao")
@ComponentScan(basePackages = {"frm","com.inspur.auto"})
@ServletComponentScan("frm.filter")
@EnableElasticsearchRepositories(basePackages = "com.inspur.auto.repositories")
public class InspurApplication {

    public static void main(String[] args) {
        SpringApplication.run(InspurApplication.class, args);
    }

}
