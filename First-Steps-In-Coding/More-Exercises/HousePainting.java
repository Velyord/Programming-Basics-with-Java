/*
Условие:
    Напишете програма, която да пресмята колко литра боя е нужна за боядисването на къщa.
    Като за стените се използва зелена боя, а за покрива – червена.
    Разхода на зелената боя е 1 литър за 3.4 м2, а на червената – 1 литър за 4.3 м2.
    Стените имат следните размери:
        • Предната и задната стена са квадрати със страна „x“
            ◦ на предната стена има правоъгълна врата с широчина 1.2м и височина 2м
        • Страничните стени са правоъгълници със страни „x“ и „y“
            ◦ и на двете странични стени има по един квадратен прозорец със страна 1.5м
    Покривът има следните размери:
        • Два правоъгълника със страни „x“ и „y“
        • Два равностранни триъгълника със страна „x“ и височина „h“
    Трябва да пресметнете площта на всички страни и площта на покрива, за да
    намерите колко литра от всяка боя ще са нужни.
Вход:
    От конзолата се четат 3 реда:
        1. x – височината на къщата – реално число в интервала [2...100]
        2. y – дължината на страничната стена – реално число в интервала [2...100]
        3. h – височината на триъгълната стена на прокрива – реално число в интервала [2...100]
Изход:
    Да се отпечатат на конзолата две числа всяко на нов ред:
        • Литрите зелена боя
        • Литритe червена боя
    Форматирани до вторият знак след десетичната запетая.
Примерен вход и изход:
    6
    10
    5.2
    -> 54.44
    -> 35.16
        СТЕНИ
        Страничната стена е с площ – 6*10 = 60;
        Прозорецът е с площ = 1.5*1.5 = 2.25;
        Двете страници са общо – 2*60 - 2*2.25 = 115.5;
        Задната стена – 6*6 = 36; Вход: 1.2*2 = 2.4
        Общо предна и задна – 2*36 - 2.4 = 69.6
        ОБЩА ПЛОЩ: 115.5 + 69.6 = 185.1 м2
        Зелена боя = 185.1 / 3.4 = 54.44 литра
        ПОКРИВ
        Двата правоъгълника на покрива – 2 * (6*10) = 120
        Двата триъгълниците – 2 * (6*5.2 / 2) = 2 * 15.6 = 31.2
        ОБЩА ПЛОЩ: 120 + 31.2 = 151.2 м2
        Червена боя = 151.2 / 4.3 = 35.16 литра
    10.25
    15.45
    8.88
    -> 152.93
    -> 94.82
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.Math.pow;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class HousePainting {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double x = setValue(2.0, 100.0);
        double y = setValue(2.0, 100.0);
        double h = setValue(2.0, 100.0);

        double lGreenPaint = calcBase(x, y, h);
        double lRedPaint = calcRoof(x, y, h);

        out.printf("%.2f\n%.2f", lGreenPaint, lRedPaint);
    }

    private static double calcRoof(double x, double y, double h) {
        double mRedPaintForL = 4.3;

        double areaOfRectangleSide = x * y;
        double areaOfTriangleSide = x * h / 2;
        double areaOfRoof = areaOfRectangleSide * 2 + areaOfTriangleSide * 2;

        return areaOfRoof / mRedPaintForL;
    }

    private static double calcBase(double x, double y, double h) {
        double mGreenPaintForL = 3.4;

        double areaOfSquareSide = pow(x, 2);
        double areaOfRectangleSide = x * y;
        double areaOfBase = areaOfSquareSide * 2 + areaOfRectangleSide * 2;
        double areaDoor = 1.2 * 2;
        double areaWindow = pow(1.5, 2);
        double areaOfBaseWithoutDoorAndWindows = areaOfBase - areaDoor - areaWindow * 2;

        return areaOfBaseWithoutDoorAndWindows / mGreenPaintForL;
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
