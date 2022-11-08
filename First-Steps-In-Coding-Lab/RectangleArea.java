/*
Условие:
    Да се напише конзолна програма, която въвежда две цели числа (страните на правоъгълника a и b) и пресмята лицето на правоъгълник с тези страни.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import java.util.Scanner;

public class RectangleArea {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int a = setSide();
        int b = setSide();

        int area = calculateRectangleArea(a, b);
        out.println(area);
    }
    private static int setSide() {
        int side;

        try {
            side = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setSide();
        }
        if (side < 0) {
            out.println("Моля въведе положително число!");
            return setSide();
        } else
            return side;
    }
    private static int calculateRectangleArea(int a, int b) {
        return a * b;
    }
}
