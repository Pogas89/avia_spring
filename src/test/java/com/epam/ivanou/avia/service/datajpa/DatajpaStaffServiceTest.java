package com.epam.ivanou.avia.service.datajpa;

import com.epam.ivanou.avia.service.StaffServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "datajpa"})
public class DatajpaStaffServiceTest extends StaffServiceTest {
}