package com.epam.ivanou.avia.service.jpa;

import com.epam.ivanou.avia.service.UserServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres","jpa"})
public class JpaUserServiceTest extends UserServiceTest {
}