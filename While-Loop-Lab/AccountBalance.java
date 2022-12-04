/*
Условие:
    Напишете програма, която пресмята колко общо пари има в сметката, след като направите определен брой вноски.
    На всеки ред ще получавате сумата, която трябва да внесете в сметката, до получаване на команда  "NoMoreMoney ".
    При всяка получена сума на конзолата трябва да се извежда "Increase: " + сумата и тя да се прибавя в сметката.
    Ако получите число по-малко от 0 на конзолата трябва да се изведе "Invalid operation!"  и програмата да приключи.
    Когато програмата приключи трябва да се принтира "Total: " + общата сума в сметката форматирана
    до втория знак след десетичната запетая.
Примерен вход и изход:
    5.51
    69.42
    100
    NoMoreMoney
    -> Increase: 5.51
    -> Increase: 69.42
    -> Increase: 100.00
    -> Total: 174.93
    120
    45.55
    -150
    -> Increase: 120.00
    -> Increase: 45.55
    -> Invalid operation!
    -> Total: 165.55
 */
package SoftUni.Lab11;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class AccountBalance {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double sum = 0;

        while (true) {
            String payment = scanner.nextLine();
            if (!payment.equals("NoMoreMoney")) {
                double paymentIntoNumber = Double.parseDouble(payment);
                if (paymentIntoNumber >= 0) {
                    out.printf("Increase: %.2f\n", paymentIntoNumber);
                    sum += paymentIntoNumber;
                } else {
                    out.println("Invalid operation!");
                    out.printf("Total: %.2f", sum);
                    break;
                }
            } else {
                out.printf("Total: %.2f", sum);
                break;
            }
        }
    }
}
