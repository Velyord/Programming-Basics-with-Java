/*
Условие:
    Да се напише конзолна програма, която прочита възраст (десетично число) и пол ("m" или "f"), въведени от потребителя, и отпечатва обръщение измежду следните:
    • "Mr." - мъж (пол "m") на 16 или повече години
    • "Master" - момче (пол "m") под 16 години
    • "Ms." - жена (пол "f") на 16 или повече години
    • "Miss" - момиче (пол "f") под 16 години
Примерен вход и изход:
    12
    f
    -> Miss
    17
    m
    -> Mr.
    25
    f
    -> Ms.
    13.5
    m
    -> Master
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class PersonalTitles {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double age = setValue(0.0);
        char sex = setChar('m', 'f');

        checkTitle(age, sex);
    }

    private static void checkTitle(double age, char sex) {
        boolean isMr =      (age >= 16  && sex == 'm');
        boolean isMaster =  (age < 16   && sex == 'm');
        boolean isMs =      (age >= 16  && sex == 'f');
//      boolean isMiss =    (age < 16   && sex == 'f');

        if (isMr)           out.println("Mr.");
        else if (isMaster)  out.println("Master");
        else if (isMs)      out.println("Ms.");
        else                out.println("Miss");
    }

    private static char setChar(char m, char f) {
        char sex = scanner.nextLine().charAt(0);

        if (sex != 'm' && sex != 'f')
            return setChar(m, f);
        else
            return sex;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min) {
        boolean isIntArgs = min instanceof Integer;
        Object value;

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min);
        }

        if (isIntArgs) {
            if ((int) value < (int) min) {
                out.println("Моля въведете положително число!");
                return setValue(min);
            }
        } else {
            if ((double) value < (double) min) {
                out.println("Моля въведете положително число!");
                return setValue(min);
            }
        }

        return (T) value;
    }
}
