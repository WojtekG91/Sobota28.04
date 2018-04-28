package com.company;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class Login {
    public static void login(){
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("Login: ");
        String login = scanner.nextLine();
        System.out.println("Password: ");
        String password = DigestUtils.md5Hex(scanner.nextLine()).toUpperCase();

        if(DBConnector.authenticate(login,password)){
            System.out.println("Zalogowano");
        }else {
            System.out.println("Error");
        }
    }
}
