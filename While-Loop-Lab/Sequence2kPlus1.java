/*
Условие:
    Напишете програма, която чете число n, въведено от потребителя, и отпечатва всички числа ≤ n от редицата:
    1, 3, 7, 15, 31, …. Всяко следващо число се изчислява като умножим предишното с 2 и добавим 1.
Примерен вход и изход:
    3
    -> 1
    -> 3
    8
    -> 1
    -> 3
    -> 7
    17
    -> 1
    -> 3
    -> 7
    -> 15
    31
    -> 1
    -> 3
    -> 7
    -> 15
    -> 31
 */
package SoftUni.Lab11;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Sequence2kPlus1 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int k = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

        displayNumsInSequence(k);
    }

    private static void displayNumsInSequence(int k) {
        int i = 1;
        while (i <= k) {
            out.println(i);
            i = i * 2 + 1;
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
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            } */
        } else {
            try {
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
