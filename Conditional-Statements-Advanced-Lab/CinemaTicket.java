package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class CinemaTicket {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String day = setStringValue();
        
        checkPriceForDay(day);
    }

    private static void checkPriceForDay(String day) {
        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Friday":
                out.println(12); break;
            case "Wednesday":
            case "Thursday":
                out.println(14); break;
            case "Saturday":
            case "Sunday":
                out.println(16); break;
            default:
        }
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
