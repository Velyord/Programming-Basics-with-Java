/*
Условие:
    За рождения си ден Любомир получил аквариум с формата на паралелепипед. Първоначално прочитаме от конзолата на отделни редове размерите му – дължина, широчина и височина в сантиметри. Трябва да се пресметне колко литра вода ще събира аквариума, ако се знае, че определен процент от вместимостта му е заета от пясък, растения, нагревател и помпа.
    Един литър вода се равнява на един кубичен дециметър/ 1л=1 дм3/.
    Да се напише програма, която изчислява литрите вода, която са необходими за напълването на аквариума.
Вход:
    От конзолата се четат 4 реда:
    1. Дължина в см – цяло число в интервала [10 … 500]
    2. Широчина в см – цяло число в интервала [10 … 300]
    3. Височина в см – цяло число в интервала [10… 200]
    4. Процент  – реално число в интервала [0.000 … 100.000]
Изход:
    Да се отпечата на конзолата едно число:
    • литрите вода, които ще събира аквариума.
Примерен вход и изход:
    85
    75
    47
    17
    -> 248.68875
        обем на аквариумa: 85 * 75 * 47 = 299625 см3
        обем в литри: 299625 * 0.001 или  299625 / 1000 => 299.625 литра
        заето пространство: 17% = 0.17
        нужни литри: 299.625 * (1 - 0.17) = 248.68875 литра
    105
    77
    89
    18.5
    -> 586.445475
        обем на аквариумa: 105 * 77 * 89 = 719565 см3
        обем в литри: 719565 * 0.001  или 719565 / 1000 => 719.565 литра
        заето пространство: 18.5% = 0.185
        нужни литри: 719.565 * (1 - 0.185) = 586.445475 литра
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class FishTank {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int duljina = setIntValue(10, 500, "дължина");
        int shirina = setIntValue(10, 300, "ширина");
        int visochina = setIntValue(10, 200, "височина");
        double procent = setDoubleValue(0, 100, "процент");

        double output = calculateNujniLitri(duljina, shirina, visochina, procent);

        out.println(output);
    }
    private static double calculateNujniLitri(int duljina, int shirina, int visochina, double procent) {
        int obem = duljina * shirina * visochina;
        double litri = obem * 0.001;
        double procentVoda = 1 - procent / 100.0;

        return litri * procentVoda;
    }

    private static int setIntValue(int min, int max, String object) {
        int value;
        out.println("Въведете " + object + ":");

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, object);
        }
        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!\n", min, max);
            return setIntValue(min, max, object);
        }
        else
            return value;
    }

    private static double setDoubleValue(double min, double max, String object) {
        double value;
        out.println("Въведете " + object + ":");

        try {
            value = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setDoubleValue(min, max, object);
        }
        
        if (value < min || value > max) {
            out.printf("Моля въведе число между %f и %f!\n", min, max);
            return setDoubleValue(min, max, object);
        }
        else
            return value;
    }
}
