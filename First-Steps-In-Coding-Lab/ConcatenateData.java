/*
Условие:
    Напишете програма, която прочита от конзолата име, фамилия, възраст и град и печата следното съобщение: 
    "You are <firstName> <lastName>, a <age>-years old person from <town>."
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import java.util.Scanner;

public class ConcatenateData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());
        String town = scanner.nextLine();

        String output = "You are %s %s, a %d-years old person from %s.";
        out.printf(output, firstName, lastName, age, town);
    }
}
