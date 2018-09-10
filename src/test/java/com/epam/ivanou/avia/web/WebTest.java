package com.epam.ivanou.avia.web;

import com.epam.ivanou.avia.service.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml",
        "classpath:spring/spring-db.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
abstract public class WebTest {

    protected MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    WebApplicationContext webApplicationContext;

    @PostConstruct
    void postConstruct() { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();}
}
