// Напишете програма, която чете ъгъл в радиани (десетично число) и го преобразува в градуси. Използвайте формулата: градус = радиан * 180 / π. Числото π в Java програми е достъпно чрез Math.PI.
    // Примерен вход и изход
        // 3.1416 -> 180.0004209182994
        // 6.2832 -> 360.0008418365988
        // 0.7854 -> 45.00010522957485

package Exe4;

import java.util.Scanner;
import static java.lang.System.out;

public class RadiansToDegrees {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double rad = Double.parseDouble(scanner.nextLine());
        double angleToRad = angleToRad(rad);
        out.println(angleToRad);
    }
    private static double angleToRad(double rad) {
        return rad * 180/Math.PI;
    }
}
