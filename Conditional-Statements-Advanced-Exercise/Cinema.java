/*
Условие:
    В една кинозала столовете са наредени в правоъгълна форма в r реда и c колони. Има три вида прожекции с билети на различни цени:
    • Premiere – премиерна прожекция, на цена 12.00 лева.
    • Normal – стандартна прожекция, на цена 7.50 лева.
    • Discount – прожекция за деца, ученици и студенти на намалена цена от 5.00 лева.
    Напишете програма, която чете тип прожекция (стринг), брой редове и брой колони в залата (цели числа), въведени от потребителя, и изчислява общите приходи от билети при пълна зала. Резултатът да се отпечата във формат като в примерите по-долу, с 2 знака след десетичната точка.
Примерен вход и изход
    Premiere
    10
    12
    -> 1440.00 leva

    Normal
    21
    13
    -> 2047.50 leva

    Discount
    12
    30
    -> 1800.00 leva
 */
package SoftUni.Exer8;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String projection = setStringValue();
        int rows = setValue();
        int columns = setValue();

        calculatePrice(projection, rows, columns);
    }

    private static void calculatePrice(String projection, int rows, int columns) {
        boolean error = false;
        double price = 0;

        switch (projection) {
            case "Premiere":    price = 12.0; break;
            case "Normal":      price = 7.50; break;
            case "Discount":    price = 5.00; break;
            default:            error = true;
        }

        double finalPrice = price * (rows * columns);
        
        if (error)
            out.println("error");
        else 
            out.printf("%.2f leva", finalPrice);
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

        if ((int) value < 0) {
            out.println("Моля въведете положително число!");
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
