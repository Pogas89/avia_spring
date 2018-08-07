package com.epam.ivanou.avia;

import com.epam.ivanou.avia.model.Role;
import com.epam.ivanou.avia.model.User;

public class UserTestData {
    public static User ADMIN = new User(3,"admin","admin","Dmitry","Ivanov","poshta@gmail.com", Role.values()[0]);
    public static User USER = new User(4,"user1","user","Ostap","Bender","bender@gmail.com", Role.values()[1]);

}
