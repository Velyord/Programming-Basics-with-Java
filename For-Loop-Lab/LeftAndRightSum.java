/*
Условие:
    Да се напише програма, която чете 2 * n-на брой цели числа, подадени от потребителя, и проверява дали сумата на
    първите n числа (лява сума) е равна на сумата на вторите n числа (дясна сума). При равенство печата
    " Yes, sum = " + сумата; иначе печата " No, diff = " + разликата. Разликата се изчислява като положително число
    (по абсолютна стойност).
Примерен вход и изход:
    2
    10
    90
    60
    40
    -> Yes, sum = 100
        10+90 = 60+40 = 100
    2
    90
    9
    50
    50
    -> No, diff = 1
        90+9 ≠ 50+50
        Difference = |99-100| = 1

 */
package SoftUni.Lab9;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class LeftAndRightSum {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int nums = setValue("int", true);

        calculateTask(nums);
    }

    private static void calculateTask(int nums) {
        int left = 0, right = 0, num = 0;

        for (int i=0; i<nums; i++) {
            num = setValue("int", false);
            left += num;
        }
        for (int i=0; i<nums; i++) {
            num = setValue("int", false);
            right += num;
        }

        displayResult(left, right);
    }

    private static void displayResult(int left, int right) {
        if (left == right) {
            out.printf("Yes, sum = %d", left);
        } else {
            out.printf("No, diff = %d", abs(left - right));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(String type, Boolean positiveOnly) {
        Object value;
        // out.println("Въведете :");

        try {
            if (type.equals("int"))
                value = Integer.parseInt(scanner.nextLine());
            else if (type.equals("double"))
                value = Double.parseDouble(scanner.nextLine());
            else {
                out.println("Моля въведете един от следните типове [int, double]:");
                return setValue(type, positiveOnly);
            }

        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(type, positiveOnly);
        }

        if (type.equals("int") && positiveOnly) {
            if ((int) value < 0) {
                out.println("Моля въведете положително число между");
                return setValue(type, positiveOnly);
            }
        } else if (type.equals("double") && positiveOnly){
            if ((double) value < 0) {
                out.println("Моля въведете положително число между");
                return setValue(type, positiveOnly);
            }
        }

        return (T) value;
    }
}
