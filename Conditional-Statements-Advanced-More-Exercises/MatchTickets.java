/*
Условие:
    Когато пуснали билетите за Евро 2016, група запалянковци решили да си закупят.
    Билетите имат две категории с различни цени:
    • IP – 499.99 лева.
    • Normal – 249.99 лева.
    Запалянковците имат определен бюджет, а броят на хората в групата определя какъв процент от бюджета
    трябва да се задели за транспоОт 1 до 4 – 75% от бюджета.
    • От 5 до 9 – 60% от бюджета.
    • От 10 до 24 – 50% от бюджета.
    • От 25 до 49 – 40% от бюджета.
    • 50 или повече – 25% от бюджета.
    Напишете програма, която да пресмята дали с останалите пари от бюджета могат да си купят билети за
    избраната категория. И колко пари ще им останат или ще са им нужни.
Вход:
    Входът се чете от конзолата и съдържа точно 3 реда:
        • На първия ред е бюджетът – реално число в интервала [1 000.00 ... 1 000 000.00]
        • На втория ред е категорията – "VIP" или "Normal"
        • На третия ред е броят на хората в групата – цяло число в интервала [1 ... 200]
Изход:
    Да се отпечата на конзолата един ред:
        • Ако бюджетът е достатъчен:
            ◦ "Yes! You have {останалите пари на групата} leva left."
        • Ако бюджетът НЕ Е достатъчен:
            ◦ "Not enough money! You need {сумата, която не достига} leva."
    Сумите трябва да са форматирани с точност до два знака след десетичната запетая.
Примерен вход и изход:
    1000
    Normal
    1
    -> Yes! You have 0.01 leva left.
        1 човек: 75% от бюджета отиват за транспорт
    Остават: 1000 – 750 = 250
    Категория Normal: билетът струва 249.99 * 1
    249.99 < 250: остават му 250 – 249.99 = 0.01
    30000
    VIP
    49
    -> Not enough money! You need 6499.51 leva.
        49 човек: 40% от бюджета отиват за транспорт
        Остават: 30000 – 12000 = 18000
        Категория VIP: билетът струва 499.99 * 49
        24499.510000000002 < 18000
        Не стигат 24499.510000000002 - 18000 = 6499.51
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class MatchTickets {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double budget = setValue(1000.0, 1000000.0);
        String category = setValue(null, null);
        int peopleCount = setValue(1, 200);

        double price = calcPrice(budget, category, peopleCount);
        checkIfBudgetIsEnough(price, budget);
    }

    private static void checkIfBudgetIsEnough(double price, double budget) {
        double priceDiff = abs(budget - price);
        
        if (price > budget)
            out.printf("Not enough money! You need %.2f leva.", priceDiff);
        else
            out.printf("Yes! You have %.2f leva left.", priceDiff);
    }

    private static double calcPrice(double budget, String category, int peopleCount) {
        double priceForCategory = 0;

        switch (category) {
            case "VIP": priceForCategory = 499.99; break;
            case "Normal": priceForCategory = 249.99; break;
        }
        double transportPercentOfBudget = 0;

        if (peopleCount >= 50)
            transportPercentOfBudget = 25;
        else if (peopleCount >= 25)
            transportPercentOfBudget = 40;
        else if (peopleCount >= 10)
            transportPercentOfBudget = 50;
        else if (peopleCount >= 5)
            transportPercentOfBudget = 60;
        else
            transportPercentOfBudget = 75;

        double seatsPrice = priceForCategory * peopleCount;
        double transportPrice = budget * transportPercentOfBudget / 100;

        return seatsPrice + transportPrice;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }

            if (requiredString){
                stringCount++;

                if (stringCount == 1) {
                    String[] required = { "VIP", "Normal" };
                    List<String> requiredList = List.of(required);
                    if (!requiredList.contains(value)){
                        out.print("Моля въведете един от следните избори: \n| ");
                        for (String thing : required)
                            out.print(thing + " | ");
                        out.println();
                        stringCount--;
                        return setValue(null, null);
                    }
                }
            }
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
