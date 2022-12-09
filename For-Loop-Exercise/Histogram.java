/*
Условие:
    Дадени са n цели числа в интервала [1…1000]. От тях някакъв процент p1 са под 200, друг процент p2 са от 200 до 399,
    друг процент p3 са от 400 до 599, друг процент p4 са от 600 до 799 и останалите p5 процента са от 800 нагоре.
    Да се напише програма, която изчислява и отпечатва процентите p1, p2, p3, p4 и p5.
    Пример: имаме n = 20 числа: 53, 7, 56, 180, 450, 920, 12, 7, 150, 250, 680, 2, 600, 200, 800, 799, 199, 46, 128, 65.
    Получаваме следното разпределение и визуализация:
    Диапазон	Числа в диапазона	Брой числа	Процент
        < 200
        53, 7, 56, 180, 12, 7, 150, 2, 199, 46, 128, 65
        12
        p1 = 12 / 20 * 100 = 60.00%

        200 … 399
        250, 200
        2
        p2 = 2 / 20 * 100 = 10.00%

        400 … 599
        450
        1
        p3 = 1 / 20 * 100 = 5.00%

        600 … 799
        680, 600, 799
        3
        p4 = 3 / 20 * 100 = 15.00%

        ≥ 800
        920, 800
        2
        p5 = 2 / 20 * 100 = 10.00%
Вход:
    На първия ред от входа стои цялото число n (1 ≤ n ≤ 1000) – брой числа. На следващите n реда стои по едно цяло число
    в интервала [1…1000] – числата върху които да бъде изчислена хистограмата.
Изход:
    Да се отпечата на конзолата хистограмата – 5 реда, всеки от които съдържа число между 0% и 100%, с точност две цифри
    след десетичната точка, например 25.00%, 66.67%, 57.14%.
Примерен вход и изход:
    3
    1
    2
    999
    -> 66.67%
    -> 0.00%
    -> 0.00%
    -> 0.00%
    -> 33.33%
    4
    53
    7
    56
    999
    -> 75.00%
    -> 0.00%
    -> 0.00%
    -> 0.00%
    -> 25.00%
    7
    800
    801
    250
    199
    399
    599
    799
    -> 14.29%
    -> 28.57%
    -> 14.29%
    -> 14.29%
    -> 28.57%
    9
    367
    99
    200
    799
    999
    333
    555
    111
    9
    -> 33.33%
    -> 33.33%
    -> 11.11%
    -> 11.11%
    -> 11.11%
    14
    53
    7
    56
    180
    450
    920
    12
    7
    150
    250
    680
    2
    600
    200
    -> 57.14%
    -> 14.29%
    -> 7.14%
    -> 14.29%
    -> 7.14%
 */
package SoftUni.Exer10;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Histogram {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int nums = setValue(1, 1000);

        calculateHistogram(nums);
    }

    private static void calculateHistogram(int nums) {
        int g1 = 0, g2 = 0, g3 = 0, g4 = 0, g5 = 0;

        for (int i=0; i<nums; i++) {
            int num = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (num < 200)      g1++;
            else if (num < 400) g2++;
            else if (num < 600) g3++;
            else if (num < 800) g4++;
            else                g5++;
        }

        double p1 = (double) g1 / nums * 100;
        double p2 = (double) g2 / nums * 100;
        double p3 = (double) g3 / nums * 100;
        double p4 = (double) g4 / nums * 100;
        double p5 = (double) g5 / nums * 100;

        displayResult(p1, p2, p3, p4, p5);
    }

    private static void displayResult(double p1, double p2, double p3, double p4, double p5) {
        out.printf("%.2f%%%n", p1);
        out.printf("%.2f%%%n", p2);
        out.printf("%.2f%%%n", p3);
        out.printf("%.2f%%%n", p4);
        out.printf("%.2f%%%n", p5);
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
