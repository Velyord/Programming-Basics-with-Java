/*
Условие:
    Поздравления, поради вашите задълбочени знания в сферата на програмирането
    МВР реши да наеме точно вас за създаването на новата им система
    за генериране на специални автомобилни номера.
    Всеки един специален автомобилен номер се състой от четири числа.
    Условията, които разграничават специалните от обикновените номера са следните:
    • Ако номерът започва с четна цифра,
    то той трябва да завършва на нечетна цифра и обратното – ако започва с нечетна,
    завършва на четна
    • Първата цифра от номера е по-голяма от последната
    • Сумата от втората и третата цифра трябва да е четно число
    Входа се състой от две числа - начало и край на интервал,
    между които трябва да се генерира всяко едно число от номера.
Вход:
    1. Първи ред - едноцифрено число - началото на интервала – цяло число в интервала [1…9]
    2. Втори ред - едноцифрено число - края на интервала – цяло число в интервала [1…9]
Изход:
    На конзолата трябва да се отпечатат всички специални номера, разделени с интервал.
Примерен вход и изход
    2
    3
    ->
    3222 3332
        Всяка цифра от номера е в интервала [2…3].
        За всеки номер проверяваме дали изпълнява съответните условия
        и ако ги изпълнява го печатаме на конзолата. В противен случай го игнорираме.
        Понеже  3 е нечетно, а 2 е четно, 3 >2  и резултата от 2+2 е четно число,
        то 3222 отговаря на условията и го принтираме.
        По същия начин при 3332 -  3 е нечетно, а 2 е четно , 3 >2 и 3+3 = 6 , което е четно число.
    3
    5
    ->
    4333 4353 4443 4533 4553 5334 5354 5444 5534 5554
    5
    8
    ->
    6555 6575 6665 6685 6755 6775 6865 6885 7556 7576 7666 7686 7756 7776 7866 7886 8555 8557 8575 8577
    8665 8667 8685 8687 8755 8757 8775 8777 8865 8867 8885 8887
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class CarNumber {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int intervalBegin = setValue(1, 9);
        int intervalEnd = setValue(1, 9);

        generateCarNumbers(intervalBegin, intervalEnd);
    }

    private static void generateCarNumbers(int intervalBegin, int intervalEnd) {
        for (int i=1; i<=9; i++)
            if (i >= intervalBegin && i <= intervalEnd)
                for (int j=1; j<=9; j++)
                    if (j >= intervalBegin && j <= intervalEnd)
                        for (int k=1; k<=9; k++)
                            if (k >= intervalBegin && k <= intervalEnd)
                                for (int l=1; l<=9; l++)
                                    if (l >= intervalBegin && l <= intervalEnd)
                                        if (i % 2 == 0 && l % 2 != 0 || i % 2 != 0 && l % 2 == 0)
                                            if (i > l)
                                                if ((j + k) % 2 == 0)
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
