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

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            } */
        } else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
