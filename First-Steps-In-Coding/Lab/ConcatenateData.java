/*
Условие:
    Напишете програма, която прочита от конзолата име, фамилия, възраст и град и печата следното съобщение: 
    "You are <firstName> <lastName>, a <age>-years old person from <town>."
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class ConcatenateData {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String firstName = setStringValue();
        String lastName = setStringValue();
        int age = setIntValue();
        String town = setStringValue();

        String output = "You are %s %s, a %d-years old person from %s.";
        out.printf(output, firstName, lastName, age, town);
    }

    private static int setIntValue() {
        int value;

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue();
        }

        if (value < 0) {
            out.println("Моля въведете положително число!");
            return setIntValue();
        }
        else
            return value;
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
            out.println("Моля въведете правилно име!");
            return setStringValue();
        }
        else
            return value;
    }
}
