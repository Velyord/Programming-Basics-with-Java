/*
Условие:
    Напишете програма, която да изчислява, колко ще струва на един шофьор да напълни резервоара на
    автомобила си, като знаете – какъв тип гориво зарежда,
    каква е цената за литър гориво и дали разполага с карта за отстъпки.
    Цените на горивата са както следва:
    • Бензин – 2.22 лева за един литър,
    • Дизел – 2.33 лева за един литър
    • Газ – 0.93 лева за литър
    Ако водача има карта за отстъпки, той се възползва от следните намаления за литър гориво:
    18 ст. за литър бензин, 12 ст. за литър дизел и 8 ст. за литър газ.
    Ако шофьора е заредил между 20 и 25 литра включително,
    той получава 8 процента отстъпка от крайната цена, при повече от 25 литра гориво,
    той получава 10 процента отстъпка от крайната цена.
Вход:
    Входът се чете от конзолата и се състои от 3 реда:
        • Типа на горивото – текст с възможности: "Gas", "Gasoline" или "Diesel"
        • Количество гориво – реално число в интервала [1.00 … 50.00]
        • Притежание на клубна карта – текст с възможности: "Yes" или "No"
Изход:
    На конзолата трябва да се отпечата един ред.
        • "{крайната цена на горивото} lv."
    Цената на горивото да бъде форматираната до втората цифра след десетичния знак.
Примерен вход и изход:
    Gas
    30
    Yes
    -> 22.95 lv.
        Горивото е газ, цена за литър газ е 0.93 лв.
        Шофьора има карта за отстъпки, отстъпката за газ е 8ст. от цената за литър. Цената на която той ще зареди е 0.93 – 0.08 = 0.85 ст.
        30 литра по 0.85 е 25.5 лв. но тъй като при заредени повече от 25 литра има отстъпка.
        25.5 – 10% = 22.95 лв. крайна цена
    Gasoline
    25
    No
    -> 51.06 lv.
        Горивото е бензин, цена за литър бензин е 2.22лв.
        Шофьора няма карта за отстъпки.
        25 литра по 2.22 е 55.50 лв. но тъй като при заредени между 20 и 25 литра включително,
        има отстъпка. 55.50 – 8% = 51.06 лв. крайна цена
    Diesel
    19
    No
    -> 44.27 lv.
        Горивото е дизел, цена за литър дизел е 2.32лв.
        Шофьора няма карта за отстъпки.
        19 литра по 2.33 е 44.27 лв. за това количество гориво няма отстъпки
        и това остава крайната цена.
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FuelTankPart2 {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String fuelType = setValue(null, null);
        double fuelQuantity = setValue(1.0, 50.0);
        String hasClubCard = setValue(null, null);

        price(fuelType, fuelQuantity, hasClubCard);
    }

    private static void price(String fuelType, double fuelQuantity, String hasClubCard) {
        double gasoline = 2.22;
        double diesel = 2.33;
        double gas = 0.93;

        if (hasClubCard.equals("Yes")) {
            gasoline -= 0.18;
            diesel -= 0.12;
            gas -= 0.08;
        }

        double discountPercent = 0;

        if (fuelQuantity > 25)
            discountPercent = 10;
        else if (fuelQuantity >= 20)
            discountPercent = 8;

        double rawPrice = 0;

        switch (fuelType) {
            case "Gasoline": rawPrice = fuelQuantity * gasoline; break;
            case "Diesel":   rawPrice = fuelQuantity * diesel;   break;
            case "Gas":      rawPrice = fuelQuantity * gas;      break;
        }

        double discountPrice = rawPrice * discountPercent / 100;
        double finalPrice = rawPrice - discountPrice;

        out.printf("%.2f lv.", finalPrice);
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
                    String[] required = { "Gas", "Gasoline", "Diesel" };
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

                if (stringCount == 2) {
                    String[] required = { "Yes", "No" };
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
