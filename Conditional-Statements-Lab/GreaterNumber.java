/*
Условие:
    Да се напише програма, която чете две цели числа, въведени от потребителя и отпечатва по-голямото от двете.
Примерен вход и изход:
    5
    3
    -> 5
    3
    5
    -> 5
    10
    10
    -> 10
    -5
    5
    -> 5
 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import static java.lang.Math.max;

public class GreaterNumber {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int num1 = setValue();
        int num2 = setValue();

        int biggerNumber = max(num1, num2);
        out.println(biggerNumber);
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
