/*
Условие:
    Иванчо е на 18 години и получава наследство, което се състои от X сума пари и машина на времето.
    Той решава да се върне до 1800 година, но не знае дали парите ще са достатъчни,
    за да живее без да работи. Напишете програма, която пресмята, дали Иванчо ще има достатъчно пари,
    за да не се налага да работи до дадена година включително. Като приемем,
    че за всяка четна (1800, 1802 и т.н.) година ще харчи 12 000 лева.
    За всяка нечетна (1801,1803  и т.н.)
    ще харчи 12 000 + 50 * [годините, които е навършил през дадената година].
Вход:
    Входът се чете от конзолата и съдържа точно 2 реда:
        • Наследените пари – реално число в интервала [1.00 ... 1 000 000.00]
        • Годината, до която трябва да живее (включително) – цяло число в интервала [1801 ... 1900]
Изход:
    Да се отпечата на конзолата 1 ред.
    Сумата трябва да е форматирана до два знака след десетичната запетая:
        • Ако парите са достатъчно:
            ◦ "Yes! He will live a carefree life and will have {N} dollars left." –
            където N са парите, които ще му останат.
        • Ако парите НЕ са достатъчно:
            ◦ "He will need {М} dollars to survive." – където M е сумата, която НЕ достига.
Примерен вход и изход:
50000
1802
-> Yes! He will live a carefree life and will have 13050.00 dollars left.
    1800 → четна
             → Харчи 12000 лева
             → Остават 50000 – 12000 = 38000
    1801 → нечетна
             → Харчи 12000 + 19*50 = 12000 + 950 = 12950
             → Остават 38000 – 12950 = 25050
    1802 → четна
             → Харчи 12000 лева
             → Остават 25050 – 12000 = 13050
100000.15
1808
-> He will need 12399.85 dollars to survive.
    1800 → четна
             → Остават 100000.15 – 12000 = 88000.15
    1801 → нечетна
             → Остават 100000.15 – 12950 = 87050.15
    ...
    1808 → четна → -399.85 – 12000 = -12399.85
    12399.85 не достигат
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class BackToThePast {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double money = Double.parseDouble(scanner.nextLine());
        int livesTo = Integer.parseInt(scanner.nextLine());

        isMoneyEnough(money, livesTo);
    }

    private static void isMoneyEnough(double money, int livesTo) {
        int ivansAge = 18;
        int backTo = 1800;

        for(int i=backTo; i<=livesTo; i++) {
            if(i % 2 == 0)
                money -= 12000;
            else
                money -= 12000 + 50 * ivansAge;
            ivansAge++;
        }

        displayResult(money);
    }

    private static void displayResult(double money) {
        boolean moneyIsEnough = money >= 0;
        if(moneyIsEnough)
            out.printf("Yes! He will live a carefree life and will have %.2f dollars left.", money);
        else
            out.printf("He will need %.2f dollars to survive.", abs(money));
    }
}
