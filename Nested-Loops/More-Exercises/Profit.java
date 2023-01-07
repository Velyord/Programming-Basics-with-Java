/*
Условие:
    Имаме банкноти и монети по 1лв., по 2лв. и по 5лв.
    Да се напише програма,
    която прочита въведените от потребителя брой банкноти и
    монети и сума,
    и извежда на екран всички възможни начини,
    по които сумата може да се изплати с наличните банкноти.
Вход:
    Входът се чете от конзолата и съдържа точно 4 реда:
    1.	Брой монети по 1лв. - цяло положително число;
    2.	Брой монети по 2лв. - цяло положително число;
    3.	Брой банкноти по 5лв. - цяло положително число;
    4.	Сума - цяло положително число в интервала [1…1000];
Изход:
    Да се отпечатат на конзолата всички комбинации от дадените
    номинали, образуващи сумата, форматирани по следния начин:
    o	"{бр. 1лв.} * 1 lv. + {бр. 2лв.} * 2 lv. + {бр. 5лв.} *
    5 lv. = {сума} lv."
Примерен вход и изход:
    3
    2
    3
    10
    ->
    0 * 1 lv. + 0 * 2 lv. + 2 * 5 lv. = 10 lv.
    1 * 1 lv. + 2 * 2 lv. + 1 * 5 lv. = 10 lv.
    3 * 1 lv. + 1 * 2 lv. + 1 * 5 lv. = 10 lv.
    5
    3
    1
    7
    ->
    0 * 1 lv. + 1 * 2 lv. + 1 * 5 lv. = 7 lv.
    1 * 1 lv. + 3 * 2 lv. + 0 * 5 lv. = 7 lv.
    2 * 1 lv. + 0 * 2 lv. + 1 * 5 lv. = 7 lv.
    3 * 1 lv. + 2 * 2 lv. + 0 * 5 lv. = 7 lv.
    5 * 1 lv. + 1 * 2 lv. + 0 * 5 lv. = 7 lv.
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Profit {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int countLev = setValue(0, biggestInt);
        int countTwoLev = setValue(0, biggestInt);
        int countFiveLev = setValue(0, biggestInt);
        int sum = setValue(1, 1000);

        for(int i = 0; i <= countLev; i++) {
            for(int j = 0; j <= countTwoLev; j++) {
                for(int k = 0; k <= countFiveLev; k++) {
                    int result = i * 1 + j * 2 + k * 5;
                    if(result == sum)
                        out.printf(
                            "%d * 1 lv. + %d * 2 lv. + %d * 5 lv. = %d lv.\n",
                            i, j, k, result
                        );
                }
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
