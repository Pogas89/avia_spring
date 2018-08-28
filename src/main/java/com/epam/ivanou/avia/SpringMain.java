package com.epam.ivanou.avia;

import com.epam.ivanou.avia.repository.jdbc.JdbcUserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try(GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()){
            appCtx.getEnvironment().setActiveProfiles("postgres","datajpa");
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();
            System.out.println(Arrays.toString(appCtx.getBeanDefinitionNames()));
        }
    }
}
