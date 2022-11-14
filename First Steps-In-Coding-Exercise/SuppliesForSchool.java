/*
    Условие:
        Учебната година вече е започнала и отговорничката на 10Б клас - Ани трябва да купи определен брой пакетчета с химикали, пакетчета с маркери, както и препарат за почистване на дъска. Тя е редовна клиентка на една книжарница, затова има намаление за нея, което представлява някакъв процент от общата сума. Напишете програма, която изчислява колко пари ще трябва да събере Ани, за да плати сметката, като имате предвид следния ценоразпис:
        • Пакет химикали - 5.80 лв.
        • Пакет маркери - 7.20 лв.
        • Препарат - 1.20 лв (за литър)
    Вход:
        От конзолата се четат 4 числа:
        • Брой пакети химикали - цяло число в интервала [0...100]
        • Брой пакети маркери - цяло число в интервала [0...100]
        • Литри препарат за почистване на дъска - цяло число в интервала [0…50]
        • Процент намаление - цяло число в интервала [0...100]
    Изход:
        Да се отпечата на конзолата колко пари ще са нужни на Ани, за да си плати сметката.
    Примерен вход и изход:
        2, 3, 4, 25 -> 28.5
            Цена на пакетите химикали => 2 * 5.80 = 11.60 лв.
            Цена на пакетите маркери => 3 * 7.20 = 21.60 лв.
            Цена на препарата => 4 * 1.20 = 4.80 лв.
            Цена за всички материали => 11.60 + 21.60 + 4.80 = 38.00 лв.
            25% = 0.25
            Цена с намаление = 38.00 – (38.00 * 0.25) = 28.50 лв.
        4, 2, 5, 13 -> 37.932
            Цена на пакетите химикали => 4 * 5.80 = 23.20 лв.
            Цена на пакетите маркери => 2 * 7.20 = 14.40 лв.
            Цена на препарата => 5 * 1.20 = 6.00 лв.
            Цена за всички материали => 23.20 + 14.40 + 6.00 = 43.60 лв.
            13% = 0.13
            Цена с намаление = 43.60 – (43.60 * 0.13) = 37.932 лв.
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SuppliesForSchool {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numOfPenPacks = setIntValue(0, 100, "брой пакети химикали");
        int numOfMarkerPacks = setIntValue(0, 100, "брой пакети маркери");
        int litersOfCleaningDetergent = setIntValue(0, 50, "литри препарат за почистване на дъска");
        int discountPercentage = setIntValue(0, 100, "процент намаление");

        double price = calculateSum(numOfPenPacks, numOfMarkerPacks, litersOfCleaningDetergent, discountPercentage);

        out.println(price);
    }

    private static double calculateSum(int numOfPenPacks, int numOfMarkerPacks, int litersOfCleaningDeturgent, int discountPercentage) {
        double priceOfPenPacks = 5.80;
        double priceOfMarkerPacks = 7.20;
        double priceOfCleaningDeturgent = 1.20;

        double priceForPenPacks = numOfPenPacks * priceOfPenPacks;
        double priceForMarkerPacks = numOfMarkerPacks * priceOfMarkerPacks;
        double priceForCleaningDeturgent = litersOfCleaningDeturgent * priceOfCleaningDeturgent;

        double price = priceForPenPacks + priceForMarkerPacks + priceForCleaningDeturgent;

        return price - (price * discountPercentage / 100);
    }

    private static int setIntValue(int min, int max, String output) {
        int value;
        out.println("Въведи " + output + ":");

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, output);
        }

        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max, output);
        }
        else
            return value;
    }
}
