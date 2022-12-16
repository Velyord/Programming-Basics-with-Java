/*
Условие:
    Напишете програма която пресмята колко пари ще изкара шофьор на ТИР за един сезон.
    На входа програмата получава през кой сезон ще работи шофьора,
    както и колко километра на месец ще кара. Един сезон е 4 месеца.
    Според зависи сезона и броя километри на месец ще му се заплаща различна сума на километър:
        Пролет/Есен
        Лято
        Зима
        км на месец <= 5000
        0.75 лв./км
        0.90 лв./км
        1.05 лв./км
        5000 < км на месец <= 10000
        0.95 лв./км
        1.10 лв./км
        1.25 лв./км
        10000 < км на месец <= 20000
        1.45 лв./км – за който и да е сезон
    След като са извадени 10% за данъци се отпечатват останалите пари.
Вход:
    Входът се чете от конзолата и се състои от два реда:
        • Първи ред – Сезон – текст "Spring", "Summer", "Autumn" или "Winter"
        • Втори ред –  Километри на месец – реално число в интервала [10.00...20000.00]
Изход:
    На конзолата трябва да се отпечатат едно число:
        • Заплатата на шофьора след данъците, форматирана до втория знак след десетичната запетая.
Примерен вход и изход:
    Summer
    3455
    -> 11194.20
        3455 <= 5000  влиза във първият интервал
        Сезонът е лято  получава 0.90 лв./км
        Заплата: 3455 * 0.90 = 3109.5 * 4 месеца - 10% данъци = 11194.2
    Winter
    4350
    -> 16443.00
    Winter
    5678
    -> 25551.00
    Winter
    16042
    -> 83739.24
    Spring
    1600
    -> 4320.00
    Autumn
    8600
    -> 29412.00
    Spring
    16942
    -> 88437.24
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TruckDriver {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String season = setValue(null, null);
        double kmPerMonth = setValue(10.0, 20000.0);

        double driversSalary = calcDriversSalary(season, kmPerMonth);
        out.printf("%.2f", driversSalary);
    }

    private static double calcDriversSalary(String season, double kmPerMonth) {
        double moneyPerKm = 0;
        switch (season) {
            case "Spring":
            case "Autumn":
                if (kmPerMonth <= 5000)
                    moneyPerKm = 0.75;
                else if (kmPerMonth <= 10000)
                    moneyPerKm = 0.95;
                else
                    moneyPerKm = 1.45;
                break;
            case "Summer":
                if (kmPerMonth <= 5000)
                    moneyPerKm = 0.90;
                else if (kmPerMonth <= 10000)
                    moneyPerKm = 1.10;
                else
                    moneyPerKm = 1.45;
                break;
            case "Winter":
                if (kmPerMonth <= 5000)
                    moneyPerKm = 1.05;
                else if (kmPerMonth <= 10000)
                    moneyPerKm = 1.25;
                else
                    moneyPerKm = 1.45;
                break;
        }
        double taxPercent = 10;
        double rawSalary = moneyPerKm * kmPerMonth * 4; // for 4 months
        double taxMoney = rawSalary * taxPercent / 100;

        return rawSalary - taxMoney;
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
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
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
