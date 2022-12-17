/*
Условие:
    Гошо работи в ресторант и отговаря за зареждането на съдомиялната накрая на деня.
    Вашата задача е да напишете програма, която изчислява, дали дадено закупено количество бутилки от препарат за
    съдомиялна е достатъчно, за да измие определено количество съдове. Знае се, че всяка бутилка съдържа 750 мл.
    препарат, за 1 чиния са нужни 5 мл., а за тенджера 15 мл.  Приемете, че на всяко трето зареждане със съдове,
    съдомиялната се пълни само с тенджери, а останалите пъти с чинии. Докато не получите команда "End" ще продължите да
    получавате бройка съдове, които трябва да бъдат измити.
Вход:
    От конзолата се четат:
    •	Брой бутилки от препарат, който ще бъде използван за миенето на чинии - цяло число в интервала [1…10]
    На всеки следващ ред, до получаване на командата "End" или докато количеството препарат не се изчерпи, брой съдове,
    които трябва да бъдат измити - цяло число в интервала [1…100]
Изход:
    В случай, че количеството препарат е било достатъчно за измиването на съдовете:
    "Detergent was enough!"
    "{брой чисти чинии} dishes and {брой чисти тенджери} pots were washed."
    "Leftover detergent {количество останал препарат} ml."
    В случай, че количеството препарат не е било достатъчно за измиването на съдовете:
    "Not enough detergent, {количество не достигнал препарат} ml. more necessary!"
Примерен вход и изход:
    2
    53
    65
    55
    End
    -> Detergent was enough!
    -> 118 dishes and 55 pots were washed.
    -> Leftover detergent 85 ml.
        Количество препарат = 2 * 750 = 1500 мл.
        53 чинии са заредени => 53 * 5 = 265 мл.  1500 — 265 = 1235 мл. (остатък)
        65 чинии => 65 * 5 = 325 мл. 1235 — 325 = 910 мл. (остатък)
        55 тенджери => 55 * 15 = 825 мл. 910 — 825 = 85 мл. (остатък)
        Получаваме команда "End", следователно количеството е стигнало и се печата съответното съобщение:
        Брой чинии = 53 + 65 = 118. Брой тенджери = 55
    1
    10
    15
    10
    12
    13
    30
    -> Not enough detergent, 100 ml. more necessary!
        Количество препарат = 1 * 750 = 750 мл.
        10 чинии => 10 * 5 = 50 мл.  750 — 50 => 700 (остатък)
        15 чинии => 15 * 5 = 75 мл. 700 — 75 = 625 мл. (остатък)
        10 тенджери => 10 * 15 = 150 мл. 625 — 150 = 475 мл. (остатък)
        12 чинии => 12 * 5 = 60 мл. 475 — 60 = 415 мл. (остатък)
        13 чинии => 13 * 5 = 65 мл. 415 — 65 = 350 мл. (остатък)
        30 тенджери => 30 * 15 = 450 мл. 350 <= 450 (100 недостиг),
        следователно печатаме съобщение за недостиг на препарата
*/
package SoftUni.MoreExercises.WhileLoop;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Dishwasher {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int detergentBottleCount = setValue(1, 10);
        startWashingDishes(detergentBottleCount);
    }

    private static void startWashingDishes(
            int detergentBottleCount
    ) {
        int mlInOneBottle = 750;
        int mlDetergent =
            mlInOneBottle * detergentBottleCount;
        int mlForOneDish = 5, mlForOnePot = 15;
        int dishwasherFillCount = 0;
        int dishesCount = 0, potsCount = 0;
        boolean isDish = true;        
        String input = setValue(null, null);
        while (!input.equals("End")) {
            int detergentUsed = 0;
            dishwasherFillCount++;
            int currentDishesCount = Integer.parseInt(input);
            while (
                currentDishesCount < 1 ||
                currentDishesCount > 100
            ) {
                out.println("Моля въведете число между 1 и 100");
                currentDishesCount = setValue(1, 100);
            }
            if (dishwasherFillCount % 3 == 0)
                isDish = false;
            if (dishwasherFillCount % 3 != 0)
                isDish = true;
            if (isDish) {
                detergentUsed =
                    currentDishesCount * mlForOneDish;
                dishesCount += currentDishesCount;
            } else {
                detergentUsed =
                    currentDishesCount * mlForOnePot;
                potsCount += currentDishesCount;
            }
            mlDetergent -= detergentUsed;
            if (mlDetergent < 0)
                break;
            else
                input = setValue(null, null);
        }
        if (mlDetergent < 0)
            out.printf(
                "Not enough detergent, %d ml. more necessary!",
                abs(mlDetergent)
            );
        else
            out.printf(
                "Detergent was enough!\n" +
                "%d dishes and %d pots were washed.\n" +
                "Leftover detergent %d ml.",
                dishesCount, potsCount, mlDetergent
            );    
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}";
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

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
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
            }
            catch (Exception e) {
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
