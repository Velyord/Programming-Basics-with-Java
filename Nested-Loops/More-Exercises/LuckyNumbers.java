/*
Условие:
    Да се напише програма, която прочита едно цяло число N и генерира всички възможни "щастливи" и различни 4-цифрени числа(всяка цифра от числото е в интервала [1...9]).
    Числото трябва да отговаря на следните условия:
    Щастливо число е 4-цифрено число, на което сбора от първите две цифри е равен на сбора от последните две. Числото N трябва да се дели без остатък от сбора на първите две цифри на "щастливото" число.
Вход:
    Входът се чете от конзолата и се състои от едно цяло число в интервала [2...10000]
Изход:
    На конзолата трябва да се отпечатат всички "щастливи" и различни 4-цифрени числа, разделени с интервал
Примерен вход и изход:
    3
    ->
    1212 1221 2112 2121
        И четирите числа са "щастливи"
        3 / (1+2) = 1 – остатък 0
    7
    ->
    1616 1625 1634 1643 1652 1661 2516 2525 2534 2543 2552 2561 3416 3425 3434 3443 3452 3461 4316 4325
    4334 4343 4352 4361 5216 5225 5234 5243 5252 5261 6116 6125 6134 6143 6152 6161
        Всички числа са "щастливи"
        7 / (1+6) = 1 – остатък 0
        7 / (2+5) = 1 – остатък 0
        7 / (3+4) = 1 – остатък 0
    24
    ->
    1111 1212 1221 1313 1322 1331 1515 1524 1533 1542 1551 1717 1726 1735 1744 1753 1762 1771 2112 2121
    2213 2222 2231 2415 2424 2433 2442 2451 2617 2626 2635 2644 2653 2662 2671 3113 3122 3131 3315 3324
    3333 3342 3351 3517 3526 3535 3544 3553 3562 3571 3939 3948 3957 3966 3975 3984 3993 4215 4224 4233
    4242 4251 4417 4426 4435 4444 4453 4462 4471 4839 4848 4857 4866 4875 4884 4893 5115 5124 5133 5142
    5151 5317 5326 5335 5344 5353 5362 5371 5739 5748 5757 5766 5775 5784 5793 6217 6226 6235 6244 6253
    6262 6271 6639 6648 6657 6666 6675 6684 6693 7117 7126 7135 7144 7153 7162 7171 7539 7548 7557 7566
    7575 7584 7593 8439 8448 8457 8466 8475 8484 8493 9339 9348 9357 9366 9375 9384 9393
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class LuckyNumbers {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue(2, 10000);
        for (int i=1; i<=9; i++)
            for (int j=1; j<=9; j++)
                for (int k=1; k<=9; k++)
                    for (int l=1; l<=9; l++)
                        if (i + j == k + l)
                            if (number % (i + j) == 0)
                                out.print("" + i + j + k + l + " ");
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
