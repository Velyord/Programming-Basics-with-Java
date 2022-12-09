/*
Условие:
    Градинар продавал реколтата от градината си на зеленчуковата борса.
    Продава зеленчуци за N лева на килограм и плодове за M лева за килограм.
    Напишете програма, която да пресмята приходите от реколтата в евро ( ако приемем, че едно евро е равно на 1.94лв).
Вход:
    От конзолата се четат 4 числа, по едно на ред:
        • Първи ред – Цена за килограм зеленчуци – реално число[0.00… 1000.00]
        • Втори ред – Цена за килограм плодове – реално число[0.00… 1000.00]
        • Трети ред – Общо килограми на зеленчуците – цяло число[0… 1000]
        • Четвърти ред – Общо килограми на плодовете – цяло число[0… 1000]
Изход:
    Да се отпечата на конзолата едно число: приходите от всички плодове и зеленчуци в евро.
    Резултата да се форматира до втория знак след десетичния разделител.
Примерен вход и изход:
    0.194
    19.4
    10
    10
    -> 101.00
        Зеленчуците струват – 0.194лв. * 10кг. = 1.94лв.
        Плодовете струват – 19.4лв. * 10кг.  = 194лв.
        Общо – 195.94лв. = 101евро
    1.5
    2.5
    10
    10
    -> 20.62
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class VegetableMarket {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double priceForKgVegetables = setValue(0.00, 1000.00);
        double priceForKgFruits = setValue(0.00, 1000.00);
        int kgVegetables = setValue(0, 1000);
        int kgFruits = setValue(0, 1000);

        double price = calcPrice(priceForKgFruits, priceForKgVegetables, kgFruits, kgVegetables);
        double priceInEuro = convertToEuro(price);

        out.printf("%.2f", priceInEuro);
    }

    private static double convertToEuro(double price) {
        return price / 1.94;
    }

    private static double calcPrice(double priceForKgFruits, double priceForKgVegetables, int kgFruits, int kgVegetables) {
        double vegetablesPrice = priceForKgVegetables * kgVegetables;
        double fruitsPrice = priceForKgFruits * kgFruits;

        return vegetablesPrice + fruitsPrice;
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
                out.println();
                return setValue(null, null);
            } */
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
