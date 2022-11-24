/*
Условие:
    Магазин за плодове през работните дни работи на следните цени:
        banana -> 2.50
        apple -> 1.20
        orange -> 0.85
        grapefruit -> 1.45
        kiwi -> 2.70
        pineapple -> 5.50
        grapes -> 3.85
    Събота и неделя магазинът работи на по-високи цени:
        banana -> 2.70
        apple -> 1.25
        orange -> 0.90
        grapefruit -> 1.60
        kiwi -> 3.00
        pineapple -> 5.60
        grapes -> 4.20
    Напишете програма, която чете от конзолата плод (banana / apple / orange / grapefruit / kiwi /
    pineapple / grapes), ден от седмицата (Monday / Tuesday / Wednesday / Thursday / Friday /
    Saturday / Sunday) и количество (десетично число) , въведени от потребителя, и пресмята цената
    според цените от таблиците по-горе. При невалиден ден от седмицата или невалидно име на плод да
    се отпечата "error".
Примерен вход и изход:
    apple
    Tuesday
    2
    -> 2.40
    orange
    Sunday
    3
    -> 2.70
    kiwi
    Monday
    2.5
    -> 6.75
    grapes
    Saturday
    0.5
    -> 2.10
    tomato
    Monday
    0.5
    -> error
 */
package SoftUni.Lab7;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class FruitShop {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String fruit = setStringValue();
        String day = setStringValue();
        double quantity = setValue();

        checkPrice(fruit, day, quantity);
    }

    private static void checkPrice(String fruit, String day, double quantity) {
        boolean error = false;
        String weekdayOrWeekend = "";
        double price = 0;

        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                weekdayOrWeekend = "Weekday";
                break;
            case "Saturday":
            case "Sunday":
                weekdayOrWeekend = "Weekend";
                break;
            default:
                error = true;
                break;
        }
        switch (weekdayOrWeekend) {
            case "Weekday":
                switch (fruit) {
                    case "banana":      price = 2.50; break;
                    case "apple":       price = 1.20; break;
                    case "orange":      price = 0.85; break;
                    case "grapefruit":  price = 1.45; break;
                    case "kiwi":        price = 2.70; break;
                    case "pineapple":   price = 5.50; break;
                    case "grapes":      price = 3.85; break;
                    default:            error = true;
                } break;
            case "Weekend":
                switch (fruit) {
                    case "banana":      price = 2.70; break;
                    case "apple":       price = 1.25; break;
                    case "orange":      price = 0.90; break;
                    case "grapefruit":  price = 1.60; break;
                    case "kiwi":        price = 3.00; break;
                    case "pineapple":   price = 5.60; break;
                    case "grapes":      price = 4.20; break;
                    default:            error = true;
                }
        }
        double finalPrice = price * quantity;

        if (error)
            out.println("error");
        else
            out.printf("%.2f", finalPrice);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue() {
        Object value;
        // out.println("Въведете бройка:");

        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue();
        }

        if ((double) value < 0.0) {
            out.println("Моля въведете положително число.");
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
