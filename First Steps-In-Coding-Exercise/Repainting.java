/*
Условие:
    Румен иска да пребоядиса хола и за целта е наел майстори. Напишете програма, която изчислява разходите за ремонта, предвид следните цени:
    • Предпазен найлон - 1.50 лв. за кв. метър
    • Боя - 14.50 лв. за литър
    • Разредител за боя - 5.00 лв. за литър
    За всеки случай, към необходимите материали, Румен иска да добави още 10% от количеството боя и 2 кв.м. найлон, разбира се и 0.40 лв. за торбички. Сумата, която се заплаща на майсторите за 1 час работа, е равна на 30% от сбора на всички разходи за материали.
Вход:
    Входът се чете от конзолата и съдържа точно 4 реда:
    1. Необходимо количество найлон (в кв.м.) - цяло число в интервала [1... 100]
    2. Необходимо количество боя (в литри) - цяло число в интервала [1…100]
    3. Количество разредител (в литри) - цяло число в интервала [1…30]
    4. Часовете, за които майсторите ще свършат работата - цяло число в интервала [1…9]
Изход:
    Да се отпечата на конзолата един ред:
    • "{сумата на всички разходи}"
Примерен вход и изход:
    10
    11
    4
    8
    -> 727.09
        Сума за найлон: (10 + 2) * 1.50 = 18 лв.
        Сума за боя: (11 + 10%) * 14.50 = 175.45 лв.
        Сума за разредител: 4 * 5.00 = 20.00 лв.
        Сума за торбички: 0.40 лв.
        Обща сума за материали: 18 + 175.45 + 20.00 + 0.40 = 213.85 лв.
        Сума за майстори: (213.85 * 30%) * 8 = 513.24 лв.
        Крайна сума: 213.85 + 513.24 = 727.09 лв.
    5
    10
    10
    1
    -> 286.52
        Сума за найлон: (5 + 2) * 1.50 = 10.50 лв.
        Сума за боя: (10 + 10%) * 14.50 = 159.50 лв.
        Сума за разредител: 10 * 5.00 = 50.00 лв.
        Сума за торбички: 0.40 лв.
        Обща сума за материали: 10.50 + 159.50 + 50.00 + 0.40 = 220.40 лв.
        Сума за майстори: (220.40 * 30%) * 1 = 66.12 лв.
        Крайна сума: 220.40 + 66.12 = 286.52 лв.
*/
package SoftUni.Exer4;

import java.util.Scanner;
import static java.lang.System.in;
import static java.lang.System.out;

public class Repainting {
    static Scanner scanner = new Scanner(in);
    
    public static void main(String[] args) {
        int naylon = setIntValue(1, 100, "найлон");
        int paint = setIntValue(1, 100, "боя");
        int thinner = setIntValue(1, 100, "разредител");
        int hours = setIntValue(1, 100, "чаове");
        
        double finalPrice = calculatePrice(naylon, paint, thinner, hours);

        out.printf("%.2f", finalPrice);
    }

    private static double calculatePrice(int naylon, int paint, int thinner, int hours) {
        int bonusNaylon = 2;
        double bonusPaint = 110/100.0;
        double priceOfNaylon = 1.50;
        double priceOfPaint = 14.50;
        double priceOfThinner = 5.00;
        double priceOfBags = 0.40;
        double builderPercentage = 30/100.0;

        double priceForNaylon = (naylon + bonusNaylon) * priceOfNaylon;
        double priceForPaint = paint * bonusPaint * priceOfPaint;
        double priceForThinner = thinner * priceOfThinner;
        double sumOfPrices = priceOfBags + priceForThinner + priceForPaint + priceForNaylon;
        double priceForBuilders = (sumOfPrices * builderPercentage) * hours;

        return sumOfPrices + priceForBuilders;
    }

    private static int setIntValue(int min, int max, String quantity) {
        int value;
        out.println("Въведи количество "+quantity+":");
        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, quantity);
        }
        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max, quantity);
        }
        else
            return value;
    }
}
