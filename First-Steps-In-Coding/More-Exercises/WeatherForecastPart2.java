/*
Условие:
    Напишете програма, която при въведени градуси (реално число) принтира какво е времето,
    като имате предвид следната таблица:
    26.00 - 35.00
    Hot
    20.1 - 25.9
    Warm
    15.00 - 20.00
    Mild
    12.00 - 14.9
    Cool
    5.00 - 11.9
    Cold
    Ако се въведат градуси, различни от посочените в таблицата, да се отпечата "unknown".
Примерен вход и изход:
    16.5
    -> Mild
    8
    -> Cold
    22.4
    -> Warm
    26
    -> Hot
    0
    -> unknown
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class WeatherForecastPart2 {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double degrees = setValue(smallestDouble, biggestDouble);

        printForecast(degrees);
    }

    private static void printForecast(double degrees) {
        String forecast = "unknown";
        
        if (degrees >= 26 && degrees <= 35)
            forecast = "Hot";
        else if (degrees >= 20.1 && degrees <= 25.9)
            forecast = "Warm";
        else if (degrees >= 15 && degrees <= 20)
            forecast = "Mild";
        else if (degrees >= 12 && degrees <= 14.9)
            forecast = "Cool";
        else if (degrees >= 5 && degrees <= 11.9)
            forecast = "Cold";

        out.println(forecast);
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
