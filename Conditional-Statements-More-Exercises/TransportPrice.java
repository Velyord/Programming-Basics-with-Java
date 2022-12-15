/*
Условие:
    Студент трябва да пропътува n километра. Той има избор измежду три вида транспорт:
    • Такси. Начална такса: 0.70 лв. Дневна тарифа: 0.79 лв. / км. Нощна тарифа: 0.90 лв. / км.
    • Автобус. Дневна / нощна тарифа: 0.09 лв. / км. Може да се използва за разстояния минимум 20 км.
    • Влак. Дневна / нощна тарифа: 0.06 лв. / км. Може да се използва за разстояния минимум 100 км.
    Напишете програма, която въвежда броя километри n и период от деня (ден или нощ)
    и изчислява цената на най-евтиния транспорт.
Вход:
    От конзолата се четат два реда:
        • Първият ред съдържа числото n – брой километри – цяло число в интервала [1…5000]
        • Вторият ред съдържа дума “day” или “night” – пътуване през деня или през нощта
Изход:
    Да се отпечата на конзолата най-ниската цена за посочения брой километри,
    форматирана до втория знак след десетичния разделител.
Примерен вход и изход:
    5
    day
    -> 4.65
        Разстоянието е под 20 км  може да се ползва само такси. Началната такса е 0.70 лв.
        Понеже е през деня, тарифата е 0.79 лв. / км. С такси цената е: 0.70 + 5 * 0.79 = 4.65 лв.
    7
    night
    -> 7.00
        Разстоянието е под 20 км  може да се ползва само такси. Началната такса е 0.70 лв.
        Понеже е през нощта, тарифата е 0.90 лв. / км. С такси цената е: 0.70 + 7 * 0.90 = 7.00 лв.
    25
    day
    -> 2.25
        Разстоянието е над 20 км  може да се ползва автобус, но не може да се ползва влак.
        Автобусът е най-евтиния възможен вариант. С автобус цената е: 25 * 0.09 = 2.25 лв.
    180
    night
    -> 10.80
        Разстоянието е над 100 км  може да се ползва влак.
        Влакът е най-евтиният възможен вариант за пътуване. С влак цената е: 180 * 0.06 = 10.80 лв.
*/
package SoftUni.MoreExercises.ConditionalStatements;

import java.util.Scanner;
import java.util.List;

import static java.lang.Math.min;
import static java.lang.System.*;

public class TransportPrice {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int kmCount = setValue(1, 5000);
        String dayOrNight = setValue(null, null);
        
        transport(kmCount, dayOrNight);
    }

    private static void transport(int kmCount, String dayOrNight) {
        double taxiPrice = calcTaxiPrice(kmCount, dayOrNight);
        double busPrice = calcBusPrice(kmCount);
        double trainPrice = calcTrainPrice(kmCount);

        out.printf("%.2f", min(taxiPrice, min(busPrice, trainPrice)));
    }

    private static double calcTrainPrice(int kmCount) {
        double trainPrice;
        double trainTaxPerKm = 0.06;

        if (kmCount < 100)
            trainPrice = Double.MAX_VALUE;
        else
            trainPrice = trainTaxPerKm * kmCount;

        return trainPrice;
    }

    private static double calcBusPrice(int kmCount) {
        double busPrice;
        double busTaxPerKm = 0.09;
        
        if (kmCount < 20)
            busPrice = Double.MAX_VALUE;
        else 
            busPrice = busTaxPerKm * kmCount;
        
        return busPrice;
    }

    private static double calcTaxiPrice(int kmCount, String dayOrNight) {
        double startTax = 0.70;
        double dayTaxPerKm = 0.79;
        double nightTaxPerKm = 0.90;
        double roadTax = 0;
        
        switch (dayOrNight) {
            case "day":   roadTax = dayTaxPerKm * kmCount; break;
            case "night": roadTax = nightTaxPerKm * kmCount; break;
        }
        
        return startTax + roadTax;
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

                if (stringCount == 1) {
                    String[] required = { "day", "night" };
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
