/*
Условие:
    Частно училище организира лагери за учениците по време на ваканциите.
    В зависимост от вида на ваканцията (пролетна, лятна или зимна)
    и вида на групата (момчета/момичета или смесена) цената на нощувката в хотела е различна,
    както и спортът, който ще практикуват учениците.
        Зимна ваканция
        Пролетна ваканция
        Лятна ваканция
        момчета/момичета
        9.60
        7.20
        15
        смесена група
        10
        9.50
        20
    Училището получава отстъпка от крайната цена, в зависимост от броя на настанените в хотела ученици:
        • Ако броят на учениците е 50 или повече, училището получава 50% отстъпка
        • Ако броят на учениците е 20 или повече и в същото време по-малък от 50,
        училището получава 15% отстъпка
        • Ако броят на учениците е 10 или повече и в същото време по-малък от 20,
        училището получава 5% отстъпка
    В таблицата по-долу са дадени спортовете, които ще се практикуват
    в зависимост от вида на ваканцията и групата:
        Зимна ваканция
        Пролетна ваканция
        Лятна ваканция
        момичета
        Gymnastics
        Athletics
        Volleyball
        момчета
        Judo
        Tennis
        Football
        смесена група
        Ski
        Cycling
        Swimming
    Да се напише програма, която пресмята цената, която ще заплати училището за нощувките и принтира спорта, който ще се практикува от учениците.
Вход:
    От конзолата се четат 4 реда:
        1. Сезонът – текст - “Winter”, “Spring” или “Summer”;
        2. Видът на групата – текст - “boys”, “girls” или “mixed”;
        3. Брой на учениците – цяло число в интервала [1 … 10000];
        4. Брой на нощувките – цяло число в интервала [1 … 100].
Изход:
    На конзолата се отпечатва 1 ред:
        • Спортът, който са практикували учениците и цената за нощувките, която е заплатило училището,
        форматирана до втория знак след десетичната запетая, в следния формат:
    "{спортът} {цената} lv.“
Примерен вход и изход:
    Spring
    girls
    20
    7
    -> Athletics 856.80 lv.
        Пролетна ваканция, група от момичета => спортът е Athletics.
        Цена на нощувките: 20 * 7.20 * 7 = 1008 лв.
        Учениците са 20 => 15% отстъпка
        Крайна цена: 1008 – (15% от 1008) = 856.8 лв.
    Winter
    mixed
    9
    15
    -> Ski 1350.00 lv.
    Summer
    boys
    60
    7
    -> Football 3150.00 lv.
    Spring
    mixed
    17
    14
    -> Cycling 2147.95 lv.
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SchoolCamp {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String season = setValue(null, null);
        String groupType = setValue(null, null);
        int studentsCount = setValue(1, 10000);
        int sleepoverCount = setValue(1, 100);

        sportAndPrice(season, groupType, studentsCount, sleepoverCount);
    }

    private static void sportAndPrice(
            String season,
            String groupType,
            int studentsCount,
            int sleepoverCount
    ) {
        double priceOfSleepover = 0;
        double discountPercent;
        String sport = "";

        switch (season) {
            case "Winter":
                switch (groupType) {
                    case "boys":
                        sport = "Judo";
                        priceOfSleepover = 9.60;
                        break;
                    case "girls":
                        sport = "Gymnastics";
                        priceOfSleepover = 9.60;
                        break;
                    case "mixed":
                        sport = "Ski";
                        priceOfSleepover = 10;
                        break;
                }
                break;
            case "Spring":
                switch (groupType) {
                    case "boys":
                        sport = "Tennis";
                        priceOfSleepover = 7.20;
                        break;
                    case "girls":
                        sport = "Athletics";
                        priceOfSleepover = 7.20;
                        break;
                    case "mixed":
                        sport = "Cycling";
                        priceOfSleepover = 9.50;
                        break;
                }
                break;
            case "Summer":
                switch (groupType) {
                    case "boys":
                        sport = "Football";
                        priceOfSleepover = 15;
                        break;
                    case "girls":
                        sport = "Volleyball";
                        priceOfSleepover = 15;
                        break;
                    case "mixed":
                        sport = "Swimming";
                        priceOfSleepover = 20;
                        break;
                }
                break;
        }

        double rawPrice = priceOfSleepover * sleepoverCount * studentsCount;

        if (studentsCount >= 50)
            discountPercent = 50;
        else if (studentsCount >= 20)
            discountPercent = 15;
        else if (studentsCount >= 10)
            discountPercent = 5;
        else 
            discountPercent = 0;

        double discountPrice = rawPrice * discountPercent / 100;
        double finalPrice = rawPrice - discountPrice;

        out.printf("%s %.2f lv.", sport, finalPrice);
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
                    required = new String[] {"Spring", "Summer", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"boys", "girls", "mixed"};
                if (stringCount > 2) {
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
