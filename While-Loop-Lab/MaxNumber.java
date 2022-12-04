/*
Условие:
    Напишете програма, която до получаване на командата "Stop", чете цели числа, въведени от потребителя
    и намира най-голямото измежду тях. Въвежда се по едно число на ред.
Примерен вход и изход:
    100
    99
    80
    70
    Stop
    -> 100
    -10
    20
    -30
    Stop
    -> 20
    45
    -20
    7
    99
    Stop
    -> 99
    999
    Stop
    -> 999
    -1
    -2
    Stop
    -> -1
 */
package SoftUni.Lab11;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class MaxNumber {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int max = Integer.MIN_VALUE;

        while (true) {
            String input = scanner.nextLine();
            if (!input.equals("Stop")) {
                int inputToNumber = Integer.parseInt(input);
                if (inputToNumber > max)
                    max = inputToNumber;
            } else {
                out.println(max);
                break;
            }
        }
    }
}
