/*
Условие:
    Прапраправнукът на Сали Яшар е получил наследство -
    сейф с парола - четири цифри.
    В него е заключена тайната на пеещите каруци.
    Той има автосервиз и се нуждае от реклама,
    затова е решил да направи такава каруца.
    Проблема е, че паролата е скрита в следната задача:
    „Парола ще получиш, ако знаеш  едно цяло число,
    контролна стойност  се нарича то,
    почива в интервала от 4 до 144 включително,
    но да го откриеш може би ще е мъчително. ”
    Паролата има формат: "abcd"
    и контролната стойност трябва да е равна на
    a*b + c*d , но трябва да бъдат спазени следните условия:
    •	при намирането на а и b: a < b
    •	при намиране на  c и d: c > d
    •	a, b, c и d са числа в интервала [1 - 9]
    Каруцата  има четири колела,
    затова паролата ще е четвърто число,
    което трябва да се отпечата.
    В случай, че НЕ се намери такова число, отпечатва се „No!“.
Вход:
    От конзолата се чете едно цяло число
    (контролната стойност): M –
    цяло число в интервала [4 … 144];
Изход:
    Отпечатването на конзолата зависи от резултата:
    •	Ако са намерени четворки числа (аbcd),
    отговарящи на условието, ги отпечатваме всичките
    с разделител интервал: “{а}{b}{c}{d} ”
    •	Отпечатва се един от двата реда на нов ред:
    •	Ако съществува четвърта четворка се отпечатва:
    „Password: {а}{b}{c}{d}“
    •	Ако НЕ са намерени такива числа
    или няма четвърта четворка отпечатваме: “No!”
Примерен вход и изход:
    11
    ->
    1291 1342 1381 1471 1532 1561 1651 1741 1831 1921 2351 2431
    Password: 1471
        Започваме да проверяваме числата последователно:
        {1} {1} {1} {1};   {1} {1} {1} {2};   {1} {1} {1} {3};
        …;   {1} {2} {9} {1}; …
        четворката 1291 спазва условието и отпечатваме и т.н.
    139
    ->
    No!
    110
    ->
    6987 7896 No!
    55
    ->
    1786 2595 3585 3974 4575 4793 5754 5853 5952 6871
    Password: 3974
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TheSongOfTheWheels {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int m = setValue(4, 144);
        int count = 0;
        String password = "";

        for (int i = 1; i <= 9; i++)
            for (int j = 1; j <= 9; j++)
                for (int k = 1; k <= 9; k++)
                    for (int l = 1; l <= 9; l++)
                        if (i * j + k * l == m)
                            if (i < j && k > l) {
                                out.print("" + i + j + k + l + " ");
                                count++;
                                if (count == 4)
                                    password = "" + i + j + k + l;
                            }
        if (!password.equals(""))
            out.printf("\nPassword: %s", password);
        else
            out.print("\nNo!");
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
