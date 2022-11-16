/*
Условие:
    Да се напише програма, която чете цяло число, въведено от потребителя и проверява дали е под 100, между 100 и 200 или над 200. Ако числото е:
    • под 100 отпечатайте: "Less than 100"
    • между 100 и 200 отпечатайте: "Between 100 and 200"
    • над 200 отпечатайте: "Greater than 200"
Примерен вход и изход:
    -> 95
    Less than 100
    -> 120
    Between 100 and 200
    -> 210
    Greater than 200
 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class Number100To200 {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int num = setValue();
        
        String output = setEstimate(num);
        
        out.println(output);
    }

    private static String setEstimate(int num) {
        if (num < 100)
            return "Less than 100";
        else if (num <= 200)
            return "Between 100 and 200";
        else 
            return "Greater than 200";
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
