/*
Условие:
    Напишете програма, която спрямо даден бюджет и сезон да пресмята цената,
    типа и класа на кола под наем.
    Сезоните са лято и зима – "Summer" и "Winter". Типа коли са кабрио и джип – "Cabrio" и "Jeep".
    • При бюджет по-малък или равен от 100лв.:
        ◦ Класът ще е - "Economy class"
        ◦ Според сезона колата и цената ще са:
            ▪ Лято – Кабрио – 35% от бюджета
            ▪ Зима – Джип – 65% от бюджета
    • При бюджет по-голям от 100лв. и по-малък или равен от 500лв.:
        ◦ Класът ще е - "Compact class"
        ◦ Според сезона колата и цената ще са:
            ▪ Лято – Кабрио – 45% от бюджета
            ▪ Зима – Джип – 80% от бюджета
    • При бюджет по-голям от 500лв.:
        ◦ Класът ще е – "Luxury class"
        ◦ За всеки сезон колата ще е джип и цената ще е:
            ▪ 90% от бюджета
Вход:
    Входът се чете от конзолата и се състои от два реда:
        • Първи ред – Бюджет – реално число в интервала [10.00...10000.00]
        • Втори ред –  Сезон – текст "Summer" или "Winter"
Изход:
    На конзолата трябва да се отпечатат два реда.
        • Първи ред – "{Вид на класа}"
            ◦ "Economy class", "Compact class" или "Luxury class"
        • Втори ред – "{Вид на колата} - {цена на колата}"
            ◦ Видът на колата – "Cabrio" или "Jeep"
            ◦ Цената трябва да е форматирана до втория знак след запетаята
Примерен вход и изход:
    450
    Summer
    -> Compact class
    -> Cabrio - 202.50
        100 < 450 <= 500 лв. –> клас "Compact class"
        Сезонът е лято –> цената = 45% от 450 = 202.5; типа кола –> "Cabrio"
    450
    Winter
    -> Compact class
    -> Jeep - 360.00
    99.99
    Summer
    -> Economy class
    -> Cabrio - 35.00
    70.50
    Winter
    -> Economy class
    -> Jeep - 45.83
    1010
    Summer
    -> Luxury class
    -> Jeep - 909.00
    1010
    Winter
    -> Luxury class
    -> Jeep - 909.00
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class CarToGo {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double budget = setValue(10.0, 10000.0);
        String season = setValue(null, null);

        displayCarFor(budget, season);
    }

    private static void displayCarFor(double budget, String season) {
        String carClass;
        String carType = "none";
        double price = 0;

        if (budget <= 100) {
            carClass = "Economy class";
            switch (season) {
                case "Summer":
                    carType = "Cabrio";
                    price = budget * 0.35;
                    break;
                case "Winter":
                    carType = "Jeep";
                    price = budget * 0.65;
                    break;
            }
        }
        else if (budget <= 500) {
            carClass = "Compact class";
            switch (season) {
                case "Summer":
                    carType = "Cabrio";
                    price = budget * 0.45;
                    break;
                case "Winter":
                    carType = "Jeep";
                    price = budget * 0.80;
                    break;
            }
        }
        else {
            carClass = "Luxury class";
            switch (season) {
                case "Summer":
                case "Winter":
                    carType = "Jeep";
                    price = budget * 0.90;
            }
        }
        out.printf("%s\n%s - %.2f", carClass, carType, price);
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
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] { "Summer", "Winter"};
                if (stringCount > 1) {
                    requiredString = false;
                    return (T) value;
                }

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
