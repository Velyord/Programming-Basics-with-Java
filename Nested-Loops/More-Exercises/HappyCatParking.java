/*
Условие:
    Деси трябва да заведе котката си на ветеринар в клиниката
    "Happy Cat", но паркингът се заплаща. Напишете програма,
    която пресмята колко общо трябва да се плати за престоя на
    колата на Деси на паркинга, за да заведе котката си на
    ветеринар. Паркингът е различен от останалите и има
    разнообразен ценоразпис. За всеки четен ден и нечетен час,
    паркингът таксува 2.50 лева. Във всеки нечетен ден и четен
    час таксата е 1.25 лева, във всички останали случаи се
    заплаща 1 лев. Таксуването става на всеки изминал час от
    деня. Всеки един от изходите трябва да бъде закръглен до
    втория знак след десетичната запетая.
Вход:
    От конзолата се четат два реда:
    •	Брой дни – цяло число в интервала [1 … 5]
    •	Брой часове за всеки един от дните -
    цяло число в интервала [1 … 24]
Изход:
    Да се отпечата на конзолата:
    •	За всеки изминал ден, общата сума,
    която трябва да се плати –
    "Day: {индексът на деня} – {общата сума за деня} leva"
    •	Когато програмата приключи -
    "Total: {общата сума за всички дни} leva"
Примерен вход и изход:
    2
    5
    ->
    Day: 1 - 5.50 leva
    Day: 2 - 9.50 leva
    Total: 15.00 leva
        2 дни по 5 часа за всеки =>
        Ден 1 - нечетен, 1вият час също =>
        таксата е равна на 1 лев
        Ден 1, 2рият час е четен =>
        таксата е равна на 1.25 лева
        Ден 1, 3ти час => 1 лев
        Ден 1, 4ти час => 1.25 лева
        Ден 1, 5ти час => 1 лев
        Ден 1 => обща сума – 5.50 лева
        Ден 2, 1ви час => таксата е равна на 2.50
        …..
        Ден 2 => обща сума – 9.50 лева
        Обща сума за всички дни => 5.50 + 9.50 = 15.00 лева
    5
    2
    ->
    Day: 1 - 2.25 leva
    Day: 2 - 3.50 leva
    Day: 3 - 2.25 leva
    Day: 4 - 3.50 leva
    Day: 5 - 2.25 leva
    Total: 13.75 leva
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class HappyCatParking {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int days = setValue(1, 5);
        int hours = setValue(1, 24);
        double tax = 0;
        double taxSum = 0;

        for (int day = 1; day <= days; day++) {
            for (int hour = 1; hour <= hours; hour++) {
                if (day % 2 == 0 && hour % 2 != 0)
                    tax += 2.5;
                else if (day % 2 != 0 && hour % 2 == 0)
                    tax += 1.25;
                else
                    tax += 1;
            }
            out.printf("Day: %d - %.2f leva\n", day, tax);
            taxSum += tax;
            tax = 0;
        }
        out.printf("Total: %.2f leva", taxSum);
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
