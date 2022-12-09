/*
Условие:
    Катерачи от цяла България се събират на групи и набелязват следващите върхове за изкачване.
    Според размера на групата, катерачите ще изкачват различни върхове.
    •	Група до 5 човека – изкачват Мусала
    •	Група от 6 до 12 човека – изкачват Монблан
    •	Група от 13 до 25 човека – изкачват Килиманджаро
    •	Група от 26 до 40 човека –  изкачват К2
    •	Група от 41 или повече човека – изкачват Еверест
    Да се напише програма, която изчислява процента на катерачите изкачващи всеки връх.
Вход:
    От конзолата се четат поредица от числа, всяко на отделен ред:
    •	На първия ред – броя на групите от катерачи – цяло число в интервала [1...1000]
    •	За всяка една група на отделен ред – броя на хората в групата – цяло число в интервала [1...1000]
Изход:
    Да се отпечатат на конзолата 5 реда, всеки от които съдържа процент между 0.00% и 100.00% с точност до втората цифра след десетичната запетая.
    •	Първи ред - процентът изкачващи Мусала
    •	Втори ред – процентът изкачващи Монблан
    •	Трети ред – процентът изкачващи Килиманджаро
    •	Четвърти ред – процентът изкачващи К2
    •	Пети ред – процентът изкачващи Еверест
Примерен вход и изход:
    10
    10
    5
    1
    100
    12
    26
    17
    37
    40
    78
    -> 1.84%
    -> 6.75%
    -> 5.21%
    -> 31.60%
    -> 54.60%
        Общ брой хора: 10 + 5 + 1 + 100 + 12 + 26 + 17 + 37 + 40 + 78 = 326
        Изкачващи Мусала: 6 / 326 * 100 = 1.84%
        Изкачващи Монблан: 22/326*100 = 6.75%
        Изкачващи Килиманджаро: 17/326*100 = 5.21%
        Изкачващи К2: 103/326*100 = 31.60%
        Изкачващи Еверест: 178/326*100 = 54.60%
    5
    25
    41
    31
    250
    6
    -> 0.00%
    -> 1.70%
    -> 7.08%
    -> 8.78%
    -> 82.44%
        Общ брой хора: 25 + 41 + 31 + 250 + 6 = 353
        Изкачващи Мусала: 0 / 353 * 100 = 0.00%
        Изкачващи Монблан: 6 / 353 * 100 = 1.78%
        Изкачващи Килиманджаро: 25 / 353 * 100 = 7.08%
        Изкачващи К2: 31 / 353 * 100 = 8.78%
        Изкачващи Еверест: 291 / 353 * 100 = 82.44%
 */
package SoftUni.Exer10;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TrekkingMania {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int groups = setValue(1, 1000);

        trekkingMania(groups);
    }

    private static void trekkingMania(int groups) {
        int climbersGroup1 = 0;
        int climbersGroup2 = 0;
        int climbersGroup3 = 0;
        int climbersGroup4 = 0;
        int climbersGroup5 = 0;

        int climbers = 0;

        for (int i=0; i<groups; i++) {
            int climbersInGroup = setValue(1, 1000);

            if (climbersInGroup <= 5)
                climbersGroup1 += climbersInGroup;
            else if (climbersInGroup <= 12)
                climbersGroup2 += climbersInGroup;
            else if (climbersInGroup <= 25)
                climbersGroup3 += climbersInGroup;
            else if (climbersInGroup <= 40)
                climbersGroup4 += climbersInGroup;
            else
                climbersGroup5 += climbersInGroup;

            climbers += climbersInGroup;
        }

        double percentGroup1 = calculatePercentFor(climbersGroup1, climbers);
        double percentGroup2 = calculatePercentFor(climbersGroup2, climbers);
        double percentGroup3 = calculatePercentFor(climbersGroup3, climbers);
        double percentGroup4 = calculatePercentFor(climbersGroup4, climbers);
        double percentGroup5 = calculatePercentFor(climbersGroup5, climbers);

        displayResult(
            percentGroup1,
            percentGroup2,
            percentGroup3,
            percentGroup4,
            percentGroup5
        );
    }

    private static double calculatePercentFor(int climbersGroup, int climbers) {
        return (double) climbersGroup / climbers * 100;
    }

    private static void displayResult(
        double percentGroup1,
        double percentGroup2,
        double percentGroup3,
        double percentGroup4,
        double percentGroup5
    ) {
        out.printf("%.2f%%%n", percentGroup1);
        out.printf("%.2f%%%n", percentGroup2);
        out.printf("%.2f%%%n", percentGroup3);
        out.printf("%.2f%%%n", percentGroup4);
        out.printf("%.2f%%%n", percentGroup5);
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
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            } */
        } else {
            try {
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
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == Double.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == Double.MAX_VALUE)
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
