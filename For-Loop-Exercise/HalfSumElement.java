/*
Условие:
    Да се напише програма, която чете n-на брой цели числа, въведени от потребителя, и проверява дали сред тях
    съществува число, което е равно на сумата на всички останали.
    •	Ако има такъв елемент печата "Yes" и на нов ред "Sum = "  + неговата стойност
    •	Ако няма такъв елемент печата "No" и на нов ред "Diff = " + разликата между най-големия елемент и сумата на
    останалите (по абсолютна стойност)
Примерен вход и изход:
    7
    3
    4
    1
    1
    2
    12
    1
    -> Yes
    -> Sum = 12
        3 + 4 + 1 + 2 + 1 + 1 = 12
    4
    6
    1
    2
    3
    -> Yes
    -> Sum = 6
        1 + 2 + 3 = 6
    3
    1
    1
    10
    -> No
    -> Diff = 8
        |10 - (1 + 1)| = 8
    3
    5
    5
    1
    -> No
    -> Diff = 1
        |5 - (5 + 1)| = 1
    3
    1
    1
    1
    -> No
    -> Diff = 1
 */
package SoftUni.Exer10;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class HalfSumElement {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int nums = setValue(0, Integer.MAX_VALUE);

        checkIfSumEquelsMax(nums);
    }

    private static void checkIfSumEquelsMax(int nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;

        for (int i=0; i<nums; i++) {
            int num = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
            sum += num;
            if (num > max)
                max = num;
        }

        sum -= max;

        displayResult(sum, max);
    }

    private static void displayResult(int sum, int max) {
        if (sum == max)
            out.printf("Yes\nSum = %d", sum);
        else
            out.printf("No\nDiff = %d", abs(sum - max));
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

            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            }
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
