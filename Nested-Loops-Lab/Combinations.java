/*
Условие:
    Напишете програма, която изчислява колко решения в естествените числа (включително и нулата) има уравнението:
    x1 + x2 + x3 = n
    Числото n е цяло число и се въвежда от конзолата.
Примерен вход и изход:
    25
    -> 351
        Генерираме всички комбинации от 3 числа, като първата е:
        0+0+0=0, но понеже не е равна на 25, продължаваме:
        0+0+1=1 – също не е 25 и т.н.
        Стигаме до първата валидна комбинация:
        0 + 0 + 25 = 25, увеличаваме броя на валидни комбинации с 1,втората валидна комбинация е:
        0 + 1 + 24 = 25
        Третата:
        0 + 2 + 23 = 25  и т.н.
        След генериране на всички възможни комбинации, броят на валидните е 351.
    20
    -> 231
    5
    -> 21
 */
package SoftUni.Lab13;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Combinations {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int n = setValue(Integer.MIN_VALUE, Integer.MAX_VALUE);
        int c = 0;

        for (int x1=0; x1<=n; x1++)
            for (int x2=0; x2<=n; x2++)
                for (int x3=0; x3<=n; x3++)
                    if (x1 + x2 + x3 == n)
                        c++;

        out.println(c);
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

                out.println();
                return setValue(null, null);
            } */
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
                    if ((int) min == 0 && (int) max == Integer.MAX_VALUE)
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
