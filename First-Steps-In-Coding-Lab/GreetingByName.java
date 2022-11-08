/*
Условие:
    Да се напише програма, която чете от конзолата текст (име на човек) и отпечатва "Hello, <name>!", където <name> е въведеното име от конзолата.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import java.util.Scanner;

public class GreetingByName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String name = scanner.nextLine();
        
        out.printf("Hello, %s!", name);
    }
}
