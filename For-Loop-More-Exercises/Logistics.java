/*
Условие:
    Отговаряте за логистиката на различни товари.
    В зависимост от теглото на товара е нужно различно превозно средство.
    Цената на тон, за която се превозва товара е различна за всяко превозно средство:
    • До 3 тона – микробус (200 лева на тон)
    • От 4 до 11 тона – камион (175 лева на тон)
    • 12 и повече тона – влак (120 лева на тон)
    Вашата задача е да изчислите средната цена на тон превозен товар,
    както и процента на тоновете  превозвани с всяко превозно средство,
    спрямо общото тегло(в тонове) на всички товари.
Вход:
    От конзолата се четат поредица от числа, всяко на отделен ред:
        • На първия ред – броя на товарите за превоз – цяло число в интервала [1...1000]
        • За всеки един товар на отделен ред – тонажа на товара – цяло число в интервала [1...1000]
Изход
    Да се отпечатат на конзолата 4 реда, както следва:
        • Първи ред – средната цена на тон превозен товар (закръглена до 2 знак след дес. запетая);
        • Втори ред – процентът тона превозвани с микробус (процент между 0.00% и 100.00%);
        • Трети ред – процентът  тона превозвани с камион (процент между 0.00% и 100.00%);
        • Четвърти ред – процентът тона превозвани с влак (процент между 0.00% и 100.00%).
Примерен вход и изход:
    4
    1
    5
    16
    3
    -> 143.80
    -> 16.00%
    -> 20.00%
    -> 64.00%
        Всички товари 1 + 5 + 16 + 3 = 25 тона
        (4 * 200 + 5 * 175 + 16 * 120)/25 = 143.80 (средно на тон)
        С микробус – 4/25*100 = 16.00%
        С камион – 5/25*100 = 20.00%
        С влак – 16/25*100 = 64.00%
    5
    2
    10
    20
    1
    7
    -> 149.38
    -> 7.50%
    -> 42.50%
    -> 50.00%
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Logistics {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int cargoCount = setValue(1, 1000);

        calcPriceAndWeight(cargoCount);
    }

    private static void calcPriceAndWeight(int cargoCount) {
        double cargoPrice = 0, cargoPriceSum = 0;
        int microbusPrice = 200, truckPrice = 175, trainPrice = 120;
        int cargoWeightSum = 0, microbusWeightSum = 0, truckWeightSum = 0, trainWeightSum = 0;

        for (int i=0; i<cargoCount; i++) {
            boolean isMicrobus = false, isTruck = false, isTrain = false;
            int cargoWeight = setValue(1, 1000);
            cargoWeightSum += cargoWeight;

            if (cargoWeight <= 3)
                isMicrobus = true;
            else if (cargoWeight <= 11)
                isTruck = true;
            else
                isTrain = true;

            if (isMicrobus) {
                cargoPrice = microbusPrice * cargoWeight;
                microbusWeightSum += cargoWeight;
            } else if (isTruck) {
                cargoPrice = truckPrice * cargoWeight;
                truckWeightSum += cargoWeight;
            } else if (isTrain){
                cargoPrice = trainPrice * cargoWeight;
                trainWeightSum += cargoWeight;
            }

            cargoPriceSum += cargoPrice;
        }

        displayResult(
                cargoPriceSum,
                cargoWeightSum,
                microbusWeightSum,
                truckWeightSum,
                trainWeightSum
        );
    }

    private static void displayResult(
            double cargoPriceSum,
            int cargoWeightSum,
            int microbusWeightSum,
            int truckWeightSum,
            int trainWeightSum
    ) {
        double avg =
                cargoPriceSum / cargoWeightSum;
        double microbusWeightPercent =
                100.0 * microbusWeightSum / cargoWeightSum;
        double truckWeightPercent =
                100.0 * truckWeightSum / cargoWeightSum;
        double trainWeightPercent =
                100.0 * trainWeightSum / cargoWeightSum;

        out.printf(
            "%.2f%n%.2f%%%n%.2f%%%n%.2f%%",
            avg,
            microbusWeightPercent,
            truckWeightPercent,
            trainWeightPercent
        );
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

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
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
            }
            catch (Exception e) {
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
