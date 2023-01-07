/*
Условие:
    Напишете програма, която първоначално прочита име и парола на потребителски профил. След това чете парола за вход.
    •	при въвеждане на грешна парола: потребителя да се подкани да въведе нова парола.
    •	при въвеждане на правилна парола: отпечатваме "Welcome {username}!".
Примерен вход и изход:
    Nakov
    1234
    pass
    1324
    1234
    -> Welcome Nakov!
    Gosho
    secret
    secret
    -> Welcome Gosho!
 */
package SoftUni.Lab11;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Password {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        String passwordAttempt = scanner.nextLine();
        
        checkPassword(passwordAttempt, password);
        displayWelcome(username);
    }

    private static void displayWelcome(String username) {
        out.printf("Welcome %s!", username);
    }

    private static void checkPassword(String passwordAttempt, String password) {
        while (!passwordAttempt.equals(password))
            passwordAttempt = scanner.nextLine();
    }
}
