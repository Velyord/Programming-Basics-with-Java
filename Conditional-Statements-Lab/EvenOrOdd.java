/*
Условие:
    Да се напише програма, която чете цяло число, въведено от потребителя и печата дали е четно или нечетно.
    Ако е четно отпечатайте "even", ако е нечетно "odd".
Примерен вход и изход:
    2
    -> even
    3
    -> odd
    25
    -> odd
    1024
    -> even

 */
package SoftUni.Lab5;

import static java.lang.Math.max;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class EvenOrOdd {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int num = setValue();

        String evenOrOdd = checkEvenOrOdd(num);

        out.println(evenOrOdd);
    }

    private static String checkEvenOrOdd(int num) {
        String output;
        boolean isEven = (num % 2 == 0);
        
        if (isEven)
            output = "even";
        else 
            output = "odd";
        
        return output;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;

        try {
            value = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }
        return (T) value;
    }
}
