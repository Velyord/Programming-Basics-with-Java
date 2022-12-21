/*
Условие:
    Да се напише програма, която генерира трицифрени PIN кодове,
    като цифрите на всеки PIN код са в определен интервал.
    За да бъде валиден един PIN код той трябва да отговаря на следните условия:
    • Първата и третата цифра трябва да бъдат четни.
    • Втората цифра трябва да бъде просто число в диапазона [2...7].
Вход:
    От конзолата се четат 3 реда:
        • Горната граница на първото число - цяло число в диапазона [1...9]
        • Горната граница на второто число - цяло число в диапазона [1...9]
        • Горната граница на третото число - цяло число в диапазона [1...9]
Изход:
    Да се отпечатат на конзолата всички валидни трицифрени PIN кодове,
    чиито цифри отговарят на съответните интервали.
Примерен вход и изход:
    3
    5
    5
    ->
    2 2 2
    2 2 4
    2 3 2
    2 3 4
    2 5 2
    2 5 4
        Първото въведено число е 3, отговарящо за максималната стойност на първата цифра.
        Второто въведено число е 5, отговарящо за максималната стойност на втората цифра.
        Третото въведено число е 5, отговарящо за максималната стойността на третата цифра.
        Във всички трицифрени PIN кодове, които сме получили първата цифра ни е 2,
        защото това е единственото възможно четно число.
        При втората цифра важи друго правило.
        Там трябва да подберем всички възможни прости числа в диапазона от 2 до 7.
        В нашия случай тези числа са както следва 2, 2, 3, 3, 5, 5.
        При третата цифра важи правилото за четните числа и ако го спазваме, получаваме,
        че възможните числа са: 2, 4, 2, 4, 2, 4.
    8
    2
    8
    ->
    2 2 2
    2 2 4
    2 2 6
    2 2 8
    4 2 2
    4 2 4
    4 2 6
    4 2 8
    6 2 2
    6 2 4
    6 2 6
    6 2 8
    8 2 2
    8 2 4
    8 2 6
    8 2 8
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class UniquePINCodes {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int first = setValue(1, 9);
        int second = setValue(1, 9);
        int third = setValue(1, 9);

        generatePINCodes(first, second, third);
    }

    private static void generatePINCodes(int first, int second, int third) {
        for (int i = 2; i <= first; i += 2)
            for (int j = 2; j <= second; j ++)
                if (j == 2 || j == 3 || j == 5 || j == 7)
                    for (int k = 2; k <= third; k += 2)
                        out.println(i + " " + j + " " + k);
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
