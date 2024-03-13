package dto;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static dto.UserInfoFileManager.createFile;

public class UserManager extends User {
    static final String RESET = "\u001b[0m";
    static final String RED = "\u001b[31m";
    public static List<User> listUser = new ArrayList<>();

    public UserManager(int id, String name, int age, String email, String address) {
        super(id, name, age, email, address);
    }

    public static boolean checkingForAvailability(int id) {
        List<User> list = listUser.stream().filter(x -> x.getId() == id).toList();
        return !list.isEmpty();
    }

    public static void create() {
        System.out.println("Введите данные пользователя через запятую с пробелом в следующем порядке: \n id, имя, возраст, почту, место проживания(город и далее без запятых)");
        Scanner scannerUser = new Scanner(System.in);
        String[] userData = scannerUser.nextLine().split(", ");
        int id = Integer.parseInt(userData[0]);
        User userNew = new User(id, userData[1], Integer.parseInt(userData[2]), userData[3], userData[4]);
        if (!checkingForAvailability(id)) {
            listUser.add(userNew);
            createFile(userNew);
            System.out.println("Пользователь с id = " + id + " создан");
        } else System.out.println(RED + "Пользователь с таким id = " + id + " уже создан" + RESET);
    }

    public static void update() {
        System.out.println("Введите id пользователя, данные которого нужно обновить:");
        Scanner scannerIdUpdate = new Scanner(System.in);
        int id = scannerIdUpdate.nextInt();
        System.out.println("Введите для обновления данные пользователя через запятую с пробелом в следующем порядке: \n имя, возраст, почту, место проживания(город и далее без запятых)");
        Scanner scannerElementUpdate = new Scanner(System.in);
        String stringElementUpdate = scannerElementUpdate.nextLine();
        String[] userDataUpdate = stringElementUpdate.split(", ");
        if (checkingForAvailability(id)) {
            for (User user : listUser) {
                if (user.getId() == id) {
                    user.setName(userDataUpdate[0]);
                    user.setAge(Integer.parseInt(userDataUpdate[1]));
                    user.setEmail(userDataUpdate[2]);
                    user.setAddress(userDataUpdate[3]);
                    System.out.println("Пользователь с id =" + id + " обновлен");
                }
            }
        } else System.out.println(RED + "Пользователь с id = " + id + " не найден" + RESET);
    }

    public static void get() {
        System.out.println("Введите id пользователя для вывода информации");
        Scanner scannerId = new Scanner(System.in);
        int id = scannerId.nextInt();
        List<User> list = listUser.stream().filter(x -> x.getId() == id).peek(System.out::println).toList();
        if (list.isEmpty()) System.out.println(RED + "Пользователь не найден" + RESET);
    }

    public static void delete() {
        System.out.println("Введите id пользователя, которого нужно удалить:");
        Scanner scannerIdDelete = new Scanner(System.in);
        int id = scannerIdDelete.nextInt();
        if (checkingForAvailability(id)) {
            listUser.removeIf(x -> x.getId() == id);
            System.out.println("Пользователь с id = " + id + " удален");
        } else System.out.println(RED + "Пользователь с id = " + id + " не найден" + RESET);
    }

    public static void sortToAddress() {
        listUser = listUser.stream().sorted(Comparator.comparing(User::getAddress)).toList();
    }

    public static void sortToName() {
        listUser = listUser.stream().sorted(Comparator.comparing(User::getName)).toList();
    }

    public static void info() {
        for (User user : listUser) {
            System.out.println(user);
        }
    }
}
