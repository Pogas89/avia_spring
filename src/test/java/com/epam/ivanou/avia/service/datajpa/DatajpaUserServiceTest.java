package com.epam.ivanou.avia.service.datajpa;

import com.epam.ivanou.avia.service.UserServiceTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"postgres","datajpa"})
public class DatajpaUserServiceTest extends UserServiceTest {
}