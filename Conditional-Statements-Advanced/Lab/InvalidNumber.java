/*
Условие:
    Дадено число е валидно, ако е в диапазона [100…200] или е 0.
    Да се напише програма, която чете цяло число, въведено от потребителя, и печата "invalid" ако
    въведеното число не е валидно.
Примерен вход и изход:
    75 -> invalid
    150 -> (няма изход)
    220 -> invalid
    199 -> (няма изход)
    -1 -> invalid
    100 -> (няма изход)
    200 -> (няма изход)
    0 -> (няма изход)
 */
package SoftUni.Lab7;

import javax.swing.*;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class InvalidNumber {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue();

        checkIfValid(number);
    }

    private static void checkIfValid(int number) {
        if (!(number == 0) && (number < 100 || number > 200))
            out.println("invalid");
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;
        // out.println("Въведете число:");

        try {
            value = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }

        return (T) value;
    }
}
