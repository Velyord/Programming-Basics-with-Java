/*
Условие:
    Екипът на СофтУни си организира футболен турнир.
    Първоначално прочитаме от конзолата капацитета на стадиона и броят на всички фенове.
    След това за всеки фен се чете секторът, в който се намира.
    Феновете на първия отбор са в сектор А и Б, а на втория – В и Г.
    Да се напише програма, която изчислява процентите на феновете във всеки сектор,
    спрямо общия брой фенове на стадиона, както и общият процент на феновете за двата отбора,
    спрямо капацитета на стадиона. Общият брой на феновете НЕ надвишава капацитета на стадиона.
Вход:
    От конзолата се четат поредица от числа, всяко на отделен ред:
        1. Капацитетът на стадиона – цяло число в интервала [1 … 10000];
        2. Броят на всички фенове – цяло число в интервала [1 … 10000].
    За всеки един фен, на отделен ред се прочита:
        1. Секторът, на който се намира – текст – "A", "B", "V" и "G".
Изход:
    Да се отпечатат на конзолата 5 реда, всеки от които съдържа процент между 0.00% и 100.00%,
    форматирани до втората цифра след десетичната запетая:
        1. Процентът на феновете в сектор А
        2. Процентът на феновете в сектор Б
        3. Процентът на феновете в сектор В
        4. Процентът на феновете в сектор Г
        5. Процентът на всички фенове, спрямо капацитета на стадиона.
Примерен вход и изход:
    76
    10
    A
    V
    V
    V
    G
    B
    A
    V
    B
    B
    -> 20.00%
    -> 30.00%
    -> 40.00%
    -> 10.00%
    -> 13.16%
        Феновете в сектор А са 2. Изчисляваме какъв процент са от общия брой фенове – 10 на стадиона.
        2 / 10 * 100 = 20.00%
        Феновете в сектор B са 3.
        По същия начин изчисляваме техния процент спрямо общия брой на феновете.
         3 / 10 * 100 = 30.00%
        Феновете в сектор V са 4.
        По същия начин изчисляваме техния процент спрямо общия брой на феновете.
        4 / 10 * 100 = 40.00%
        В сектор G има само 1 фен.
        По същия начин изчисляваме какъв процент е спрямо общия брой на феновете.
        1/10 * 100 = 10.00%
        Изчисляваме процентът на всички фенове, спрямо капацитетът на стадиона:
        10/ 76 * 100 = 13.16%
    93
    16
    A
    V
    G
    G
    B
    B
    G
    B
    A
    B
    B
    B
    A
    B
    B
    A
    -> 25.00%
    -> 50.00%
    -> 6.25%
    -> 18.75%
    -> 17.20%
    1000
    12
    A
    A
    V
    V
    A
    G
    A
    A
    V
    G
    V
    A
    -> 50.00%
    -> 0.00%
    -> 33.33%
    -> 16.67%
    -> 1.20%
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FootballLeague {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int stadiumCapacity = setValue(1, 10000);
        int fanCount = setValue(1, 10000);
        int a = 0, b = 0, v = 0, g = 0;

        for (int fan = 1; fan <= fanCount; fan++) {
            String sector = setValue(null, null);
            switch (sector) {
                case "A": a++; break;
                case "B": b++; break;
                case "V": v++; break;
                case "G": g++; break;
            }
        }

        double aPercent = xPercentOfY(a, fanCount);
        double bPercent = xPercentOfY(b, fanCount);
        double vPercent = xPercentOfY(v, fanCount);
        double gPercent = xPercentOfY(g, fanCount);
        double fanToCapacityPercent = xPercentOfY(fanCount, stadiumCapacity);

        displayResult(aPercent, bPercent, vPercent, gPercent, fanToCapacityPercent);
    }

    private static double xPercentOfY(int x, int y) {
        return 100.0 * x / y;
    }

    private static void displayResult(
            double aPercent,
            double bPercent,
            double vPercent,
            double gPercent,
            double fanToCapacityPercent
    ) {
        out.printf("%.2f%%%n", aPercent);
        out.printf("%.2f%%%n", bPercent);
        out.printf("%.2f%%%n", vPercent);
        out.printf("%.2f%%%n", gPercent);
        out.printf("%.2f%%%n", fanToCapacityPercent);
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
                    required = new String[] {"A", "B", "V", "G"};
                if (stringCount > 1) {
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
