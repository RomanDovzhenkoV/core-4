package dto;

import java.util.Scanner;

import static dto.UserManager.*;


public class Main {
    static final String YELLOW = "\u001b[33m";
    static final String RESET = "\u001b[0m";
    static final String BLUE = "\u001b[34m";
    static final String RED = "\u001b[31m";

    public static void main(String[] args) {

        while (true) {
            System.out.println(YELLOW + "Возможны следующие операции: create, get user, get info, delete, update, sort by name, sort by address , stop");
            System.out.println(BLUE + "Введите операцию:" + RESET);
            Scanner scanner = new Scanner(System.in);
            String operation = scanner.nextLine().trim();
            try {
                switch (operation) {
                    case "create":
                        create();
                        break;

                    case "get user":
                        get();
                        break;

                    case "update":
                        update();
                        break;

                    case "delete":
                        delete();
                        break;

                    case "get info":
                        info();
                        break;

                    case "sort by name":
                        sortToName();
                        break;

                    case "sort by address":
                        sortToAddress();
                        break;

                    case "stop":
                        System.out.println("Завершение программы");
                        return;

                    default:
                        System.out.println(RED + "Введена не корректная операция, попробуйте снова" + RESET);
                }
            } catch (Exception exception) {
                System.out.println(RED + "Введенные данные введены не корректно, попробуйте снова" + RESET);
            }
        }
    }
}
