/*
Условие:
    Напишете програма, която генерира и принтира на конзолата четирицифрени числа,
    в които първата и втората двойка цифри образуват двуцифрени прости числа
    (пример за такова число 1723). Крайната стойност до която трябва да се генерират двойките
    се определя от други 2 цифри, подадени като вход, които определят с колко крайната стойност
    е по-голяма от началната.
Вход
От конзолата се четат четири реда:
    • На първия ред – началната стойност на първите първата двойка числа –
    цяло положително число в диапазона [10… 90]
    • На втория ред – началната стойност на втората двойка числа –
    цяло положително число в диапазона [10… 90]
    • На третия ред – разликата между началната и крайната стойност на първата двойка числа –
    цяло положително число в диапазона [1… 9]
    • На четвъртия ред – разликата между началната и крайната стойност на  втората двойка числа –
    цяло положително число в диапазона [1… 9]
Изход:
    Да се отпечатат на конзолата четирицифрените числа,
    в които първите две и вторите две цифри са прости двуцифрени числа.
Примерен вход и изход:
    10
    20
    5
    5
    ->
    1123
    1323
        Началната стойност на първата двойка цифри е 10, а на втората 20.
        Крайните стойности са съответно:
        10 + 5 = 15
        20 + 5 = 25
        Получават се следните комбинации:
        1020 1021 1022 1023 1024 1025 1120 1121 1122 1123 1124 1125 ...
        1320 1321 1322 1323 1324 1325 1420 1421 1422 1423 1424 1425
        1520 1521 1522 1523 1524 1525
        но от тях само 1123 и 1323 са четирицифрени числа,
        в които първата част и втората са едновременно прости числа.
    10
    30
    9
    6
    ->
    1131
    1331
    1731
    1931
        Началната стойност на първата двойка цифри е 10, а на втората 30.
        Крайните стойности са съответно:
        10 + 9 = 19
        30 + 6 = 36
        Получават се следните комбинации:
        1030 1031 1032 1033 1034 1035 1036 1130 1131 1131 1132 ...
        1330 1331 1332 1333 1334 ...
        1930 1931 1932 1933 1934 1935 1936
        но от тях само 1123 1331 1731 1931 са четирицифрени числа,
        в които първата част и втората са едновременно прости числа.
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class PrimePairs {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int startFirstPair = setValue(10, 99);
        int startSecondPair = setValue(10, 99);
        int endFirstPair = setValue(0, 9);
        int endSecondPair = setValue(0, 9);
        Integer[] primeNumbersFrom10To100 =
                new Integer[] {
                        11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47,
                        53, 59, 61, 67, 71, 73, 79, 83, 89, 97
                };
        List<Integer> primeNumbersList = List.of(primeNumbersFrom10To100);

        for (int i = startFirstPair; i <= startFirstPair + endFirstPair; i++) {
            for (int j = startSecondPair; j <= startSecondPair + endSecondPair; j++) {

                if (primeNumbersList.contains(i))
                    if (primeNumbersList.contains(j))
                        out.println("" + i + j);
            }
        }
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
