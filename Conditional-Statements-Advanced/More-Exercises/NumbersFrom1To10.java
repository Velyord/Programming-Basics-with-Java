/*
Условие:
    Напишете програма, която отпечатва числата от 1 до 10, по едно на ред.
Примерен вход и изход:
    (няма)
    1
    2
    3
    …
    10
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class NumbersFrom1To10 {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        printNumbersFrom(1, 10);
    }

    private static void printNumbersFrom(int num, int maxNum) {
        while (num <= maxNum) {
            out.println(num);
            num++;
        }
    }
}
