/*
Условие:
    Напишете програма, която чете число n, въведено от потребителя и отпечатва числата от 1 до n през 3.
Примерен вход и изход:
    10
    -> 1
    -> 4
    -> 7
    -> 10
    7
    -> 1
    -> 4
    -> 7
    15
    -> 1
    -> 4
    -> 7
    -> 10
    -> 13
 */
package SoftUni.Lab9;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class Numbers1ToNWithStep3 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue("int", true);

        for (int i=1; i<=number; i+=3)
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
