/*
Условие:
    Да се напише програма, която чете час от денонощието(цяло число) и ден от седмицата(текст) -
    въведени от потребителя и проверява дали офисът на фирма е отворен, като работното време на офисът
    е от 10-18 часа, от понеделник до събота включително
Примерен вход и изход
    11
    Monday
    -> open
    19
    Friday
    -> closed
    11
    Sunday
    -> closed
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class WorkingHours {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int hour = setValue(0, 24);
        String day = setStringValue();

        checkIfOpen(day, hour);
    }

    private static void checkIfOpen(String day, int hour) {
        switch (day) {
            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":
            case "Saturday":
                if (hour < 10 || hour > 18)
                    out.println("closed");
                else
                    out.println("open");
                break;
            case "Sunday":
                out.println("closed");
                break;
            default:
    }
}

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете час:");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
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
