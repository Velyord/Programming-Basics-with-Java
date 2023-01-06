/*
Условие:
    Да се напише програма, която проверява дали въведеното от потребителя число е
    в интервала [-100, 100] и е различно от 0 и извежда "Yes", ако отговаря на условията,
    или "No" ако е извън тях.
Примерен вход и изход:
    -25 -> Yes
    0 -> No
    25 -> Yes
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class NumberInRange {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double number = setValue();

        checkNumber(number);
    }

    private static void checkNumber(double number) {
        if (number < -100 || number > 100 || number == 0)
            out.println("No");
        else
            out.println("Yes");
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;
        // out.println("Въведете число:");

        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }

        return (T) value;
    }
}
