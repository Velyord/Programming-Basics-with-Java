/*
Условие:
    Напишете програма, която спрямо даден бюджет и сезон да пресмята цената,
    локацията и мястото на настаняване за ваканция.
    Сезоните са лято и зима – "Summer" и "Winter".
    Локациите са – "Alaska" и "Morocco".
    Възможните места за настаняване – "Hotel", "Hut" или "Camp".
    • При бюджет по-малък или равен от 1000лв.:
        ◦ Настаняване в "Camp"
        ◦ Според сезона локацията ще е една от следните и ще струва определен процент от бюджета:
            ▪ Лято – Аляска – 65% от бюджета
            ▪ Зима – Мароко – 45% от бюджета
    • При бюджет по-голям от 1000лв. и по-малък или равен от 3000лв.:
        ◦ Настаняване в "Hut"
        ◦ Според сезона локацията ще е една от следните и ще струва определен процент от бюджета:
            ▪ Лято – Аляска – 80% от бюджета
            ▪ Зима – Мароко – 60% от бюджета
    • При бюджет по-голям от 3000лв.:
        ◦ Настаняване в "Hotel"
        ◦ Според сезона локацията ще е една от следните и ще струва 90% от бюджета:
            ▪ Лято – Аляска
            ▪ Зима – Мароко
Вход:
    Входът се чете от конзолата и се състои от два реда:
        • Първи ред – Бюджет – реално число в интервала [10.00...10000.00]
        • Втори ред –  Сезон – текст "Summer" или "Winter"
Изход:
    На конзолата трябва да се отпечатат един ред.
    "{локацията} – {мястото за настаняване} – {цената}"
    Цената трябва да е форматирана до вторият знак след десетичната запетая.
Примерен вход и изход:
    800
    Summer
    -> Alaska - Camp - 520.00
        800 <= 1000 лв.  настаняване "Camp"
        Сезонът е лято  "Alaska"; Цената е  65% от 800 = 520
    799.50
    Winter
    -> Morocco - Camp - 359.78
    1100
    Summer
    -> Alaska - Hut - 880.00
    2543.99
    Winter
    -> Morocco - Hut - 1526.39
    3460
    Summer
    -> Alaska - Hotel - 3114.00
    5000
    Winter
    -> Morocco - Hotel - 4500.00
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Vacation {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double budget = setValue(10.0, 10000.0);
        String season = setValue(null, null);

        vacation(budget, season);
    }

    private static void vacation(double budget, String season) {
        String location = "";
        String type;
        double price = 0;

        if (budget <= 1000) {
            type = "Camp";
            switch (season) {
                case "Summer":
                    location = "Alaska";
                    price = budget * 0.65;
                    break;
                case "Winter":
                    location = "Morocco";
                    price = budget * 0.45;
                    break;
            }
        }
        else if (budget <= 3000) {
            type = "Hut";
            switch (season) {
                case "Summer":
                    location = "Alaska";
                    price = budget * 0.80;
                    break;
                case "Winter":
                    location = "Morocco";
                    price = budget * 0.60;
                    break;
            }
        }
        else {
            type = "Hotel";
            switch (season) {
                case "Summer":
                    location = "Alaska";
                    price = budget * 0.90;
                    break;
                case "Winter":
                    location = "Morocco";
                    price = budget * 0.90;
                    break;
            }
        }
        out.printf("%s - %s - %.2f", location, type, price);
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
