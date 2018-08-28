package com.epam.ivanou.avia.service.jdbc;

import com.epam.ivanou.avia.service.StaffServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jdbc"})
public class JdbcStaffServiceTest extends StaffServiceTest {
}