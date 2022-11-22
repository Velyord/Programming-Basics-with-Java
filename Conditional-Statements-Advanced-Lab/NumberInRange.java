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
