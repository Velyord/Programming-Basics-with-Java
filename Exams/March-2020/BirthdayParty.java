/*
Условие:
    За рожденният ден на дъщеря си Людмила е решила да
    организира парти, на което да покани всичките ѝ съученици.
    За целта е решила да наеме развлекателна зала за деца,
    чийто наем ще получите като вход от конзолата.
    Напишете програма, с която да помогнете на Людмила да изчисли
    какъв бюджет ще ѝ бъде необходим, като имате следната
    информация за допълнителните неща, необходими за
    тържеството:
    •	Торта  – цената ѝ е 20% от наема на залата
    •	Напитки – цената им е 45% по-малко от тази на тортата
    •	Аниматор – цената му е 1/3 от цената за наема на залата
Вход:
    От конзолата се четe 1 ред:
    •	Наем за залата –
    реално число в интервала [100.00..10000.00]
Изход:
    Да се отпечата на конзолата какъв бюджет ще бъде необходим
    за организиране на тържеството.
Примерен вход и изход:
    2250
    -> 3697.5
        наем за залата: 2250
        цена за тортата: 2250 * 20% = 450
        цена за напитки: 450 – 45% = 247.5
        цена за аниматор: 1 / 3 от 2250 = 750
        необходима сума: 2250 + 450 + 247.5 +750 = 3697.5
    3720
    -> 6113.2
*/
package SoftUni.March2020;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class BirthdayParty {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double rent = setValue(100.00, 10000.00);
        double cake = rent * 20 / 100;
        double drinks = cake * 55 / 100;
        double animator = rent / 3;

        double budget = rent + cake + drinks + animator;
        out.println(budget);
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
