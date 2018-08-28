package com.epam.ivanou.avia.service.datajpa;

import com.epam.ivanou.avia.service.CrewServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres","datajpa"})
public class DatajpaCrewServiceTest extends CrewServiceTest {
}