/*
Условие:
    Напишете програма, която да пресмята резултата от игра.
    Първо получавате число, което показва колко хода ще продължи играта.
    После за всеки ход на играта ще получавате по едно ново число.
    Според интервала в който попада числото се прибавят точки.
    Ако числото е отрицателно или по-голямо 50, тогава то е невалидно.
    В началото на играта резултата е 0 и на всеки ход се прибавят точки по следният начин:
    • От 0 до 9  20 % от числото
    • От 10 до 19  30 % от числото
    • От 20 до 29  40 % от числото
    • От 30 до 39  50 точки
    • От 40 до 50  100 точки
    • Невалидно число  резултата се дели на 2
    Освен резултата програмата трябва да изкарва статистика за проценти числа в дадените интервали.
Вход:
    Входът се чете от конзолата:
        • Първи ред - колко хода ще има по време на играта – цяло число в интервала [1...100]
        • За всеки ход – числата, които се проверяват в кой интервал са –
        цели числа в интервала [-100...100]
Изход:
    Да се отпечата на конзолата 7 реда:
        • 1ви ред: "{Краен резултат}"
        • 2ри ред: "From 0 to 9: {процент в интервала}%"
        • 3ти ред: "From 10 to 19: {процент в интервала}%"
        • 4ти ред: "From 20 to 29: {процент в интервала}%"
        • 5ти ред: "From 30 to 39: {процент в интервала}%"
        • 6ти ред: "From 40 to 50: {процент в интервала}%"
        • 7ми ред: "Invalid numbers: {процент в интервала}%"
    Всички числа трябва да са форматирана до вторият знак след запетаята.
Примерен вход и изход:
    10
    43
    57
    -12
    23
    12
    0
    50
    40
    30
    20
    -> 295.80
    -> From 0 to 9: 10.00%
    -> From 10 to 19: 10.00%
    -> From 20 to 29: 20.00%
    -> From 30 to 39: 10.00%
    -> From 40 to 50: 30.00%
    -> Invalid numbers: 20.00%
        10 хода; Начален резултат = 0 точки
        1ви ход: 40 <= 43 <= 50  към резултата се добавят 100 точки
        2ри ход: 57 > 50 невалидно число  100/2 = 50 точки
        3ти ход:-12 < 0  невалидно число  50/2 = 25 точки
        4ти ход: 20 <= 23 <= 29  към резултата се добавят 40% от 23 = 25 + 9.2 = 34.2
        и така до 10тият ход...

    От 0 до 9: 1 число (0) = 10%
    От 10 до 19: 1 число (10) = 10%
    От 20 до 29: 2 числа (20 и 23) = 20%
    От 30 до 39: 1 число (30) = 10%
    От 40 до 50: 3 числа (40, 43 и 50) = 30%
    Невалидни: 2 числа (57 и -12) = 20%
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class GameOfIntervals {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numberCount = setValue(1, 100);
        validator(numberCount);
    }

    private static void validator(int numberCount) {
        int interval0To9    = 0,
            interval10To19  = 0,
            interval20To29  = 0,
            interval30To39  = 0,
            interval40To50  = 0,
            intervalInvalid = 0;
        double points = 0;

        for (int move=1; move <= numberCount; move++) {
            int number = setValue(-100, 100);
            if (isNBetweenXAndY(number, 0, 9)) {
                interval0To9++;
                points += number * 20.0 / 100.0;
            }
            else if (isNBetweenXAndY(number, 10, 19)) {
                interval10To19++;
                points += number * 30.0 / 100.0;
            }
            else if (isNBetweenXAndY(number, 20, 29)) {
                interval20To29++;
                points += number * 40.0 / 100.0;
            }
            else if (isNBetweenXAndY(number, 30, 39)) {
                interval30To39++;
                points += 50;
            }
            else if (isNBetweenXAndY(number, 40, 50)) {
                interval40To50++;
                points += 100;
            }
            else {
                intervalInvalid++;
                points /= 2;
            }
        }

        displayResult(
            points,
            numberCount,
            interval0To9,
            interval10To19,
            interval20To29,
            interval30To39,
            interval40To50,
            intervalInvalid
        );
    }

    private static void displayResult(
        double points,
        int numberCount,
        int interval0To9,
        int interval10To19,
        int interval20To29,
        int interval30To39,
        int interval40To50,
        int intervalInvalid
    ) {
        double percent0To9 =    100.0 * interval0To9    / numberCount;
        double percent10To19 =  100.0 * interval10To19  / numberCount;
        double percent20To29 =  100.0 * interval20To29  / numberCount;
        double percent30To39 =  100.0 * interval30To39  / numberCount;
        double percent40To50 =  100.0 * interval40To50  / numberCount;
        double percentInvalid = 100.0 * intervalInvalid / numberCount;

        out.printf("%.2f%n", points);
        out.printf("From 0 to 9: %.2f%%%n",   percent0To9);
        out.printf("From 10 to 19: %.2f%%%n", percent10To19);
        out.printf("From 20 to 29: %.2f%%%n", percent20To29);
        out.printf("From 30 to 39: %.2f%%%n", percent30To39);
        out.printf("From 40 to 50: %.2f%%%n", percent40To50);
        out.printf("Invalid numbers: %.2f%%", percentInvalid);
    }

    private static boolean isNBetweenXAndY(int n, int x, int y) {
        return n >= x && n <= y;
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
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
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
