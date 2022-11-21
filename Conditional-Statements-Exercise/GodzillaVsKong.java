/*
Условие:
    Снимките за дългоочаквания филм "Годзила срещу Конг" започват.
    Сценаристът Адам Уингард ви моли да напишете програма,
    която да изчисли дали предвидените средства са достатъчни за снимането на филма.
    За снимките  ще бъдат нужни определен брой статисти, облекло за всеки един статист и декор.
        Известно е, че:
            ◦ Декорът за филма е на стойност 10% от бюджета.
            ◦ При повече от 150 статиста,  има отстъпка за облеклото на стойност 10%.
Вход
    От конзолата се четат 3 реда:
        Ред 1. Бюджет за филма – реално число в интервала [1.00 … 1000000.00]
        Ред 2. Брой на статистите – цяло число в интервала [1 … 500]
        Ред 3. Цена за облекло на един статист – реално число в интервала [1.00 … 1000.00]
Изход
    На конзолата трябва да се отпечатат два реда:
        • Ако  парите за декора и дрехите са повече от бюджета:
            ◦ "Not enough money!"
            ◦ "Wingard needs {парите недостигащи за филма} leva more."
        • Ако парите за декора и дрехите са по малко или равни на бюджета:
            ◦ "Action!"
            ◦ "Wingard starts filming with {останалите пари} leva left."
    Резултатът трябва да е форматиран до втория знак след десетичната запетая.
Примерен вход и изход:
    20000
    120
    55.5
    -> Action!
    -> Wingard starts filming with 11340.00 leva left.
        Сума за декор: 10% от 20000 = 2000 лв.
        Сума за облекло: 120 * 55.5 = 6660 лв.
        Обща сума за филма: 2000 + 6660 = 8660 лв.
        20000 – 8660 = 11340 лева остават.
    15437.62
    186
    57.99
    -> Action!
    -> Wingard starts filming with 4186.33 leva left.
        Сума за декор: 10% от 15437.62 = 1543.762 лв.
        Сума за облекло: 186 * 57.99 = 10786.14 лв.
        Статистите са повече от 150 следователно има 10% отстъпка на облеклото.
        10% от 10786.14 е 1078.614
        10786.14 – 1078.614 = 9707.526 лв. за облекло
        Обща сума за филма: 1543.762 + 9707.526 = 11251.288
        15437.62 – 11251.288 = 4186.331 лева остават
    9587.88
    222
    55.68
    -> Not enough money!
    -> Wingard needs 2495.77 leva more.
        Сума за декор: 10% от 9587.88 = 958.788 лв.
        Сума за облекло: 11124.864 лв.
        Обща сума за филма: 958.788 + 11124.864 = 12083.652
        9587.88 – 12083.652 = 2495.77 лева не достигат
 */
package SoftUni.Exer6;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class GodzillaVsKong {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double movieBudget = setValue(1.0, 1000000.0);
        int numberOfStatists = setValue(1, 500);
        double priceOfCloths = setValue(1.0, 1000.0);

        calculateIfBudgetEnough(movieBudget, numberOfStatists, priceOfCloths);
    }

    private static void calculateIfBudgetEnough(double movieBudget, int numberOfStatists, double priceOfCloths) {
        double decor = movieBudget * 0.1;
        double discountPercentage = 10;
        double priceForCloths = priceOfCloths * numberOfStatists;

        if (numberOfStatists > 150)
            priceForCloths -= priceForCloths * discountPercentage / 100;

        movieBudget -= decor;

        double moneyAfterMovie = movieBudget - priceForCloths;

        if (priceForCloths > movieBudget) {
            out.println("Not enough money!");
            out.printf("Wingard needs %.2f leva more.", abs(moneyAfterMovie));
        } else {
            out.println("Action!");
            out.printf("Wingard starts filming with %.2f leva left.", abs(moneyAfterMovie));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        Object value;

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
