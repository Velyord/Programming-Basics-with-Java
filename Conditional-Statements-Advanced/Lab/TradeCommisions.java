/*
Условие:
    Фирма дава следните комисионни на търговците си според града,
    в който работят и обема на продажбите s:
        Град
            0 ≤ s ≤ 500
            500 < s ≤ 1 000
            1 000 < s ≤ 10 000
            s > 10 000
        Sofia
            5%
            7%
            8%
            12%
        Varna
            4.5%
            7.5%
            10%
            13%
        Plovdiv
            5.5%
            8%
            12%
            14.5%
    Напишете конзолна програма, която чете име на град (текст) и обем на продажби (реално число) , въведени от потребителя, и изчислява и извежда размера на търговската комисионна според горната таблица. Резултатът да се изведе форматиран до 2 цифри след десетичната точка. При невалиден град или обем на продажбите (отрицателно число) да се отпечата "error".
Примерен вход и изход:
    Sofia
    1500
    -> 120.00
    Plovdiv
    499.99
    -> 27.50
    Varna
    3874.50
    -> 387.45
    Kaspichan
    -50
    -> error
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class TradeCommissions {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String city = setStringValue();
        double sales = setValue();
        
        checkCommision(city, sales);
    }

    private static void checkCommision(String city, double sales) {
        boolean error = false;
        double commision = 0;
        
        if (sales >= 0 && sales <= 500) {
            switch (city) {
                case "Sofia":   commision = 5.0; break;
                case "Varna":   commision = 4.5; break;
                case "Plovdiv": commision = 5.5; break;
                default: error = true; break;
            }
        } else if (sales > 500 && sales <= 1000) {
            switch (city) {
                case "Sofia":   commision = 7.0; break;
                case "Varna":   commision = 7.5; break;
                case "Plovdiv": commision = 8.0; break;
                default: error = true; break;
            }
        } else if (sales > 1000 && sales <= 10000) {
            switch (city) {
                case "Sofia":   commision = 8;  break;
                case "Varna":   commision = 10; break;
                case "Plovdiv": commision = 12; break;
                default: error = true; break;
            }
        } else if (sales > 10000) {
            switch (city) {
                case "Sofia":   commision = 12.0; break;
                case "Varna":   commision = 13.0; break;
                case "Plovdiv": commision = 14.5; break;
                default: error = true; break;
            }
        } else
            error = true;
        
        double cash = sales * commision / 100;
        
        if (error)
            out.println("error");
        else
            out.printf("%.2f", cash);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;
        // out.println("Въведете продажби:");

        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }

        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
