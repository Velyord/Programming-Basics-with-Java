/*
Условие:
    Георги ще има гости вечерта и решава да ги нагости с паламуд, сафрид и миди.
    Затова отива на рибната борса, за да си купи по няколко килограма.
    Oт конзолата се въвеждат цените в лева на скумрията и цацата.
    Също количеството на паламуд, сафрид и миди в килограми.
    Колко пари ще са му необходими, за да плати сметката си, ако цените на борсата са:
    • Паламуд – 60% по-скъп от скумрията
    • Сафрид – 80% по-скъп от цацата
    • Миди – 7.50 лв. за килограм
Вход:
    От конзолата се четат 5 числа:
        • Първи ред – цена на скумрията на килограм. Реално число в интервала [0.00…40.00]
        • Втори ред – цена на цацата на килограм. Реално число в интервала [0.00…30.00]
        • Трети ред – килограма паламуд. Реално число в интервала [0.00…50.00]
        • Четвърти ред – килограма сафрид. Реално число в интервала [0.00… 70.00]
        • Пети ред – килограма миди. Цяло число в интервала [0 ... 100]
Изход:
    Да се отпечата на конзолата едно число с плаваща запетая: колко пари ще са нужни на Георги, за да си плати сметката.
    Числото трябва да е форматирано до вторият знак след десетичната запетая (1.2457 -> 1.25).
Примерен вход и изход:
    6.90
    4.20
    1.5
    2.5
    1
    -> 42.96
        Цена на паламуда = 6.90 + 6.90 * 0.60 = 11.04 лв. за килограм
        Сума паламуд = 1.5 * 11.04 = 16.56
        Цена на сафрид = 4.20 + 4.20 * 0.80 =  7.56 лв. за килограм
        Сума сафрид = 2.5 * 7.56 = 18.90
        Сума миди = 1 * 7.50 = 7.50
        Сметка = 16.56 + 18.90 + 7.50 = 42.96
    5.55
    3.57
    4.3
    3.6
    7
    -> 113.82
    7.79
    5.35
    9.3
    0
    0
    -> 115.92
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Fishland {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double skumriqPriceOfKg = setValue(0.00, 40.00);
        double cacaPriceOfKg = setValue(0.00, 30.00);
        double palamudKg = setValue(0.00, 50.00);
        double safridKg = setValue(0.00, 70.00);
        int midiKg = setValue(0, 100);

        double finalPrice =
            calcFinalPrice(
                skumriqPriceOfKg,
                cacaPriceOfKg,
                palamudKg,
                safridKg,
                midiKg
            );

        out.printf("%.2f", finalPrice);
    }

    private static double calcFinalPrice(double skumriqPriceOfKg, double cacaPriceOfKg, double palamudKg, double safridKg, int midiKg) {
        double palamudPriceOfKg = skumriqPriceOfKg * 1.6;
        double safridPriceOfKg = cacaPriceOfKg * 1.8;
        double midiPriceOfKg = 7.50;

        double palamudPrice = palamudPriceOfKg * palamudKg;
        double safridPrice = safridPriceOfKg * safridKg;
        double midiPrice = midiPriceOfKg * midiKg;

        return palamudPrice + safridPrice + midiPrice;
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
        }
        else {
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
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
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
