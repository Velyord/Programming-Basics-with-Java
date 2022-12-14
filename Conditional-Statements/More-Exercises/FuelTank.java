/*
Условие:
    Напишете програма, която познава дали резервоара на едно превозно средство има нужда от
    презареждане на горивото или не. От конзолата се четат два реда – текст и реално число,
    на първия ред се чете типа на горивото – текст с възможности: "Diesel", "Gasoline" или "Gas",
    а на втория литрите гориво, които има в резервоара. Ако литрите гориво са повече или равни на 25,
    на конзолата да се отпечата "You have enough {вида на горивото}.", ако са по-малко от 25,
    да се отпечата "Fill your tank with {вида на горивото}!". В случай, че бъде въведено гориво,
    различно от посоченото, да се отпечата "Invalid fuel!".
Примерен вход и изход:
    Diesel
    10
    -> Fill your tank with diesel!
    Gasoline
    40
    -> You have enough gasoline.
    Gas
    25
    -> You have enough gas.
    Kerosene
    200
    -> Invalid fuel!
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class FuelTank {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String fuelType = setValue(null, null);
        double fuelQuantity = setValue(0.0, biggestDouble);

        checkIfFuelIsEnough(fuelType, fuelQuantity);
    }

    private static void checkIfFuelIsEnough(String fuelType, double fuelQuantity) {
        switch (fuelType) {
            case "Diesel":
            case "Gasoline":
            case "Gas":
                if (fuelQuantity < 25)
                    out.printf("Fill your tank with %s!", fuelType);
                else
                    out.printf("You have enough %s.", fuelType);
                break;
            default:
                out.println("Invalid fuel!");
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

            if (requiredString){
                stringCount++;

                if (stringCount == 1) {
                    String[] required = { "room for one person", "apartment", "president apartment" };
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
                    String[] required = { "positive", "negative" };
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
