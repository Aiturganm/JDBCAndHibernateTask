package peaksoft;

import peaksoft.model.User;
import peaksoft.service.UserServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Scanner scanner = new Scanner(System.in);
        UserServiceImpl userService = new UserServiceImpl();
        while(true){
            System.out.println("""
                    1. create table
                    2. save user
                    3. remove user by id
                    4. get all user
                    5. clean user table
                    6. drop user table
                    """);
            switch (scanner.nextInt()){
                case 1 -> {
                    userService.createUsersTable();
                }
                case 2 -> {
                    userService.saveUser("Aiturgan", "Maksatbek kyzy", (byte)19);
                    userService.saveUser("Aliya", "Kybatova", (byte)18);
                    userService.saveUser("Asel", "Babaeva", (byte)19);
                    userService.saveUser("Cholponay", "Kuttukbekova", (byte)19);
                }
                case 3 -> {
                    System.out.println("Enter the id:");
                    userService.removeUserById(scanner.nextInt());
                }
                case 4 -> {
                    List<User> allUsers = userService.getAllUsers();
                    for (User allUser : allUsers) {
                        System.out.println(allUser);
                    }
                }
                case 5 -> {
                    userService.cleanUsersTable();
                }
                case 6 -> {
                    userService.dropUsersTable();
                }
            }
        }
    }
}
