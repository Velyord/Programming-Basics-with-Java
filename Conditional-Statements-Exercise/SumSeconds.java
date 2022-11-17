/*
Условие:
    Трима спортни състезатели финишират за някакъв брой секунди (между 1 и 50). Да се напише програма,
    която чете времената на състезателите в секунди, въведени от потребителя и пресмята сумарното им време
    във формат "минути:секунди". Секундите да се изведат с водеща нула (2  "02", 7  "07", 35  "35").
Примерен вход и изход:
    35
    45
    44
    -> 2:04
    22
    7
    34
    -> 1:03
    50
    50
    49
    -> 2:29
    14
    12
    10
    -> 0:36
 */
package SoftUni.Exer6;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SumSeconds {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int first = setValue(1, 50, "първия");
        int second = setValue(1, 50, "втория");
        int third = setValue(1, 50, "третия");

        String result = calculateTime(first, second, third);

        out.println(result);
    }

    private static String calculateTime(int first, int second, int third) {
        int sum = first + second + third;
        int minutes = sum / 60;
        int seconds = sum % 60;

        if (seconds < 10)
            return (minutes + ":0" + seconds);
        else
            return (minutes + ":" + seconds);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max, String thing) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        out.println("Въведете секунди на " + thing + " състезател:");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max, thing);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max, thing);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max, thing);
            }
        }
        return (T) value;
    }
}
