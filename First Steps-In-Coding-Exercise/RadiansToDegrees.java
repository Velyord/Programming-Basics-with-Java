/*
Условие:
    Напишете програма, която чете ъгъл в радиани (десетично число) и го преобразува в градуси. Използвайте формулата: градус = радиан * 180 / π. Числото π в Java програми е достъпно чрез Math.PI.
Примерен вход и изход:
    3.1416 -> 180.0004209182994
    6.2832 -> 360.0008418365988
    0.7854 -> 45.00010522957485
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class RadiansToDegrees {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double rads = setDoubleValue();

        double angles = radsToAngles(rads);

        out.println(angles);
    }

    private static double radsToAngles(double rads) {
        return rads * 180 / Math.PI;
    }

    private static double setDoubleValue() {
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
