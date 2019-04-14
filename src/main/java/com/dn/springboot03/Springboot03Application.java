package com.dn.springboot03;

import com.dn.springboot03.servlet.Myservlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.dn.springboot03.mapper")
public class Springboot03Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Springboot03Application.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

    /**
     * 注册servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        return new ServletRegistrationBean(new Myservlet(),"/servlet/*");
    }

}
