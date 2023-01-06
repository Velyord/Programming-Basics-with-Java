/*
Условие:
    От лозе с площ X квадратни метри се заделя 40% от реколтата за производство на вино.
    От 1 кв.м лозе се изкарват Y килограма грозде. За 1 литър вино са нужни 2,5 кг. грозде.
    Желаното количество вино за продан е Z литра.
    Напишете програма, която пресмята
    колко вино може да се произведе и дали това количество е достатъчно.
    Ако е достатъчно, остатъкът се разделя по равно между работниците на лозето.
Вход:
    Входът се чете от конзолата и се състои от точно 4 реда:
        • 1ви ред: X кв.м е лозето – цяло число в интервала [10 … 5000]
        • 2ри ред: Y грозде за един кв.м – реално число в интервала [0.00 … 10.00]
        • 3ти ред: Z нужни литри вино – цяло число в интервала [10 … 600]
        • 4ти ред: брой работници – цяло число в интервала [1 … 20]
Изход:
    На конзолата трябва да се отпечата следното:
        • Ако произведеното вино е по-малко от нужното:
            ◦ “It will be a tough winter! More {недостигащо вино} liters wine needed.”
                ▪ Резултатът трябва да е закръглен към по-ниско цяло число
        • Ако произведеното вино е колкото или повече от нужното:
            ◦ “Good harvest this year! Total wine: {общо вино} liters.”
                ▪ Резултатът трябва да е закръглен към по-ниско цяло число
            ◦ “{Оставащо вино} liters left -> {вино за 1 работник} liters per person.”
                ▪ И двата резултата трябва да са закръглени към по-високото цяло число
Примерен вход и изход:
    650
    2
    175
    3
    -> Good harvest this year! Total wine: 208 liters.
    -> 33 liters left -> 11 liters per person.
        Общо грозде: 650 * 2 = 1 300
        Вино = 40% * 1300 / 2,5 = 208
        208 > 175
        208 - 175 = 33 л остават -> 11 л на човек
    1020
    1.5
    425
    4
    -> It will be a tough winter! More 180 liters wine needed.
        Общо грозде: 1 020 * 1.5 = 1 530
        Вино = 40% * 1 530 / 2,5 = 244.80
        244.80 < 425
        425 - 244.8 = 180.2 -> 180 л не достигат
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Harvest {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int X = setValue(10, 5000);
        double Y = setValue(0.0, 10.0);
        int Z = setValue(10, 600);
        int workers = setValue(1, 20);

        harvest(X, Y, Z, workers);
    }

    private static void harvest(int x, double y, int z, int workers) {
        double vinePercent = 40;
        double kgNeededGrapesFor1LVine = 2.5;

        double allGrapes = x * y;
        double vine = vinePercent / 100 * allGrapes / kgNeededGrapesFor1LVine;
        double leftVine = vine - z;
        double litersPerPerson = leftVine / workers;
        double vineNeeded = z - vine;

        double vineCeiled = ceil(vine);
        double leftVineCeiled = ceil(leftVine);
        double litersPerPersonCeiled = ceil(litersPerPerson);
        double vineNeededFloored = floor(vineNeeded);

        if (leftVine >= 0){
            out.printf("Good harvest this year! Total wine: %.0f liters.\n", vineCeiled);
            out.printf("%.0f liters left -> %.0f liters per person.", leftVineCeiled, litersPerPersonCeiled);
        } else
            out.printf("It will be a tough winter! More %.0f liters wine needed.", vineNeededFloored);

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
