/*
Условие:
    Иван решава да подобри Световния рекорд по плуване на дълги разстояния.
    На конзолата се въвежда рекордът в секунди, който Иван трябва да подобри,
    разстоянието в метри, което трябва да преплува и времето в секунди,
    за което плува разстояние от 1 м. Да се напише програма, която изчислява
    дали се е справил със задачата, като се има предвид, че:
    съпротивлението на водата го забавя на всеки 15 м. с 12.5 секунди.
    Когато се изчислява колко пъти Иван ще се забави, в резултат на съпротивлението на водата,
    резултатът трябва да се закръгли надолу до най-близкото цяло число.
    Да се изчисли времето в секунди, за което Иван ще преплува разстоянието и разликата спрямо
    Световния рекорд.
Вход:
    От конзолата се четат 3 реда:
        1. Рекордът в секунди – реално число в интервала [0.00 … 100000.00]
        2. Разстоянието в метри – реално число в интервала [0.00 … 100000.00]
        3. Времето в секунди, за което плува разстояние от 1 м. - реално число в интервала [0.00 … 1000.00]
Изход:
    Отпечатването на конзолата зависи от резултата:
        • Ако Иван е подобрил Световния рекорд (времето му е по-малко от рекорда) отпечатваме:
            ◦ "Yes, he succeeded! The new world record is {времето на Иван} seconds."
        • Ако НЕ е подобрил рекорда (времето му е по-голямо или равно на рекорда) отпечатваме:
            ◦ "No, he failed! He was {недостигащите секунди} seconds slower."
    Резултатът трябва да се форматира до втория знак след десетичната запетая.
Примерен вход и изход:
    10464
    1500
    20
    -> No, he failed! He was 20786.00 seconds slower.
        Иван трябва да преплува 1500 м.:  1500 * 20 = 30000 сек.
        На всеки 15 м. към времето му се добавят 12.5 сек.:
        1500 / 15 = 100 * 12.5 = 1250 сек.
        Общо време: 30000 + 1250 = 31250 сек.
        10464 < 31250
        Времето, което не му е стигнало за да подобри рекорда:
        31250 – 10464 = 20786 сек.
    55555.67
    3017
    5.03
    -> Yes, he succeeded! The new world record is 17688.01 seconds.
        Иван трябва да преплува 3017 м.: 3017 * 5.03 = 15175.51 сек.
        На всеки 15 м. към времето му се добавят 12.5 сек.:
        3017/ 15 = 201 * 12.5 = 2512.50 сек.
        Общо време: 15175.51 + 2512.50 = 17688.01 сек.
        Рекордът е подобрен: 55555.67 > 17688.01
 */
package SoftUni.Exer6;

import static java.lang.Math.floor;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class WorldSwimmingRecord {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double recordInSeconds = setValue(0.0, 100000.0);
        double distanceInMeters = setValue(0.0, 100000.0);
        double secondsForMeter = setValue(0.0, 1000.0);

        calculateIfNewRecord(recordInSeconds, distanceInMeters, secondsForMeter);
    }

    private static void calculateIfNewRecord(double recordInSeconds, double distanceInMeters, double secondsForMeter) {
        double secondsForDistance = distanceInMeters * secondsForMeter;
        double waterResistance = floor(distanceInMeters / 15) * 12.5;
        double time = secondsForDistance + waterResistance;
        double timeDifference = time - recordInSeconds;

        if (time >= recordInSeconds)
            out.printf("No, he failed! He was %.2f seconds slower.", timeDifference);
        else
            out.printf("Yes, he succeeded! The new world record is %.2f seconds.", time);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        Object value;

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }
        /*
        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведе число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }
        */
        return (T) value;
    }
}
