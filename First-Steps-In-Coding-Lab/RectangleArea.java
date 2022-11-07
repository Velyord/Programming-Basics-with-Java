// Да се напише конзолна програма, която въвежда две цели числа (страните на правоъгълника a и b) и пресмята лицето на правоъгълник с тези страни.

package SoftUni;

import static java.lang.System.out;
import java.util.Scanner;

public class RectangleArea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        out.println(a*b);
    }
}
