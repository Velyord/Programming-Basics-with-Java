/*
Условие:
    Да се напише програма, която чете скорост (реално число), въведена от потребителя и отпечатва информация за скоростта.
    • При скорост до 10 (включително) отпечатайте "slow"
    • При скорост над 10 и до 50 (включително) отпечатайте "average"
    • При скорост над 50 и до 150 (включително) отпечатайте "fast"
    • При скорост над 150 и до 1000 (включително) отпечатайте "ultra fast"
    • При по-висока скорост отпечатайте "extremely fast"
Примерен вход и изход:
    8
    -> slow
    49.5
    -> average
    126
    -> fast
    160
    -> ultra fast
    3500
    -> extremely fast
 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SpeedInfo {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double speed = setValue();

        String speedMeter = checkSpeed(speed);

        out.println(speedMeter);
    }

    private static String checkSpeed(double speed) {
        if (speed <= 10) 
            return "slow";
        else if (speed <= 50) 
            return "average";
        else if (speed <= 150) 
            return "fast";
        else if (speed <= 1000) 
            return "ultra fast";
        else 
            return "extremely fast";
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;

        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }

        return (T) value;
    }
}
