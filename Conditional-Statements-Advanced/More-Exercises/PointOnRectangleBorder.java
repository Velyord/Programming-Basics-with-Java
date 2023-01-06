/*
Условие:
    Напишете програма, която проверява дали точка {x, y} се намира върху някоя от страните на
    правоъгълник {x1, y1} – {x2, y2}. Входните данни се четат от конзолата и се състоят от 6 реда
    въведени от потребителя: десетичните числа x1, y1, x2, y2, x и y (като се гарантира, че x1 < x2 и
    y1 < y2). Да се отпечата "Border" (точката лежи на някоя от страните) или "Inside / Outside"
    (в противен случай).
Примерен вход и изход:
    2
    -3
    12
    3
    8
    -1
    -> Inside / Outside
    2
    -3
    12
    3
    12
    -1
    -> Border
* Подсказка:
    използвайте една или няколко условни if проверки с логически операции.
    Точка {x, y} лежи върху някоя от страните на правоъгълник {x1, y1} – {x2, y2},
    ако е изпълнено едно от следните условия:
    • x съвпада с x1 или x2 и същевременно y е между y1 и y2
    • y съвпада с y1 или y2 и същевременно x е между x1 и x2
    Можете да проверите горните условия с една по-сложна if-else конструкция
    или с няколко по-прости проверки или с вложени if-else проверки.
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class PointOnRectangleBorder {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double x1 = setValue(smallestDouble, biggestDouble);
        double y1 = setValue(smallestDouble, biggestDouble);
        double x2 = setValue(smallestDouble, biggestDouble);
        double y2 = setValue(smallestDouble, biggestDouble);
        double x = setValue(smallestDouble, biggestDouble);
        double y = setValue(smallestDouble, biggestDouble);

        isPointOnBorder(x1, y1, x2, y2, x, y);
    }

    private static void isPointOnBorder(double x1, double y1, double x2, double y2, double x, double y) {
        if ((x == x1 || x == x2) && (y >= y1 && y <= y2) || (y == y1 || y == y2) && (x >= x1 && x <= x2))
            out.println("Border");
        else
            out.println("Inside / Outside");
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
