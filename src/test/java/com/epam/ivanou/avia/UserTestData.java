package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;

public class UserTestData {
    public static User USER = new User(3,"admin","admin","Dmitry","Ivanov","poshta@gmail.com", Role.values()[0]);
    public static User USER_TO_SAVE = new User(null,"admin3","admin3","Dmitry3","Ivanov3","poshta3@gmail.com", Role.values()[0]);

}
