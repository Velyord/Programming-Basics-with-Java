/*
Условие:
    Напишете програма която проверява всички възможни комбинации от двойка числа в интервала от две дадени числа.
    На изхода се отпечатва, коя поред е комбинацията чиито сбор от числата е равен на дадено магическо число.
    Ако няма нито една комбинация отговаряща на условието се отпечатва съобщение, че не е намерено.
Вход:
    Входът се чете от конзолата и се състои от три реда:
    •	Първи ред – начало на интервала – цяло число в интервала [1...999]
    •	Втори ред – край на интервала – цяло число в интервала [по-голямо от първото число...1000]
    •	Трети ред – магическото число – цяло число в интервала [1...10000]
Изход:
    На конзолата трябва да се отпечата един ред, според резултата:
    •	Ако е намерена комбинация чиито сбор на числата е равен на магическото число
    o	"Combination N:{пореден номер} ({първото число} + {второ число} = {магическото число})"
    •	Ако не е намерена комбинация отговаряща на условието
    o	"{броят на всички комбинации} combinations - neither equals {магическото число}"
Примерен вход и изход:
    1
    10
    5
    -> Combination N:4 (1 + 4 = 5)
        Всички комбинации  от две числа между 1 и 10 са:
        1 1, 1 2, 1 3, 1 4, 1 5, ... 2 1, 2 2, ... 4 9, 4 10, 5 1 ... 10 9, 10 10
        Първата комбинация, чиито сбор на числата е равен на магическото число 5 е четвъртата (1 и 4)
    88
    888
    1000
    -> Combination N:20025 (112 + 888 = 1000)
    23
    24
    20
    -> 4 combinations - neither equals 20
        Всички комбинации  от две числа между 23 и 24 са: 23 23, 23 24, 24 23, 24 24 (общо 4)
        Няма двойки числа, чиито сбор е равен на магическото 20
    88
    888
    2000
    -> 641601 combinations - neither equals 2000

 */
package SoftUni.Lab13;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SumOfTwoNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int start = setValue(1, 999);
        int end = setValue(start, 1000);
        int magicNumber = setValue(1, 10000);

        findTheWiningCombination(start, end, magicNumber);
    }

    private static void findTheWiningCombination(int start, int end, int magicNumber) {
        int counter = 0;
        boolean combinationFound = false;

        for (int i=start; i<=end; i++) {
            for (int j=start; j<=end; j++) {
                counter++;

                if (i + j == magicNumber) {
                    out.printf("Combination N:%d (%d + %d = %d)", counter, i, j, magicNumber);
                    combinationFound = true;
                    break;
                }
            }
            if (combinationFound) {
                break;
            }
        }
        if (!combinationFound)
            out.printf("%d combinations - neither equals %d", counter, magicNumber);
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

                out.println();
                return setValue(null, null);
            } */
        } else {
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
