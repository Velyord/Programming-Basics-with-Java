/*
Условие:
    Да се напише програма, която чете от конзолата реално число и го преобразува от инчове в сантиметри. За целта умножете инчовете по 2.54 (1 инч = 2.54 сантиметра).
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class InchesToCentimeters {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double inches = setDoubleValue();

        double centimeters = inchesToCentimeters(inches);
        out.println(centimeters);
    }
    
    private static double inchesToCentimeters(double inches) {
        return inches * 2.54;
    }

    private static double setDoubleValue() {
        out.println("Inches: ");
        double value;        

        try {
            value = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setDoubleValue();
        }
        
        if (value < 0) {
            out.println("Моля въведе положително число");
            return setDoubleValue();
        }
        else
            return value;
    }
}
