/*
Условие:
    Напишете програма, която чете цяло число n, въведено от потребителя, и отпечатва пирамида от числа като в примерите:
7 ->
1
2 3
4 5 6
7
10 ->
1
2 3
4 5 6
7 8 9 10
12 ->
1
2 3
4 5 6
7 8 9 10
11 12
15 ->
1
2 3
4 5 6
7 8 9 10
11 12 13 14 15
*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class NumberPyramid {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int pyramidSize = setValue(0, Integer.MAX_VALUE);

        createPyramid(pyramidSize);
    }

    private static void createPyramid(int pyramidSize) {
        int nextLine = 1;
        int numbersOnLine = 0;
        for (int i=1; i<=pyramidSize; i++) {
            out.print(i + " ");
            numbersOnLine++;
            if (numbersOnLine == nextLine) {
                out.println();
                numbersOnLine = 0;
                nextLine++;
            }
        }
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
        } else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
