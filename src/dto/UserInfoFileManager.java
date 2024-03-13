package dto;

import java.io.FileWriter;

public class UserInfoFileManager {
    public static final String fileName = "File.txt";

    public static void createFile(User user) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(user.toString() + ";\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
