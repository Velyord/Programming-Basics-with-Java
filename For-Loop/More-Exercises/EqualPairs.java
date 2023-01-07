/*
Условие:
    Дадени са 2*n-на брой числа.
    Първото и второто формират двойка, третото и четвъртото също и т.н.
    Всяка двойка има стойност – сумата от съставящите я числа.
    Напишете програма, която проверява дали всички двойки имат еднаква стойност
    или печата максималната разлика между две последователни двойки.
    Ако всички двойки имат еднаква стойност, отпечатайте "Yes, value={Value}" + стойността.
    В противен случай отпечатайте "No, maxdiff={Difference}" + максималната разлика.
Примерен вход и изход:
    3
    1
    2
    0
    3
    4
    -1
    -> Yes, value=3
        стойности = {3, 3, 3}
        еднакви стойности
    2
    1
    2
    2
    2
    -> No, maxdiff=1
        стойности = {3, 4}
        разлики = {1}
        макс. разлика = 1
    4
    1
    1
    3
    1
    2
    2
    0
    0
    -> No, maxdiff=4
        стойности = {2, 4, 4, 0}
        разлики = {2, 0, 4}
        макс. разлика = 4
    1
    5
    5
    -> Yes, value=10
        стойности = {10}
        една стойност
        еднакви стойности
    2
    -1
    0
    0
    -1
    -> Yes, value=-1
        стойности = {-1, -1}
        еднакви стойности
    2
    -1
    2
    0
    -1
    -> No, maxdiff=2
        стойности = {1, -1}
        разлики = {2}
        макс. разлика = 2
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class EqualPairs {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numberCount = setValue(0, biggestInt);
        
        int[] pairs = pairsCollector(numberCount);
        checkIfPairsEqual(pairs);
    }
    
    private static int[] pairsCollector(int numberCount) {
        int grouper = 0;
        int size = 0;
        int[] pairs = {};

        for (int n = 1; n <= numberCount * 2; n++) {
            int number = setValue(smallestInt, biggestInt);
            grouper += number;

            if (n % 2 == 0) {
                pairs = addX(size, pairs, grouper);
                size++;
                grouper = 0;
            }
        }
        return pairs;
    }

    private static void checkIfPairsEqual(int[] pairs) {
        boolean allEqual = Arrays.stream(pairs).distinct().count() == 1;
        if (allEqual)
            out.printf("Yes, value=%d", pairs[0]);
        else {
            int maxdiff = 0;
            for (int i=0; i<pairs.length - 1; i++) {
                int iterationMaxdiff = abs(pairs[i] - pairs[i+1]);
                if (iterationMaxdiff > maxdiff)
                    maxdiff = iterationMaxdiff;
            }
            out.printf("No, maxdiff=%d", maxdiff);
        }
    }

    // Function to add x in arr
    public static int[] addX(int size, int[] arr, int x) {
        int i;

        // create a new array of size n+1
        int[] newArr = new int[size + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < size; i++)
            newArr[i] = arr[i];

        newArr[size] = x;

        return newArr;
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
