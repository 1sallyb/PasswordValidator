package passwordvalidator;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PasswordValidator {

    private static final String TEXT_FILE_NAME = "project123.txt";

    private static boolean checkPasswordRequirements(String password) {
        boolean isValid = true;
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            isValid = false;
        }
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            isValid = false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            isValid = false;
        }
        if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one digit.");
            isValid = false;
        }
        if (!password.matches(".*[!@#$%^&*()-_=+\\|\\[{\\]};:'\",<.>/?`~].*")) {
            System.out.println("Password must contain at least one special character.");
            isValid = false;
        }
        return isValid;
    }

    private static String checkCommonPasswords(String password) {
        try {
            File commonPasswordsFile = new File("common_password.txt");
            System.out.println("Looking for common passwords file.");
            try (Scanner fileScanner = new Scanner(commonPasswordsFile)) {
                while (fileScanner.hasNextLine()) {
                    String commonPassword = fileScanner.nextLine();
                    if (password.contains(commonPassword)) {
                        return commonPassword;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Common passwords file not found.");
        }
        return null;
    }

    private static void createTextFile() {
        try {
            File file = new File(TEXT_FILE_NAME);
            if (file.createNewFile()) {
                System.out.println("Text file created: " + file.getName());
            } else {
                System.out.println("Text file already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the text file.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean isValidPassword = false;
        createTextFile(); 

        while (!isValidPassword) {
            System.out.println("Enter your password (must be 8 characters, 1 upper, 1 lower, 1 special, and 1 number): ");
            String password = sc.nextLine();

            if (!checkPasswordRequirements(password)) {
                continue;
            }
            String commonPassword = checkCommonPasswords(password);
            if (commonPassword != null) {
                System.out.println("The part that makes the password common: \"" + commonPassword + "\". Choose a stronger one.");
                continue;
            }

            System.out.println("Password is valid.");
            isValidPassword = true;
        }
        sc.close();
    }
}
