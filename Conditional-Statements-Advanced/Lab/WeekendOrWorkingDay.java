/*
Условие:
    Напишете програма която, чете ден от седмицата (текст),
    на английски език - въведен от потребителя.
    Ако денят е работен отпечатва на конзолата - "Working day", ако е почивен - "Weekend".
    Ако се въведе текст различен от ден от седмицата да се отпечата - "Error".
Примерен вход и изход:
    Monday -> Working day
    Sunday -> Weekend
    April -> Error
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class WeekendOrWorkingDay {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String day = scanner.nextLine();

        checkDay(day);
    }

    private static void checkDay(String day) {
        switch (day){
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
                out.println("Working day"); break;
            case "Saturday":
            case "Sunday":
                out.println("Weekend"); break;
            default:
                out.println("Error");
        }
    }
}
