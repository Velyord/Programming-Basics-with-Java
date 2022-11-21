/*
Условие:
    Петър иска да купи N видеокарти, M процесора и P на брой рам памет.
    Ако броя на видеокартите е по-голям от този на процесорите получава 15% отстъпка от крайната сметка.
    Важат следните цени:
        • Видеокарта – 250 лв./бр.
        • Процесор – 35% от цената на закупените видеокарти/бр.
        • Рам памет – 10% от цената на закупените видеокарти/бр.
    Да се изчисли нужната сума за закупуване на материалите и да се пресметне дали бюджета ще му стигне.
Вход:
    Входът се състои от четири реда:
        1. Бюджетът на Петър - реално число в интервала [0.0…100000.0]
        2. Броят видеокарти - цяло число в интервала [0…100]
        3. Броят процесори - цяло число в интервала [0…100]
        4. Броят рам памет - цяло число в интервала [0…100]
Изход:
    На конзолата се отпечатва 1 ред, който трябва да изглежда по следния начин:
        • Ако бюджета е достатъчен:
    "You have {остатъчен бюджет} leva left!"
        • Ако сумата надхвърля бюджета:
    "Not enough money! You need {нужна сума} leva more!"
    Резултатът да се форматира до втория знак след десетичната запетая.
Примерен вход и изход
    900
    2
    1
    3
    -> You have 198.75 leva left!
        Бюджет: 900 лв
        Сума за видеокарти: 2 * 250 = 500 лв.
        Цената за процесор: 35% от 500 = 175 лв.
        Сума за процесори: 1 * 175 = 175 лв.
        Цената за рам памет: 10% от 500 = 50 лв.
        Сума за рам памет: 3 * 50 = 150 лв.
        Обща сума: 500 + 175 + 150 = 825 лв.
        Броя на видеокартите е по-голям от броя на процесорите,
        затова той получава 15% отстъпка от крайната цена: 825 – 15% = 701.25 лв.
        701.25 <= 900
        => парите са му достатъчни
        => остават 900 – 701.25 = 198.75 лв.
    920.45
    3
    1
    1
    ->Not enough money! You need 3.92 leva more!
        Бюджет: 920.45 лв
            Сума за видеокарти: 3 * 250 = 750 лв.
        Цената за процесор: 35% от 750 = 262.50 лв.
            Сума за процесори: 1 * 262.50  = 262.50  лв.
        Цената за рам памет: 10% от 750 = 75 лв.
            Сума за рам памет: 1 * 75 = 75 лв.
        Обща сума: 750 + 262.50 + 75 = 1087.50 лв.
        Броя на видеокартите е по-голям от броя на процесорите,
        затова той получава 15% отстъпка от крайната цена: 1087.50 – 15% = 924.37 лв.
        924.37 > 920.45
        => парите не са му достатъчни
        => нужни са 924.375 – 920.45 = 3.92 лв.
 */
package SoftUni.Exer6;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Shopping {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double budget = setValue(0.0, 100000.0);
        int videoCards = setValue(0, 100);
        int processors = setValue(0, 100);
        int rams = setValue(0, 100);

        calculateIfMoneyIsEnough(budget, videoCards, processors, rams);
    }

    private static void calculateIfMoneyIsEnough(double budget, int videoCards, int processors, int rams) {
        double priceOfVideoCard = 250;
        double priceForVideoCards = priceOfVideoCard * videoCards;
        double priceOfProcessor = priceForVideoCards * 35 / 100;
        double priceForProcessor = priceOfProcessor * processors;
        double priceOfRam = priceForVideoCards * 10 / 100;
        double priceForRam = priceOfRam * rams;
        double discount = 0;

        if (videoCards > processors)
            discount = 15;

        double priceBeforeDiscount = priceForVideoCards + priceForProcessor + priceForRam;
        double priceAfterDiscount = priceBeforeDiscount - priceBeforeDiscount * discount / 100;

        double moneyDifference = budget - priceAfterDiscount;

        if (priceAfterDiscount > budget)
            out.printf("Not enough money! You need %.2f leva more!", abs(moneyDifference));
        else
            out.printf("You have %.2f leva left!", abs(moneyDifference));
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
        /*
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
        */
        return (T) value;
    }
}
