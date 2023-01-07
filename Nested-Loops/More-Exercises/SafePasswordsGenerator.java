/*
Условие:
    Ани се страхува от това, да не й бъде хакнат някой от
    профилите в социалните мрежи, затова решава да направи
    генератор за пароли, които да бъдат достатъчно сигурни.
    Вашата задача е да й помогнете да напише програма, която ще
    генерира тези пароли, разделени една от друга от знака "|".
    Да се напише програма, която генерира серия от символи като в
    шаблона:
    ABxyBA
    като при всяко генериране на нов код,
    стойностите на символите се увеличават с 1.
    Ако A надхвърли 55, се връща на 35.
    Ако B надхвърли 96, се връща на 64.
    Вход
    От конзолата се чете 1 ред:
    •	На първия ред a – цяло число в интервала [1 … 1000]
    •	На втория ред b – цяло число в интервала [1 … 1000]
    •	На третия ред максимален брой генерирани пароли –
    цяло число в интервала [1 … 1000000]
    Ограничения:
    •	A е символ с ASCII стойност в диапазона [35… 55]
    •	B е символ с ASCII стойност в диапазона [64 … 96]
    •	x e цяло число в диапазона [1… a]
    •	y e цяло число в диапазона [1… b]
Изход:
    Да се отпечата на конзолата:
    •	Генерираният код. Ако броят на комбинациите е по-голям от максималния на кода, да се отпечата до подадената стойност, в противен случай да се отпечата до текущия брой на комбинациите.
Примерен вход и изход:
    2
    3
    10
    ->
    #@11@#|$A12A$|%B13B%|&C21C&|'D22D'|(E23E(|
        Понеже се достига стойността на числата a и b по-рано
        от постигането на максималния брой на комбинациите,
        програмата приключва.
    20
    50
    10
    ->
    #@11@#|$A12A$|%B13B%|&C14C&|'D15D'|
    (E16E(|)F17F)|*G18G*|+H19H+|,I110I,|
        Понеже се достига максималния брой на комбинациите
        по-рано от стойностите на числата a и b, програмата
        приключва.
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class SafePasswordsGenerator {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int a = setValue(1, 1000);
        int b = setValue(1, 1000);
        int maxCountGenPass = setValue(1, 1000000);

        int count = 0;
        int A = 35;
        int B = 64;
        int x = 1;
        int y = 1;
        int maxCombo = a * b;

        while (
            count < maxCombo &&
            count < maxCountGenPass
        ) {
            count++;
            out.printf("%c%c%d%d%c%c|", A, B, x, y, B, A);
            if (y == b) {
                x++;
                y = 0;
            }
            A++;
            B++;
            y++;
            if (A > 55)
                A = 35;
            if (B > 96)
                B = 64;
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
