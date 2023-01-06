/*
Условие:
    Напишете програма, която познава дали е топло или студено навън.
    От конзолата се чете един ред – текст, който подсказва какво е времето.
    При въвеждане на "sunny" трябва да се отпечата "It's warm outside!".
    Във всички останали случаи трябва да се отпечата "It's cold outside!".
Примерен вход и изход:
    sunny
    -> It's warm outside!
    cloudy
    -> It's cold outside!
    snowy
    -> It's cold outside!
*/
package SoftUni.MoreExercises.FirstStepsInCoding;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class WeatherForecast {

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String input = scanner.nextLine();
        
        printForecast(input);
    }

    private static void printForecast(String input) {
        if (input.equals("sunny"))
            out.println("It's warm outside!");
        else 
            out.println("It's cold outside!");
    }
}
