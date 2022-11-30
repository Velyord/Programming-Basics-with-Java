/*
Условие:
    Да се напише програма, която чете n-на брой цели числа, въведени от потребителя и ги сумира.
    • От първия ред на входа се въвежда броят числа n.
    • От следващите n реда се въвежда по едно цяло число.
    Програмата трябва да прочете числата, да ги сумира и да отпечата сумата им.
Примерен вход и изход:
    2
    10
    20
    -> 30
    3
    -10
    -20
    -30
    -> -60
    4
    45
    -20
    7
    11
    -> 43
    1
    999
    -> 999
    0
    -> 0
 */
package SoftUni.Lab9;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SumNumbers {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int nums = setValue("int");
        int result = 0;

        for (int i=0; i<nums; i++)
            result += (int) setValue("int");
        out.println(result);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(String type) {
        Object value;
        boolean isInt = type.equals("int");
        // out.println("Въведете :");

        try {
            if (isInt)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(type);
        }

        return (T) value;
    }
}
