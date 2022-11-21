/*
Условие:
    Да се напише програма, която чете час и минути от 24-часово денонощие, въведени от потребителя
    и изчислява колко ще е часът след 15 минути. Резултатът да се отпечата във формат часове:минути.
    Часовете винаги са между 0 и 23, а минутите винаги са между 0 и 59. Часовете се изписват с една или две цифри.
    Минутите се изписват винаги с по две цифри, с водеща нула, когато е необходимо.
Примерен вход и изход:
    1
    46
    -> 2:01
    0
    01
    -> 0:16
    23
    59
    -> 0:14
    11
    08
    -> 11:23
    12
    49
    -> 13:04
 */
package SoftUni.Exer6;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class TimePlus15Minutes {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int hours = setValue(0, 24);
        int mins = setValue(0, 60);

        calculateTime(hours, mins);
    }

    private static void calculateTime(int hours, int mins) {
        mins += 15;

        if (mins >= 60){
            mins -= 60;
            hours += 1;

            if (hours == 24)
                hours = 0;
        }

        if (mins < 10)
            out.printf("%d:0%d", hours, mins);
        else
            out.printf("%d:%d", hours, mins);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        Object value;

        try {
            value = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
    }
}
