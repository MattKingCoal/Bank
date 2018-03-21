package com.match.bank.account;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.omg.PortableServer.POAPackage.ObjectNotActive;

import com.match.bank.user.BusinessUser;
import com.match.bank.user.User;

public class UserTest {

    @Test
    public void testToString() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(4321, "balls@hpe.com", "Mars"));
        users.add(new BusinessUser(3321, "deep@hpe.com", "Pluto", "Biomet"));
        Collections.sort(users);
        for (User u : users) {
            printUser(u);
            if (u instanceof BusinessUser) {
                System.out.println("Is a BusinessUser");
            } else {
                System.out.println("Is not a BusinessUser");
            }
        }
    }

    @Test
    public void objsAsParams() {
        takeObject(new ObjectNotActive().getClass());
        takeObject(ObjectNotActive.class);
    }

    @Test
    public void missingParenth() {
        System.out.println(getWord());
    }

    private static void printUser(User u) {
        System.out.println(u.toString());
    }

    private String getWord() {
        if (true)
            System.out.println("Wahoo");

        return "one";
    }

    private static void takeObject(Object o) {
        System.out.println("Object: " + o.toString());
    }
}
