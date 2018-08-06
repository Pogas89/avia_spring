package com.epam.ivanou.avia;

import com.epam.ivanou.avia.repository.jdbc.JdbcUserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
    public static void main(String[] args) {
        try(ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext(
                "spring/spring-app.xml")){

            System.out.println(appCtx.getBeanDefinitionNames());
            JdbcUserRepository userRepository = appCtx.getBean(JdbcUserRepository.class);
        }
    }
}
