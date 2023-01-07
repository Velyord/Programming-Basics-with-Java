/*
Условие:
    Напишете програма, която чете цяло положително число n, въведено от потребителя и печата числата от n до 1
    в обратен ред. Въведеното число n, винаги ще бъде по-голямо от 1.
Примерен вход и изход:
    2
    -> 2
    -> 1
    3
    -> 3
    -> 2
    -> 1
    5
    -> 5
    -> 4
    -> 3
    -> 2
    -> 1
 */
package SoftUni.Lab9;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class NumbersNTo1 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue("int", true);

        for(int i=number; i>0; i--)
            out.println(i);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(String type, Boolean positiveOnly) {
        Object value;
        // out.println("Въведете :");

        try {
            if (type.equals("int"))
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(type, positiveOnly);
        }

        if (type.equals("int") && positiveOnly) {
            if ((int) value < 0) {
                out.println("Моля въведете положително число между");
                return setValue(type, positiveOnly);
            }
        }
        if (type.equals("double") && positiveOnly){
            if ((double) value < 0) {
                out.println("Моля въведете положително число между");
                return setValue(type, positiveOnly);
            }
        }

        return (T) value;
    }
}
