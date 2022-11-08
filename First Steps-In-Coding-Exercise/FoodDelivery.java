/*
Условие:
    Ресторант отваря врати и предлага няколко менюта на преференциални цени:
    • Пилешко меню –  10.35 лв.
    • Меню с риба – 12.40 лв.
    • Вегетарианско меню  – 8.15 лв.
    Напишете програма, която изчислява колко ще струва на група хора да си поръчат храна за вкъщи.
    Групата ще си поръча и десерт, чиято цена е равна на 20% от общата сметка (без доставката).
    Цената на доставка е 2.50 лв и се начислява най-накрая.
Вход:
    От конзолата се четат 3 реда:
    • Брой пилешки менюта – цяло число в интервала [0 … 99]
    • Брой менюта с риба – цяло число в интервала [0 … 99]
    • Брой вегетариански менюта – цяло число в интервала [0 … 99]
Изход:
    Да се отпечата на конзолата един ред:  "{цена на поръчката}"
Примерен вход и изход:
    2
    4
    3
    -> 116.2
        Цена за пилешките менюта: 2 броя * 10.35  = 20.70
        Цена за менютата с риба: 4 броя * 12.40 = 49.60
        Цена за вегетарианските менюта: 3 броя * 8.15 = 24.45
        Обща цена на менютата: 20.70 + 49.60 + 24.45 = 94.75
        Цена на десерта: 20% от 94.75 = 18.95
        Цена на доставка: 2.50 (по условие)
        Обща цена на поръчката: 94.75 + 18.95 + 2.50 = 116.20
    9
    2
    6
    -> 202.72
        Цена за пилешките менюта: 9 броя * 10.35  = 93.15
        Цена за менютата с риба: 2 броя * 12.40 = 24.80
        Цена за вегетарианските менюта: 6 броя * 8.15 = 48.90
        Обща цена на менютата: 93.15 + 24.80 + 48.90 = 166.85
        Цена на десерта: 20% от 166.85 = 33.37
        Цена на доставка: 2.50 (по условие)
        Обща цена на поръчката: 166.85 + 33.37 + 2.50 = 202.72
*/
package SoftUni.Exer4;

import java.util.Scanner;

import static java.lang.System.*;

public class FoodDelivery {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int chickenMenus = setIntValue(0, 99, "пилешки");
        int fishMenus = setIntValue(0, 99, "рибни");
        int veganMenus = setIntValue(0, 99, "вегетариански");

        double priceForChicken = 10.35;
        double priceForFish = 12.40;
        double priceForVegan = 8.15;
        double priceDelivery = 2.50;

        double priceOfChickenMenus = chickenMenus * priceForChicken;
        double priceOfFishMenus = fishMenus * priceForFish;
        double priceOfVeganMenus = veganMenus * priceForVegan;

        double sumMenus = priceOfChickenMenus + priceOfFishMenus + priceOfVeganMenus;
        double desert = sumMenus * 0.20;
        double sumAll = sumMenus + desert + priceDelivery;

        out.printf("%.2f", sumAll);
    }
    private static int setIntValue(int min, int max, String menu) {
        int value;
        out.println("Въведи брой "+menu+" менюта:");
        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, menu);
        }
        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max, menu);
        }
        else
            return value;
    }
}
