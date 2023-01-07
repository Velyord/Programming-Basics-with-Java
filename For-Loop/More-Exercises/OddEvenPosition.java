/*
Условие:
    Напишете програма, която чете n-на брой числа, въведени от потребителя,
    и пресмята сумата, минимума и максимума на числата на четни и нечетни позиции (броим от 1).
    Когато няма минимален / максимален елемент, отпечатайте "No".
Изходът да се форматира в следния вид:
    "OddSum=" + {сума на числата на нечетни позиции},
    "OddMin=" + { минимална стойност на числата на нечетни позиции } / {“No”},
    "OddMax=" + { максимална стойност на числата на нечетни позиции } / {“No”},
    "EvenSum=" + { сума на числата на четни позиции },
    "EvenMin=" + { минимална стойност на числата на четни позиции } / {“No”},
    "EvenMax=" + { максимална стойност на числата на четни позиции } / {“No”}
    Всяко число трябва да е форматирано до втория знак след десетичната запетая.
Примерен вход и изход:
    6
    2
    3
    5
    4
    2
    1
    -> OddSum=9.00, OddMin=2.00, OddMax=5.00, EvenSum=8.00, EvenMin=1.00, EvenMax=4.00
    2
    1.5
    -2.5
    -> OddSum=1.50, OddMin=1.50, OddMax=1.50, EvenSum=-2.50, EvenMin=-2.50, EvenMax=-2.50
    1
    1
    -> OddSum=1.00, OddMin=1.00, OddMax=1.00, EvenSum=0.00, EvenMin=No, EvenMax=No
    0
    -> OddSum=0.00, OddMin=No, OddMax=No, EvenSum=0.00, EvenMin=No, EvenMax=No
    5
    3
    -2
    8
    11
    -3
    -> OddSum=8.00, OddMin=-3.00, OddMax=8.00, EvenSum=9.00, EvenMin=-2.00, EvenMax=11.00
    4
    1.5
    1.75
    1.5
    1.75
    -> OddSum=3.00, OddMin=1.50, OddMax=1.50, EvenSum=3.50, EvenMin=1.75, EvenMax=1.75
    1
    -5
    -> OddSum=-5.00, OddMin=-5.00, OddMax=-5.00, EvenSum=0.00, EvenMin=No, EvenMax=No
    3
    -1
    -2
    -3
    -> OddSum=-4.00, OddMin=-3.00, OddMax=-1.00, EvenSum=-2.00, EvenMin=-2.00, EvenMax=-2.00
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class OddEvenPosition {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numberCount = setValue(0, biggestInt);

        double oddSum  = 0,
               evenSum = 0;
        double oddMin  = biggestDouble,
               oddMax  = smallestDouble,
               evenMin = biggestDouble,
               evenMax = smallestDouble;

        for (int n = 1; n <= numberCount; n++) {
            double number = setValue(smallestDouble, biggestDouble);
            if (n % 2 != 0) {
                oddSum += number;
                if (number < oddMin)
                    oddMin = number;
                if (number > oddMax)
                    oddMax = number;
            } else {
                evenSum += number;
                if (number < evenMin)
                    evenMin = number;
                if (number > evenMax)
                    evenMax = number;
            }
        }
        displayResult(numberCount, oddSum, oddMin, oddMax, evenSum, evenMin, evenMax);
    }

    private static void displayResult(
        int numberCount,
        double oddSum,
        double oddMin,
        double oddMax,
        double evenSum,
        double evenMin,
        double evenMax
    ) {
        if (numberCount == 0)
            out.printf(
                "OddSum=%.2f,\n OddMin=No,\n OddMax=No,\n EvenSum=%.2f,\n EvenMin=No,\n EvenMax=No\n",
                oddSum, evenSum
            );
        else if (numberCount == 1)
            out.printf(
                    "OddSum=%.2f,\n OddMin=%.2f,\n OddMax=%.2f,\n EvenSum=%.2f,\n EvenMin=No,\n EvenMax=No\n",
                    oddSum, oddMin, oddMax, evenSum
            );
        else
            out.printf(
                "OddSum=%.2f,\n OddMin=%.2f,\n OddMax=%.2f,\n EvenSum=%.2f,\n EvenMin=%.2f,\n EvenMax=%.2f\n",
                oddSum, oddMin, oddMax, evenSum, evenMin, evenMax
            );
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
