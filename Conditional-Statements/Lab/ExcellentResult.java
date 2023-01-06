/*
Условие:
    Напише конзолна програма, която чете оценка (цяло число), въведена от потребителя и отпечатва "Excellent!" ако оценката е 5 или по-висока.
Примерен вход и изход:
    6
    -> Excellent!
    4
    -> (няма изход)
    5
    -> Excellent!
    3
    -> (няма изход)
 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class ExcellentResult {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int grade = setValue(2, 6);

        if (grade >= 5)
            out.println("Excellent!");
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }
        return (T) value;
    }
}
