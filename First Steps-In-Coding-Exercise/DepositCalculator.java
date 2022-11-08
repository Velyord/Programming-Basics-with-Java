/*
Условие:
    Напишете програма, която изчислява каква сума ще получите в края на депозитния период при определен лихвен процент. Използвайте следната формула:
    сума = депозирана сума  + срок на депозита * ((депозирана сума * годишен лихвен процент ) / 12)
Вход:
    От конзолата се четат 3 реда:
    1. Депозирана сума – реално число в интервала [100.00 … 10000.00]
    2. Срок на депозита (в месеци) – цяло число в интервала [1…12]
    3. Годишен лихвен процент – реално число в интервала [0.00 …100.00]
Изход:
    Да се отпечата на конзолата сумата в края на срока.
Примерен вход и изход:
    200
    3
    5.7
    ->  202.85
        1. Изчисляваме натрупаната лихва: 200 * 0.057 (5.7%) = 11.40 лв.
        2. Изчисляваме лихвата за 1 месец: 11.40 лв. / 12 месеца = 0.95 лв.
        3. Общата сума е: 200 лв. + 3 * 0.95 лв. = 202.85 лв.
    2350
    6
    7
    -> 2432.25
        1. Изчисляваме натрупаната лихва: 2350 * 0.07 (7%) = 164.50 лв.
        2. Изчисляваме лихвата за 1 месец: 164.50 лв. / 12 месеца = 13.7083... лв.
        3. Общата сума е: 2350 лв. + 6 * 13.7083... лв. = 202.85 лв.
*/
package SoftUni.Exer4;

import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

public class DepositCalculator {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double depSum = setDepSum();
        int srok = setSrok();
        double godLihPro = setGodLihPro();

        double output = calculate(depSum, srok, godLihPro);
        out.println(output);
    }
    private static double calculate(double depSum, int srok, double godLihPro) {
        return depSum + depSum * (godLihPro / 100) / 12 * srok;
    }
    private static double setDepSum() {
        double depSum;

        try {
            depSum = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setDepSum();
        }
        if (depSum < 100 || depSum > 10000) {
            out.println("Моля въведе число между 100 и 10000!");
            return setDepSum();
        }
        else
            return depSum;
    }
    private static int setSrok() {
        int srok;

        try {
            srok = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setSrok();
        }
        if (srok < 1 || srok > 12) {
            out.println("Моля въведе число между 1 и 12!");
            return setSrok();
        }
        else
            return srok;
    }
    private static double setGodLihPro() {
        double godLihPro;

        try {
            godLihPro = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setGodLihPro();
        }
        if (godLihPro < 0 || godLihPro > 100) {
            out.println("Моля въведе число между 0 и 100!");
            return setGodLihPro();
        }
        else
            return godLihPro;
    }
}
