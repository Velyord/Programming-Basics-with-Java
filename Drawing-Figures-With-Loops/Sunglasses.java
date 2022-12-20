/*
Условие:
    Напишете програма, която чете цяло число n (3 ≤ n ≤ 100), въведено от потребителя,
    и печата слънчеви очила с размер 5*n x n като в примерите:
Примерен вход и изход:
    https://imgur.com/a/LoUhDSN
*/
package SoftUni.MoreExercises.DrawingFiguresWithLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Sunglasses {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int n = setValue(3, 100);
        int i;
        for (i = 0; i < 2 * n; i++)
            out.print("*");
        for (i = 0; i < n; i++)
            out.print(" ");
        for (i = 0; i < 2 * n; i++)
            out.print("*");
        out.println();
        for (int j = 0; j < n - 2; j++) {
            out.print("*");
            for (i = 0; i < 2 * n - 2; i++)
                out.print("/");
            out.print("*");
            if (j == (n - 1) / 2 - 1)
                for (i = 0; i < n; i++)
                    out.print("|");
            else
                for (i = 0; i < n; i++)
                    out.print(" ");            
            out.print("*");
            for (i = 0; i < 2 * n - 2; i++)
                out.print("/");
            out.print("*");
            out.println();
        }
        for (i = 0; i < 2 * n; i++)
            out.print("*");
        for (i = 0; i < n; i++)
            out.print(" ");
        for (i = 0; i < 2 * n; i++)
            out.print("*");
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
