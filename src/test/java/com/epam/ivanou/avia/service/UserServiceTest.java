package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.UserTestData;
import com.epam.ivanou.avia.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User user = service.create(UserTestData.USER_TO_SAVE);
        assertThat(user).isEqualToIgnoringGivenFields(UserTestData.USER_TO_SAVE,"id","roles");
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void get() throws Exception {
        User user = service.get(3);
        assertThat(user).isEqualToIgnoringGivenFields(UserTestData.ADMIN,"roles");
    }

    @Test
    public void getByLogin() throws Exception {
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = service.getAll();
        assertThat(users).
    }

}