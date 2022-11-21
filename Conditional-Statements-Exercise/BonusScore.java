/*
Условие:
    Дадено е цяло число – начален брой точки. Върху него се начисляват бонус точки по правилата, описани по-долу.
    Да се напише програма, която пресмята бонус точките, които получава числото и общия брой точки (числото + бонуса).
    • Ако числото е до 100 включително, бонус точките са 5.
    • Ако числото е по-голямо от 100, бонус точките са 20% от числото.
    • Ако числото е по-голямо от 1000, бонус точките са 10% от числото.

    • Допълнителни бонус точки (начисляват се отделно от предходните):
        ◦ За четно число  + 1 т.
        ◦ За число, което завършва на 5  + 2 т.
Примерен вход и изход:
    20
    -> 6.0
    -> 26.0
    175
    -> 37.0
    -> 212.0
    2703
    -> 270.3
    -> 2973.3
    15875
    -> 1589.5
    -> 17464.5
 */
package SoftUni.Exer6;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class BonusScore {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int startPoints = setValue(0);

        double points = calculatePoints(startPoints);

        out.println(points - startPoints);
        out.println(points);
    }

    private static double calculatePoints(int startPoints) {
        double bonusPoints;
        double allPoints;

        if (startPoints <= 100)
            bonusPoints = 5;
        else if (startPoints > 1000)
            bonusPoints = startPoints * 0.1;
        else
            bonusPoints = startPoints * 0.2;

        if (startPoints % 2 == 0)
            bonusPoints += 1;
        if (startPoints % 10 == 5)
            bonusPoints += 2;

        allPoints = startPoints + bonusPoints;

        return allPoints;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min) {
        Object value;
        boolean isIntArgs = min instanceof Integer;

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min);
        }

        if (isIntArgs) {
            if ((int) value < (int) min) {
                out.printf("Моля въведете положително число");
                return setValue(min);
            }
        } else {
            if ((double) value < (double) min) {
                out.printf("Моля въведете положително число");
                return setValue(min);
            }
        }
        return (T) value;
    }
}
