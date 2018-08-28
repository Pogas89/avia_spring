package com.epam.ivanou.avia.service.jpa;

import com.epam.ivanou.avia.service.StaffServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres", "jpa"})
public class JpaStaffServiceTest extends StaffServiceTest {
}