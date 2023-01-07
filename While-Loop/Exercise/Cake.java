/*
Условие:
    Поканени сте на 30-ти рожден ден, на който рожденикът черпи с огромна торта. Той обаче не знае колко парчета могат да си вземат гостите от нея. Вашата задача е да напишете програма, която изчислява броя на парчетата, които гостите са взели, преди тя да свърши. Ще получите размерите на тортата в сантиметри (широчина и дължина – цели числа в интервала [1...1000]) и след това на всеки ред, до получаване на командата "STOP" или докато не свърши тортата, броят на парчетата, които гостите вземат от нея. Парчетата са квадратни с размер  1 см .
    Да се отпечата на конзолата един от следните редове:
    •	"{брой парчета} pieces are left." - ако стигнете до STOP и има останали парчета торта.
    •	"No more cake left! You need {брой недостигащи парчета} pieces more."
Примерен вход и изход:
    10
    10
    20
    20
    20
    20
    21
    -> No more cake left! You need 1 pieces more.
        Тортата е с дължина 10 и широчина 10
        => броят на парчетата = 10 * 10 = 100
        1-во вземане -> 100 - 20 = 80
        2-ро вземане -> 80 - 20 = 60
        3-то вземане -> 60 - 20 = 40
        4-то вземане -> 40 - 20 = 20
        5-то вземане -> 20 - 21 = -1 < 0
        => не остава повече торта, 1 парче не достига
    10
    2
    2
    4
    6
    STOP
    -> 8 pieces are left.
        Тортата е с дължина 10 и широчина 2
        => броят на парчетата = 10 * 2 = 20
        1-во вземане -> 20 - 2 = 18
        2-ро вземане -> 18 - 4 = 14
        3-то вземане -> 14 - 6 = 8
        4-то вземане -> команда STOP
        =>останали парчета: 8
 */
package SoftUni.Exer12;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Cake {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int height = setValue(0, 1000);
        int width = setValue(0, 1000);
        int pieces = height * width;

        treatGuests(pieces);
    }

    private static void treatGuests(int pieces) {
        while (pieces > 0) {
            String taken = scanner.nextLine();
            if (taken.equals("STOP")) {
                out.printf("%d pieces are left.", pieces);
                break;
            } else {
                int takenInNumber = Integer.parseInt(taken);
                pieces -= takenInNumber;
                if (pieces < 0)
                    out.printf("No more cake left! You need %d pieces more.", abs(pieces));
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
