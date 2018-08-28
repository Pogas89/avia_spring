package com.epam.ivanou.avia.service.jdbc;

import com.epam.ivanou.avia.service.CrewServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres","jdbc"})
public class JdbcCrewServiceTest extends CrewServiceTest {
}