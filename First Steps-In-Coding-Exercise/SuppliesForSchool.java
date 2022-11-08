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
package Exe4;

import java.util.Scanner;
import static java.lang.System.out;

public class SuppliesForSchool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfPenPacks = Integer.parseInt(scanner.nextLine());
        int numOfMarkerPacks = Integer.parseInt(scanner.nextLine());
        int litersOfCleaningDetergent = Integer.parseInt(scanner.nextLine());
        int discountPercentage = Integer.parseInt(scanner.nextLine());
        out.println(calculateSum(numOfPenPacks, numOfMarkerPacks, litersOfCleaningDetergent, discountPercentage));
    }
    private static double calculateSum(int numOfPenPacks, int numOfMarkerPacks, int litersOfCleaningDeturgent, int discountPercentage) {
        double price = numOfPenPacks * 5.80 + numOfMarkerPacks * 7.20 + litersOfCleaningDeturgent * 1.20;
        return price - price * discountPercentage / 100;
    }
}
