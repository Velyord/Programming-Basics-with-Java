/*
Условие:
    Да се напише програма, която чете парола (текст), въведена от потребителя и проверява дали въведената парола съвпада с фразата "s3cr3t!P@ssw0rd". 
    При съвпадение да се изведе "Welcome". При несъвпадение да се изведе "Wrong password!".
Примерен вход и изход:
    qwerty
    -> Wrong password!
    s3cr3t!P@ssw0rd
    -> Welcome
    s3cr3t!p@ss
    -> Wrong password!

 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class PasswordGuess {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String password = scanner.nextLine();

        String isCorrect = checkPassword(password);

        out.println(isCorrect);
    }

    private static String checkPassword(String password) {
        if (password.equals("s3cr3t!P@ssw0rd"))
            return "Welcome";
        else
            return "Wrong password!";
    }
}
