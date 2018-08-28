package com.epam.ivanou.avia.service.jdbc;

import com.epam.ivanou.avia.service.UserServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres","jdbc"})
public class JdbcUserServiceTest extends UserServiceTest {
}