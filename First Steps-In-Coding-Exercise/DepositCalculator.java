// Напишете програма, която изчислява каква сума ще получите в края на депозитния период при определен лихвен процент. Използвайте следната формула: 
// сума = депозирана сума  + срок на депозита * ((депозирана сума * годишен лихвен процент ) / 12)
    // Вход От конзолата се четат 3 реда:
        // 1. Депозирана сума – реално число в интервала [100.00 … 10000.00]
        // 2. Срок на депозита (в месеци) – цяло число в интервала [1…12]
        // 3. Годишен лихвен процент – реално число в интервала [0.00 …100.00]
    // Изход Да се отпечата на конзолата сумата в края на срока.
    // Примерен вход и изход
        // 200, 3, 5.7 ->  202.85
            // 1. Изчисляваме натрупаната лихва: 200 * 0.057 (5.7%) = 11.40 лв.
            // 2. Изчисляваме лихвата за 1 месец: 11.40 лв. / 12 месеца = 0.95 лв.
            // 3. Общата сума е: 200 лв. + 3 * 0.95 лв. = 202.85 лв.
        // 2350, 6, 7 -> 2432.25
            // 1. Изчисляваме натрупаната лихва: 2350 * 0.07 (7%) = 164.50 лв.
            // 2. Изчисляваме лихвата за 1 месец: 164.50 лв. / 12 месеца = 13.7083... лв.
            // 3. Общата сума е: 2350 лв. + 6 * 13.7083... лв. = 202.85 лв.

package Exe4;

import java.util.Scanner;
import static java.lang.System.out;

public class DepositCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double depSum = Double.parseDouble(scanner.nextLine());
        int srok = Integer.parseInt(scanner.nextLine());
        double godLihPro = Double.parseDouble(scanner.nextLine());
        out.println(calculate(depSum, srok, godLihPro));
    }
    private static double calculate(double depSum, int srok, double godLihPro) {
        return depSum * godLihPro / 100 / 12 * srok + depSum;
    }
}
