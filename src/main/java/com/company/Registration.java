package com.company;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class Registration {
    static void registration(){
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.println("Password: ");
        user.setPassword(DigestUtils.md5Hex(scanner.nextLine()).toUpperCase());
        System.out.println("Name: ");
        user.setName(scanner.nextLine());
        System.out.println("Surname: ");
        user.setSurname(scanner.nextLine());

        DBConnector.connect();
        DBConnector.insertUser(user);
    }
}
