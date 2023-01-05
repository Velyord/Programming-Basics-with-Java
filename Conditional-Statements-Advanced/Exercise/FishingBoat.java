/*
Условие:
    Тони и приятели много обичали да ходят за риба, те са толкова запалени по риболова, че решават да отидат на риболов с кораб. Цената за наема на кораба зависи от сезона и броя рибари.
    Цената зависи от сезона:
    • Цената за наем на кораба през пролетта е  3000 лв.
    • Цената за наем на кораба през лятото и есента е  4200 лв.
    • Цената за наем на кораба през зимата е  2600 лв.
    В зависимост от броя си групата ползва отстъпка:
    • Ако групата е до 6 човека включително  –  отстъпка от 10%.
    • Ако групата е от 7 до 11 човека включително  –  отстъпка от 15%.
    • Ако групата е от 12 нагоре  –  отстъпка от 25%.
    Рибарите ползват допълнително 5% отстъпка ако са четен брой освен ако не е есен -
    тогава нямат допълнителна отстъпка.
    Напишете програма, която да пресмята дали рибарите ще съберат достатъчно пари.
Вход:
    От конзолата се четат точно три реда.
        • Бюджет на групата – цяло число в интервала [1…8000]
        • Сезон –  текст : "Spring", "Summer", "Autumn", "Winter"
        • Брой рибари – цяло число в интервала [4…18]
Изход:
    Да се отпечата на конзолата един ред:
        • Ако бюджетът е достатъчен:
    "Yes! You have {останалите пари} leva left."
        • Ако бюджетът НЕ Е достатъчен:
    "Not enough money! You need {сумата, която не достига} leva."
    Сумите трябва да са форматирани с точност до два знака след десетичната запетая.
Примерен вход и изход
    3000
    Summer
    11
    -> Not enough money! You need 570.00 leva.
        Лятото риболовния туризъм струва 4200 лв., 11 рибари ползват 15% отстъпка -> 4200 - 15% = 3570 лв.,
        нечетен брой са и не ползват допълнителна отстъпка,
        3000 <= 3570, следователно не им достигат 570.00 лв.
    3600
    Autumn
    6
    -> Not enough money! You need 180.00 leva.
    2000
    Winter
    13
    -> Yes! You have 50.00 leva left.
 */
package SoftUni.Exer8;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class FishingBoat {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int budget = setValue(1, 8000);
        String season = setStringValue();
        int fishermen = setValue(4, 18);

        checkBudget(budget, season, fishermen);
    }

    private static void checkBudget(int budget, String season, int fishermen) {
        boolean error = false;
        int rent = 0;

        switch (season) {
            case "Spring":  rent = 3000;  break;
            case "Summer":
            case "Autumn":  rent = 4200;  break;
            case "Winter":  rent = 2600;  break;
            default:        error = true; break;
        }
        
        double finalRent = calculateRentAfterDiscount(rent, fishermen, season);
        
        double difference = abs(budget - finalRent);

        displayResult(error, finalRent, budget, difference);
    }

    private static double calculateRentAfterDiscount(int rent, int fishermen, String season) {
        double discountPercent;
        double bonusDiscountPercent;

        if (fishermen <= 6)
            discountPercent = 10;
        else if (fishermen <= 11)
            discountPercent = 15;
        else
            discountPercent = 25;

        if (fishermen % 2 == 0 && !season.equals("Autumn"))
            bonusDiscountPercent = 5;
        else 
            bonusDiscountPercent = 0;

        double discount = rent * (discountPercent / 100.0);
        double rentAfterFirstDiscount = rent - discount;
        double bonusDiscount = rentAfterFirstDiscount * (bonusDiscountPercent / 100.0);

        return rentAfterFirstDiscount - bonusDiscount; 
    }

    private static void displayResult(boolean error, double finalRent, int budget, double difference) {
        if (error)
            out.println("error");
        else if (finalRent > budget)
            out.printf("Not enough money! You need %.2f leva.", difference);
        else
            out.printf("Yes! You have %.2f leva left.", difference);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете :");

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
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }
*/
        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        // out.println("Въведете сезон [Spring, Summer, Autumn, Winter]:");
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
