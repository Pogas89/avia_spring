package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.epam.ivanou.avia.UserTestData.*;

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
        User newUser = new User(null,  "newUser@gmail.com","newUser", "newUser", "newUser", Role.ROLE_ADMIN);
        service.create(newUser);
        assertMatch(service.getAll(), ADMIN,newUser,USER);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateLogin() throws Exception {
        service.create(new User(null,  "admin@gmail.com", "newUser", "newUser", "newUser", Role.ROLE_ADMIN));
    }

    @Test
    public void update() throws Exception {
        User updated = new User(USER);
        updated.setEmail("asd@ya.ru");
        updated.setPassword("1111");
        updated.setFirstname("adac");
        updated.setLastname("dacas");
        service.update(updated);
        User need = service.get(USER_ID);
        assertMatch(updated, need);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_ID);
        assertMatch(service.getAll(),ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(1);
        assertMatch(service.getAll(),ADMIN);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(ADMIN_ID);
        assertMatch(user,ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
       service.get(0);
    }

    @Test
    public void getByLogin() throws Exception {
        User user = service.getByLogin(ADMIN.getEmail());
        assertMatch(user,ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFoundByLogin() throws Exception {
        service.getByLogin("empty");
    }

    @Test
    public void getAll() throws Exception {
        List<User> users = service.getAll();
        assertMatch(users, ADMIN,USER);
    }

}