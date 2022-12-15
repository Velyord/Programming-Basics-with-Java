/*
Условие:
    Марина обича да пътува. Тя има 3 домашни любимеца (куче, котка и костенурка).
    Когато заминава на пътешествие трябва да съобрази колко храна да им остави,
    за да не останат гладни. Напишете програма,
    която пресмята колко килограма храна ще изядат всички за времето,
    в което Марина отсъства и дали оставената храна от нея ще им е достатъчна.
    Всяко животно изяжда определено количество храна на ден.
Вход:
    От конзолата се четат пет реда:
        • Първи ред – брой дни – цяло число в интервал [1…5000]
        • Втори ред – оставена храна в килограми – цяло число в интервал [0…100000]
        • Трети ред – храна на ден за кучето в килограми – реално число в интервал [0.00…100.00]
        • Четвърти ред – храна на ден за котката в килограми– реално число в интервал [0.00…100.00]
        • Пети ред – храна на ден за костенурката в грамове – реално число в интервал [0.00…10000.00]
Изход:
    На конзолата трябва да се отпечата на един ред:
        • Ако оставената храна Е достатъчна:
            ◦ "{килограма остатък} kilos of food left."
                ▪ Резултатът трябва да е закръглен към по-ниското цяло число
        • Ако оставената храна НЕ Е достатъчна:
            ◦ “{килограма недостигат} more kilos of food are needed.”
                ▪ Резултатът трябва да е закръглен към по-високото цяло число
Примерен вход и изход:
    2
    10
    1
    1
    1200
    -> 3 kilos of food left.
        Нужна храна за: куче = 2 дена * 1 кг = 2кг;
                        котка = 2 дена * 1 кг = 2 кг;
                        костенурка = 2 дена * 1200 грама = 2.4 кг;
        Общо храна = 2 + 2 + 2.4 = 6.4;
        6.4 < 10 => 10 - 6.4 = 3.6 -> 3 кг. храна остават
    5
    10
    2.1
    0.8
    321
    -> 7 more kilos of food are needed.
        Нужна храна за: куче = 10.5 кг;
                        котка = 5 дена * 0.8 кг = 4 кг;
                        костенурка = 5 дена * 321 грама = 1.605 кг;
        Общо храна = 10.5 + 4 + 1.605 = 16.105;
        16.105 – 10 = 6.105 -> 7 кг не достигат
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.Math.*;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Pets {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int dayCount = setValue(1, 5000);
        int kgFoodLeft = setValue(0, 100000);
        double kgFoodForDogForDay = setValue(0.0, 100.0);
        double kgFoodForCatForDay = setValue(0.0, 100.0);
        double gFoodForTurtleForDay = setValue(0.0, 10000.0);

        double foodNeeded = foodNeeded(
                dayCount,
                kgFoodForDogForDay,
                kgFoodForCatForDay,
                gFoodForTurtleForDay
        );
        isFoodEnough(foodNeeded, kgFoodLeft);
    }

    private static void isFoodEnough(double foodNeeded, int kgFoodLeft) {
        double foodDiff = abs(kgFoodLeft - foodNeeded);

        if (foodNeeded > kgFoodLeft)
            out.printf("%.0f more kilos of food are needed.", ceil(foodDiff));
        else
            out.printf("%.0f kilos of food left.", floor(foodDiff));
    }

    private static double foodNeeded(
            int dayCount,
            double kgFoodForDogForDay,
            double kgFoodForCatForDay,
            double gFoodForTurtleForDay
    ) {
        double neededFoodForDog = dayCount * kgFoodForDogForDay;
        double neededFoodForCat = dayCount * kgFoodForCatForDay;
        double neededFoodForTurtle = dayCount * gFoodForTurtleForDay / 1000;

        return neededFoodForDog + neededFoodForCat + neededFoodForTurtle;
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

            if (requiredString){
                stringCount++;

                if (stringCount == 1) {
                    String[] required = { "room for one person", "apartment", "president apartment" };
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

                if (stringCount == 2) {
                    String[] required = { "positive", "negative" };
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
