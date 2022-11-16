/*
Условие:
    Да се напише програма, в която потребителят въвежда вида и размерите на геометрична фигура и пресмята лицето й. Фигурите са четири вида: квадрат (square), правоъгълник (rectangle), кръг (circle) и триъгълник (triangle). На първия ред на входа се чете вида на фигурата (текст със следните възможности: square, rectangle, circle или triangle).
    • Ако фигурата е квадрат (square): на следващия ред се чете едно дробно число - дължина на страната му
    • Ако фигурата е правоъгълник (rectangle): на следващите два реда четат две дробни числа - дължините на страните му
    • Ако фигурата е кръг (circle): на следващия ред чете едно дробно число - радиусът на кръга
    • Ако фигурата е триъгълник (triangle): на следващите два реда четат две дробни числа - дължината на страната му и дължината на височината към нея
    Резултатът да се закръгли до 3 цифри след десетичната запетая.
Примерен вход и изход:
    square
    5
    -> 25.000
    rectangle
    7
    2.5
    -> 17.500
    circle
    6
    -> 113.097
    triangle
    4.5
    20
    -> 45.000
 */
package SoftUni.Lab5;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;
import static java.lang.Math.pow;
import static java.lang.Math.PI;

public class AreaOfFigure {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String figure = setStringValue();

        Double result = calculateBasedOnFigure(figure);

        out.printf("%.3f", result);
    }

    private static Double calculateBasedOnFigure(String figure) {
        switch (figure) {
            case "square": {
                double side = setValue("страна");
                return pow(side, 2);
            }
            case "rectangle": {
                double side1 = setValue("първа страна");
                double side2 = setValue("втора страна");
                return side1 * side2;
            }
            case "circle": {
                double radius = setValue("радиус");
                return PI * pow(radius, 2);
            }
            case "triangle": {
                double side = setValue("страна");
                double height = setValue("височина");
                return (side * height) / 2;
            }
            default:
                return 0.0;
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(String thing) {
        Object value;
        out.println("Въведете " + thing + ":");

        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(thing);
        }

        if ((double) value < 0 ) {
            out.println("Моля въведете положително число!");
            return setValue(thing);
        }
        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        out.println("Въведете фигура:");
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно име!");
            return setStringValue();
        }
        else
            return value;
    }
}
