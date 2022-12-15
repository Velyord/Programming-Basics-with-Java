/*
Условие:
    Мария иска да купи подарък на сина си. Тя работи в магазин за цветя.
    В магазина идва поръчка за цветя. Напишете програма,
    която пресмята сумата от поръчката и дали печалбата е достатъчна за подаръка.
    Цветята имат следните цени:
    • Магнолии – 3.25 лева
    • Зюмбюли – 4 лева
    • Рози – 3.50 лева
    • Кактуси – 8 лева
    От общата сума, Мария трябва да плати 5% данъци.
Вход:
    Входът се чете от конзолата и се състои от 5 реда:
        • Брой магнолии – цяло число в интервала [0 … 50]
        • Брой зюмбюли – цяло число в интервала [0 … 50]
        • Брой рози – цяло число в интервала [0 … 50]
        • Брой кактуси – цяло число в интервала [0 … 50]
        • Цена на подаръка – реално число в интервала [0.00 … 500.00]
Изход:
    На конзолата трябва да се отпечата един ред.
        • Ако парите СА стигнали: "She is left with {останали} leva." –
        сумата трябва да е закръглена към по-малко цяло число (пр. 1.90 -> 1).
        • Ако парите НЕ достигат: "She will have to borrow {останали} leva." –
        сумата трябва да е закръглена към по-голямо цяло число (пр. 1.10 -> 2).
Примерен вход и изход:
    2
    3
    5
    1
    50
    -> She will have to borrow 9 leva.
        Сума = 2 * 3.25 + 3 * 4 + 5 * 3.5 + 1 * 8 = 44 лева
        Данъци = 5% от 44 = 2.20. Печалба - 41.80 лева
        50 – 41.80 = 8.20 лева недостигнали
    15
    7
    5
    10
    100
    -> She is left with 65 leva.
        Сума = 15 * 3.25 + 7 * 4 + 5 * 3.5 + 10 * 8 = 174.25 лева
        Данъци = 5% от 174.25 = 8.7125. Печалба - 165.5375 лева
        165.5375 - 100 = 65.54 лева остават
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.Math.*;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FlowerShop {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int magnolii = setValue(0, 50);
        int ziumbiuli = setValue(0, 50);
        int rozi = setValue(0, 50);
        int kaktusi = setValue(0, 50);
        double giftPrice = setValue(0.0, 500.0);

        double profit = calcProfit(magnolii, ziumbiuli, rozi, kaktusi);
        isMoneyEnough(profit, giftPrice);
    }

    private static void isMoneyEnough(double profit, double giftPrice) {
        double priceDiff = abs(profit - giftPrice);
        if (giftPrice > profit)
            out.printf("She will have to borrow %.0f leva.", ceil(priceDiff));
        else
            out.printf("She is left with %.0f leva.", floor(priceDiff));
    }

    private static double calcProfit(
            int magnolii,
            int ziumbiuli,
            int rozi,
            int kaktusi
    ) {
        double priceOfMagnolii = 3.25;
        double priceOfZiumbiuli = 4;
        double priceOfRozi = 3.5;
        double priceOfKaktusi = 8;
        double taxPercent = 5;

        double priceForMagnolii = priceOfMagnolii * magnolii;
        double priceForZiumbiuli = priceOfZiumbiuli * ziumbiuli;
        double priceForRozi = priceOfRozi * rozi;
        double priceForKaktusi = priceOfKaktusi * kaktusi;

        double priceForEverything =
                priceForMagnolii + priceForZiumbiuli + priceForRozi + priceForKaktusi;
        double priceOfTax = priceForEverything * taxPercent / 100;

        return priceForEverything - priceOfTax;
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
                    String[] required = { "room for one person", "apartment", "president apartment" };
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

                if (stringCount == 2) {
                    String[] required = { "positive", "negative" };
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
