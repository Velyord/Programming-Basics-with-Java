/*
Условие:
    Като един истински запалянко Пепи решил да се подготви за световното първенство,
    като си закупи екип на любимия си футболен отбор. В магазина, в който пазарувал предлагали
    тениски, шорти, чорапи и бутонки. Знае се, че цената на шортите е 75% от цената на тениските, а
    цената на чорапите е 20% от цената на шортите. Бутонките струват два пъти колкото тениската и шортите взети заедно.
    Тъй като Пепи редовно пазарува от този магазин, той има карта за отстъпка на стойност 15% от общата сума на
    покупката. Ако сметката на Пепи е по-висока или равна на дадена сума, той получава подарък – точно копие на топката
    от световното. Напишете програма, която изчислява дали Пепи е спечелил топката.
Вход:
    Входът се чете от конзолата и съдържа точно 2 реда:
    •	На първия ред - цената на тениската – реално число в интервала [1.00 ... 1000.00]
    •	На втория ред - сумата, която трябва да достигне,
    за да спечели топка – реално число в интервала [100.00 ... 10 000.00]
Изход:
    На конзолата се отпечатват два реда:
    •	Ако топката Е спечелена:
    o	"Yes, he will earn the world-cup replica ball!"
    o	"His sum is {сумата} lv."
    •	Ако  топката НЕ Е спечелена:
    o	"No, he will not earn the world-cup replica ball."
    o	"He needs {недостигащи пари} lv. more."
    Резултатът да бъде форматиран до втората цифра след десетичната запетая.
Примерен вход и изход:
    25
    100
    -> Yes, he will earn the world-cup replica ball!
    -> His sum is 114.75 lv.
        Цена на тениската: 25
        Цена на шортите: 25 * 0.75 = 18.75 лв.
        Цена на чорапите: 18.75 * 0.20 = 3.75 лв.
        Цена на бутонките: (25 + 18.75) * 2 = 87.5 лв.
        Обща сума: 25 + 18.75 + 3.75 + 87.5 = 135 лв.
        Сума след отстъпката: 135 лв. - 15% = 114.75 лв.
        114.75 >= 100 => Пепи печели топката.
    55
    310
    -> No, he will not earn the world-cup replica ball.
    -> He needs 57.55 lv. more.
        Цена на тениската: 55
        Цена на шортите: 55 * 0.75 = 41.25 лв.
        Цена на чорапите: 41.25 * 0.20 = 8.25 лв.
        Цена на бутонките: (55 + 41.25) * 2 = 192.50 лв.
        Обща сума: 55 + 41.25  + 8.25 + 192.50 = 297 лв.
        Сума след отстъпката: 297 лв. - 15% = 252.45 лв.
        252.45 < 310 => Пепи не печели топката.
        Нужна сума: 310 – 252.45 = 57.55 лв.
    59.99
    500
    -> No, he will not earn the world-cup replica ball.
    -> He needs 224.65 lv. more.
*/
package SoftUni.Exam;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class FootballKit {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double priceOfTShirt =
            Double.parseDouble(scanner.nextLine());
        double priceGoal =
            Double.parseDouble(scanner.nextLine());

        checkIfGoalMet(priceOfTShirt, priceGoal);
    }

    private static void checkIfGoalMet(
        double priceOfTShirt,
        double priceGoal
    ) {
        double shorts = priceOfTShirt * 0.75;
        double socks = shorts * 0.20;
        double shoes = (priceOfTShirt + shorts) * 2;
        double percentDiscount = 15;
        double priceForKitBeforeDiscount =
            priceOfTShirt + shorts + socks + shoes;
        double discountPrice =
            priceForKitBeforeDiscount * percentDiscount / 100;
        double priceForKitAfterDiscount =
            priceForKitBeforeDiscount - discountPrice;

        displayResult(priceForKitAfterDiscount, priceGoal);
    }

    private static void displayResult(
        double priceForKitAfterDiscount, 
        double priceGoal
    ) {
        double priceDifference =
            priceGoal - priceForKitAfterDiscount;
        if (priceForKitAfterDiscount >= priceGoal) {
            out.println(
                "Yes, he will earn the world-cup replica ball!"
            );
            out.printf(
                "His sum is %.2f lv.",
                priceForKitAfterDiscount
            );
        } else {
            out.println(
                "No, he will not earn the world-cup replica ball."
            );
            out.printf(
                "He needs %.2f lv. more.",
                priceDifference
            );
        }
    }
}
