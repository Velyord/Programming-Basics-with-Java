/*
Условие:
    Да се напише конзолна програма, която въвежда две цели числа (страните на правоъгълника a и b) и пресмята лицето на правоъгълник с тези страни.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class RectangleArea {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int a = setIntValue();
        int b = setIntValue();

        int rectArea = calculateRectangleArea(a, b);
        
        out.println(rectArea);
    }
    
    private static int calculateRectangleArea(int a, int b) {
        return a * b;
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
}
