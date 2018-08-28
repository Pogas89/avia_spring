package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.UserTestData;
import com.epam.ivanou.avia.model.Crew;
import com.epam.ivanou.avia.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.epam.ivanou.avia.CrewTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populatePostgresDb.sql", config = @SqlConfig(encoding = "UTF-8"))
abstract public class CrewServiceTest {


    @Autowired
    private CrewService service;

    @Test
    public void create() throws Exception {
        Crew crew = service.create(new Crew(null, "Crew3", UserTestData.ADMIN));
        assertMatch(service.getAll(), CREW, CREW2, crew);
    }

    @Test(expected = Exception.class)
    public void duplicateLogin() throws Exception {
        service.create(new Crew(null, "Crew2", UserTestData.ADMIN));
    }

    @Test
    public void update() throws Exception {
        Crew update = new Crew(CREW);
        update.setName("asd");
        update.setUser(UserTestData.USER);
        service.update(update);
        assertMatch(update, service.get(CREW_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(CREW_ID);
        assertMatch(service.getAll(), CREW2);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(0);
    }

    @Test
    public void get() throws Exception {
        Crew crew = service.get(CREW_ID);
        assertMatch(crew, CREW);
    }

    @Test
    public void getAll() throws Exception {
        List<Crew> crews = service.getAll();
        assertMatch(crews, CREW, CREW2);
    }

}