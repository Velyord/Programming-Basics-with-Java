/*
Условие:
    Да се напише програма, която чете n-на брой цели числа, подадени от потребителя и проверява дали сумата от числата на четни позиции е равна на сумата на числата на нечетни позиции.
    •	Ако сумите са равни да се отпечатат два реда: "Yes" и на нов ред "Sum = " + сумата;
    •	Ако сумите не са равни да се отпечат два реда: "No" и на нов ред "Diff = " + разликата.
    Разликата се изчислява по абсолютна стойност.
Примерен вход и изход:
    4
    10
    50
    60
    20
    -> Yes
    -> Sum = 70
        10+60 = 50+20 = 70
    4
    3
    5
    1
    -2
    -> No
    -> Diff = 1
        3+1 ≠ 5-2
        Diff = |4-3| = 1
    3
    5
    8
    1
    -> No
    -> Diff = 2
        5+1 ≠ 8
        Diff = |6-8| = 2
 */
package SoftUni.Lab9;

import static java.lang.Math.abs;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class OddEvenSum {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int nums = setValue(0, Integer.MAX_VALUE);

        calculateTask(nums);
    }

    private static void calculateTask(int nums) {
        int odd = 0, even = 0;

        for (int i=0; i<nums; i++) {
            int num = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (i % 2 != 0)
                odd += num;
            else
                even += num;
        }

        displayResult(odd, even);
    }

    private static void displayResult(int odd, int even) {
        if (odd == even)
            out.printf("Yes\nSum = %d", odd);
        else
            out.printf("No\nDiff = %d", abs(odd - even));
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
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
