package com.epam.ivanou.avia.service;

import com.epam.ivanou.avia.model.Department;
import com.epam.ivanou.avia.model.Staff;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.epam.ivanou.avia.StaffTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class StaffServiceTest {

    @Autowired
    private StaffService service;

    @Test
    public void create() throws Exception {
        Staff newStaff = new Staff("Abram", "Abramov", Department.ATTENDANT);
        service.create(newStaff);
        assertMatch(service.getAll(), newStaff, STAFF2, STAFF1);
    }

    @Test
    public void update() throws Exception {
        Staff staff = new Staff(STAFF1);
        staff.setFirstName("AAA");
        staff.setLastName("BBB");
        staff.setDepartment(Department.ATTENDANT);
        service.update(staff);
        assertMatch(staff, service.get(STAFF1_ID));
    }

    @Test
    public void delete() throws Exception {
        service.delete(STAFF1_ID);
        assertMatch(service.getAll(), STAFF2);
    }

    @Test(expected = Exception.class)
    public void getNotFound() throws Exception {
        service.get(0);
    }

    @Test
    public void get() throws Exception {
        Staff staff = service.get(STAFF1_ID);
        assertMatch(staff, STAFF1);
    }

    @Test(expected = Exception.class)
    public void getByLastnameNotFound() throws Exception {
        service.getByLastname("ASda");
    }

    @Test
    public void getByLastname() throws Exception {
        Staff staff = service.getByLastname(STAFF1.getLastName());
        assertMatch(staff, STAFF1);
    }

    @Test
    public void getAll() throws Exception {
        List<Staff> staff = service.getAll();
        assertMatch(staff, STAFF2, STAFF1);
    }

}