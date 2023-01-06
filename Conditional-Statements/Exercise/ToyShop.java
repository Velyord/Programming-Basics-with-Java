/*
Условие:
    Петя има магазин за детски играчки.
    Тя получава голяма поръчка, която трябва да изпълни. С парите, които ще спечели иска да отиде на екскурзия.
    Цени на играчките:
        • Пъзел - 2.60 лв.
        • Говореща кукла - 3 лв.
        • Плюшено мече - 4.10 лв.
        • Миньон - 8.20 лв.
        • Камионче - 2 лв.
    Ако поръчаните играчки са 50 или повече магазинът прави отстъпка 25% от общата цена.
    От спечелените пари Петя трябва да даде 10% за наема на магазина.
    Да се пресметне дали парите ще ѝ стигнат да отиде на екскурзия.
Вход
    От конзолата се четат 6 реда:
    1. Цена на екскурзията - реално число в интервала [1.00 … 10000.00]
    2. Брой пъзели - цяло число в интервала [0… 1000]
    3. Брой говорещи кукли - цяло число в интервала [0 … 1000]
    4. Брой плюшени мечета - цяло число в интервала [0 … 1000]
    5. Брой миньони - цяло число в интервала [0 … 1000]
    6. Брой камиончета - цяло число в интервала [0 … 1000]
Изход
    На конзолата се отпечатва:
    • Ако парите са достатъчни се отпечатва:
        ◦ "Yes! {оставащите пари} lv left."
    • Ако парите НЕ са достатъчни се отпечатва:
        ◦ "Not enough money! {недостигащите пари} lv needed."
    Резултатът трябва да се форматира до втория знак след десетичната запетая.
Примерен вход и изход:
    40.8
    20
    25
    30
    50
    10
    -> Yes! 418.20 lv left.
        Сума: 20 * 2.60 + 25 * 3 + 30 * 4.10 + 50 * 8.20 + 10 * 2 = 680 лв.
        Брой на играчките: 20 + 25 + 30 + 50 + 10 = 135
        135 > 50 => 25% отстъпка; 25% от 680 = 170 лв. отстъпка
        Крайна цена: 680 – 170 = 510 лв.
        Наем: 10% от 510 лв. = 51 лв.
        Печалба: 510 – 51 = 459 лв.
        459 > 40.8 => 459 – 40.8 = 418.20 лв. остават
    320
    8
    2
    5
    5
    1
    -> Not enough money! 238.73 lv needed.
        Сума: 8 * 2.60 + 2 * 3 + 5 * 4.10 + 5 * 8.20 + 1 * 2 = 90.3 лв.
        Брой на играчките: 8 + 2 + 5 + 5 + 1 = 21
        21 < 50 => няма отстъпка
        Наем: 10% от 90.3 = 9.03 лв.
        Печалба: 90.3 – 9.03 = 81.27 лв.
        81.27 < 320 => 320 – 81.27 = 238.73 лв. не достигат
 */
package SoftUni.Exer6;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ToyShop {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double excursionPrice = setValue(1.0, 10000.0);

        int puzzle = setValue(0, 1000);
        int talkingDoll = setValue(0, 1000);
        int teddyBear = setValue(0, 1000);
        int minion = setValue(0, 1000);
        int truck = setValue(0, 1000);

        calculateIfEnoughMoney(excursionPrice, puzzle, talkingDoll, teddyBear, minion, truck);
    }

    private static void calculateIfEnoughMoney(double excursionPrice, int puzzle, int talkingDoll, int teddyBear, int minion, int truck) {
        double priceOfPuzzle = 2.6;
        double priceOfTalkingDoll = 3;
        double priceOfTeddyBear = 4.1;
        double priceOfMinion = 8.2;
        double priceOfTruck = 2;

        double discountPercentage = 0;
        double rentPercentage = 10;

        int countToys = puzzle + talkingDoll + teddyBear + minion + truck;

        if (countToys >= 50)
            discountPercentage = 25;

        double priceForPuzzle = puzzle * priceOfPuzzle;
        double priceForTalkingDoll = talkingDoll * priceOfTalkingDoll;
        double priceForTeddyBear = teddyBear * priceOfTeddyBear;
        double priceForMinion = minion * priceOfMinion;
        double priceForTruck = truck * priceOfTruck;

        double incomeBeforeDiscount = priceForPuzzle + priceForTalkingDoll + priceForTeddyBear + priceForMinion + priceForTruck;
        double incomeAfterDiscount = incomeBeforeDiscount - (incomeBeforeDiscount * discountPercentage / 100);
        double incomeAfterRent = incomeAfterDiscount - (incomeAfterDiscount * rentPercentage / 100);
        double moneyAfterExcursion = incomeAfterRent - excursionPrice;

        if (moneyAfterExcursion >= 0)
            out.printf("Yes! %.2f lv left.", moneyAfterExcursion);
        else
            out.printf("Not enough money! %.2f lv needed.", abs(moneyAfterExcursion));
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
