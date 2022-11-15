/*
Условие:
    Да се напише програма, която чете от конзолата текст (име на човек) и отпечатва "Hello, <name>!", където <name> е въведеното име от конзолата.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class GreetingByName {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String name = setStringValue();

        out.printf("Hello, %s!", name);
    }

    private static String setStringValue() {
        String specialCharacters="!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно име!");
            return setStringValue();
        }
        else
            return value;
    }
}
