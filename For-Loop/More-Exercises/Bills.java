/*
Условие:
    Напишете програма която да пресмята средният разход за месец на семейство за даден период време.
    За всеки месец разходите са следните:
    • За ток – всеки месец е различен, ще се чете от конзолата
    • за вода – 20 лв.
    • за интернет – 15 лв.
    • за други – събират се тока, водата и интернета за месеца и към сумата се прибавят 20%.
    За всеки разход трябва да се пресметне колко общо е платено за всички месеци.
Вход:
    Входът се чете от конзолата:
        • Първи ред – месеците за които се търси средният разход – цяло число в интервала [1...100]
        • За всеки месец – сметката за ток – реално число в интервала [1.00...1000.00]
Изход:
    Да се отпечата на конзолата 5 реда:
        • 1ви ред: "Electricity: {ток за всички месеци} lv"
        • 2ри ред: "Water: {вода за всички месеци} lv"
        • 3ти ред: "Internet: {интернет за всички месеци} lv"
        • 4ти ред: "Other: {други за всички месеци} lv"
        • 5ти ред: "Average: {средно всички разходи за месец} lv"
    Всички числа трябва да са форматирана до вторият знак след запетаята.
Примерен вход и изход:
    5
    68.63
    89.25
    132.53
    93.53
    63.22
    -> Electricity: 447.16 lv
    -> Water: 100.00 lv
    -> Internet: 75.00 lv
    -> Other: 746.59 lv
    -> Average: 273.75 lv
        За 5 месеца:
        Ток  68.63 + 89.25 + 132.53 + 93.53 + 63.22 = 447.16 лв.
        Вода  5 месеца по 20 лв. = 100 лв.
        Интернет  5 месеца по15 = 75 лв.
        Други  (68.63+20+15) + 20% = 124.356  общо 746.592 лв.
                           (89.25+20+15) + 20% = 149.1
                        (132.53+20+15) + 20% = 201.036
                         (93.53+20+15) + 20% = 154.236
                        (63.22+20+15) + 20% = 117.864
        Средно на месец  (447.16+100+75+746.592)/5 = 273.7504 лв.
    8
    123.54
    231.54
    140.23
    100
    122.4
    430
    178.52
    64.2
    -> Electricity: 1390.43 lv
    -> Water: 160.00 lv
    -> Internet: 120.00 lv
    -> Other: 2004.52 lv
    -> Average: 459.37 lv
*/
package SoftUni.MoreExercises.ForLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Bills {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int months = setValue(1, 100);

        double electricitySum = 0, waterSum = 0, internetSum = 0, otherSum = 0, billsSum = 0;
        double electricity, water, internet, other, bills;

        for (int month = 1; month <= months; month++) {
            electricity = setValue(1.0, 1000.0);
            water = 20;
            internet = 15;
            other = (electricity + water + internet) * 1.20;
            bills = electricity + water + internet + other;
            
            electricitySum += electricity;
            waterSum       += water;
            internetSum    += internet;
            otherSum       += other;
            billsSum       += bills;
        }        
        double avg = billsSum / months;
        
        displayResult(electricitySum, waterSum, internetSum, otherSum, avg);
    }

    private static void displayResult(
            double electricitySum, 
            double waterSum, 
            double internetSum, 
            double otherSum, 
            double avg
    ) {
        out.printf("Electricity: %.2f lv\n", electricitySum);
        out.printf("Water: %.2f lv\n", waterSum);
        out.printf("Internet: %.2f lv\n", internetSum);
        out.printf("Other: %.2f lv\n", otherSum);
        out.printf("Average: %.2f lv\n", avg);
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
