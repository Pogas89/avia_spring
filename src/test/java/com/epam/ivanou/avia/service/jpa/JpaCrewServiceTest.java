package com.epam.ivanou.avia.service.jpa;

import com.epam.ivanou.avia.service.CrewServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jpa"})
public class JpaCrewServiceTest extends CrewServiceTest {
}