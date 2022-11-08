/*
Условие:
    Да се напише програма, която чете от конзолата реално число и го преобразува от инчове в сантиметри. За целта умножете инчовете по 2.54 (1 инч = 2.54 сантиметра).
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import java.util.Scanner;

public class InchesToCentimeters {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Inches: ");
        double inches = Double.parseDouble(scanner.nextLine());

        double centimeters = inchesToCentimeters(inches);

        out.println(centimeters);
    }
    private static double inchesToCentimeters(double inches) {
        return inches * 2.54;
    }
}
