/*
Условие:
    В града има тайна врата, за която всички знаят,
    но никой не е успявал да я отключи
    и да види какво има зад нея.
    За да бъде отключена трябва да се въведе трицифрен код.
    Напишете програма,
    която генерира комбинации спрямо въведени числа –
    предположения от потребителя.
    От конзолата се въвеждат три цифри.
    Тези цифри ще бъдат горната граница,
    до която ние искаме да получим всички трицифрени числа,
    на които всяка една цифра отговаря на следните условия:
    •	Цифрата на единиците и цифрата на стотиците трябва да
    бъде четна
    •	Цифрата на десетиците да бъде просто число в диапазона
    (2...7).
    Това ще са възможните комбинации според въведените
    предположения от потребителя,
    с които ще може евентуално да се отключи вратата.
Вход:
    От конзолата се четат 3 реда:
    •	Горната граница на стотиците -
    цяло число в диапазона (1-9)
    •	Горната граница на десетиците -
    цяло число в диапазона (1-9)
    •	Горната граница на единиците -
    цяло число в диапазона (1-9)
Изход:
    Да се отпечатат на конзолата всички трицифрени числа,
    за които всяка една част отговаря на условията по-горе.
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
        Първото въведено число е 3,
        отговарящо за максималната стойност на стотиците.
        Второто въведено число е 5,
        отговарящо за максималната стойност на десетиците.
        Третото въведено число е 5,
        отговарящо за максималната стойността на едениците.
        Във всички кобинации,
        които сме получили стотицата ни е 2,
        защото това е единственото четно число.
        При десетиците важи друго правило.
        Там трябва да подберем всички прости числа
        в диапазона от 2 до 7.
        В нашия случай тези числа
        са както следва 2, 2, 3, 3, 5, 5.
        При единиците важи правилото за четните числа
        и ако го следваме, получаваме,
        че резултатът ни е: 2, 4, 2, 4, 2, 4.
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

public class SecretDoorsLock {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int upperStotici = setValue(1, 9);
        int upperDesetici = setValue(1, 9);
        int upperEdinici = setValue(1, 9);

        for (int i = 2; i <= upperStotici; i++)
            for (int j = 2; j <= upperDesetici; j++)
                for (int k = 2; k <= upperEdinici; k++)
                    if (i % 2 == 0 && k % 2 == 0)
                        if (j == 2 || j == 3 || j == 5 || j == 7)
                            out.printf("%d %d %d%n", i, j, k);
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
