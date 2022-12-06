/*
Условие:
    Ани обича да пътува и иска тази година да посети няколко различни дестинации.
    Като си избере дестинация, ще прецени колко пари ще й трябват, за да отиде до там и ще започне да спестява.
    Когато е спестила достатъчно, ще може да пътува.
    От конзолата всеки път ще се четат първо дестинацията и минималния бюджет,
    който ще е нужен за пътуването, реално число.
    След това ще се четат няколко суми, реални числа, които Ани спестява като работи
    и когато успее да събере достатъчно за пътуването, ще заминава, като на конзолата трябва да се изпише:
    "Going to {дестинацията}!"
    Когато е посетила всички дестинации, които иска, вместо дестинация ще въведе "End" и програмата ще приключи.
Примерен вход и изход:
    Greece
    1000
    200
    200
    300
    100
    150
    240
    Spain
    1200
    300
    500
    193
    423
    End
    -> Going to Greece!
    -> Going to Spain!
    France
    2000
    300
    300
    200
    400
    190
    258
    360
    Portugal
    1450
    400
    400
    200
    300
    300
    Egypt
    1900
    1000
    280
    300
    500
    End
    -> Going to France!
    -> Going to Portugal!
    -> Going to Egypt!
 */
package SoftUni.Lab13;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class Travelling {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        travelTheWorld();
    }

    private static void travelTheWorld() {
        String input = scanner.nextLine();
        
        while (!input.equals("End")) {
            String destination = input;
            double destinationMoney = setValue(Double.MIN_VALUE, Double.MAX_VALUE);
            while (true) {
                double inputMoney = setValue(Double.MIN_VALUE, Double.MAX_VALUE);
                destinationMoney -= inputMoney;
                if (destinationMoney <= 0) {
                    out.printf("Going to %s!\n", destination);
                    break;
                }
            }
            input = scanner.nextLine();
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}";
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

                out.println();
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
