/*
Условие:
    Напишете програма, която чете от конзолата три дробни числа b1, b2 и h и пресмята лицето на трапец 
    с основи b1 и b2 и височина h. Формулата за лице на трапец е (b1 + b2) * h / 2.
    На фигурата по-долу е показан трапец със страни 8 и 13 и височина 7. Той има лице 
    (8 + 13) * 7 / 2 = 73.5.
    Отговорът трябва да е форматиран до втората цифра след десетичния знак.
Примерен вход и изход:
8
13
7
-> 73.50
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TrapeziodArea {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double b1 = setValue(0.0, biggestDouble);
        double b2 = setValue(0.0, biggestDouble);
        double h = setValue(0.0, biggestDouble);
        
        double trapezoidArea = calcTrapezoidArea(b1, b2, h);
        
        out.printf("%.2f", trapezoidArea);
    }

    private static double calcTrapezoidArea(double b1, double b2, double h) {
        return (b1 + b2) * h / 2;
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
