/*
Условие:
    Предприемчив българин отваря квартални магазинчета в няколко града и продава на различни цени:
    град / продукт
    coffee
    water
    beer
    sweets
    peanuts
    Sofia
    0.50
    0.80
    1.20
    1.45
    1.60
    Plovdiv
    0.40
    0.70
    1.15
    1.30
    1.50
    Varna
    0.45
    0.70
    1.10
    1.35
    1.55
    Напишете програма, която чете продукт (низ), град (низ) и количество (десетично число),
    въведени от потребителя, и пресмята и отпечатва колко струва съответното количество от
    избрания продукт в посочения град.
Примерен вход и изход
    coffee
    Varna
    2
    -> 0.9
    peanuts
    Plovdiv
    1
    -> 1.5
    beer
    Sofia
    6
    -> 7.2
    water
    Plovdiv
    3
    -> 2.1
    sweets
    Sofia
    2.23
    -> 3.2335
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SmallShop {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String product = setStringValue();
        String town = setStringValue();
        double quantity = setValue(0.0);
        
        checkPrice(product, town, quantity);
    }

    private static void checkPrice(String product, String town, double quantity) {
        double price = 0;

        switch (town) {
            case "Sofia":
                switch (product) {
                    case "coffee":  price = 0.50; break;
                    case "water":   price = 0.80; break;
                    case "beer":    price = 1.20; break;
                    case "sweets":  price = 1.45; break;
                    case "peanuts": price = 1.60; break;
                    default:
                } break;
            case "Plovdiv":
                switch (product) {
                    case "coffee":  price = 0.40; break;
                    case "water":   price = 0.70; break;
                    case "beer":    price = 1.15; break;
                    case "sweets":  price = 1.30; break;
                    case "peanuts": price = 1.50; break;
                    default:
                } break;
            case "Varna":
                switch (product) {
                    case "coffee":  price = 0.45; break;
                    case "water":   price = 0.70; break;
                    case "beer":    price = 1.10; break;
                    case "sweets":  price = 1.35; break;
                    case "peanuts": price = 1.55; break;
                    default:
                } break;
            default:
        }

        double payment = price * quantity;
        
        out.println(payment);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min) {
        Object value;
        boolean isIntArgs = min instanceof Integer;
        // out.println("Въведете бройка:");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min);
        }

        if (isIntArgs) {
            if ((int) value < (int) min) {
                out.println("Моля въведете положително число!");
                return setValue(min);
            }
        } else {
            if ((double) value < (double) min) {
                out.println("Моля въведете положително число!");
                return setValue(min);
            }
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
