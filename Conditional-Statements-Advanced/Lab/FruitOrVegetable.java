/*
Условие:
    Да се напише програма, която чете име на продукт, въведено от потребителя, и проверява дали е плод или зеленчук.
    • Плодовете "fruit" са banana, apple, kiwi, cherry, lemon и grapes
    • Зеленчуците "vegetable" са tomato, cucumber, pepper и carrot
    • Всички останали са "unknown"
    Да се изведе "fruit", "vegetable" или "unknown" според въведения продукт.
Примерен вход и изход:
    banana -> fruit
    apple -> fruit
    tomato -> vegetable
    water -> unknown
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class FruitOrVegetable {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String food = setStringValue();

        checkFruitOrVegetable(food);
    }

    private static void checkFruitOrVegetable(String food) {
        switch (food) {
            case "banana":
            case "apple":
            case "kiwi":
            case "cherry":
            case "lemon":
            case "grapes":
                out.println("fruit"); break;
            case "tomato":
            case "cucumber":
            case "pepper":
            case "carrot":
                out.println("vegetable"); break;
            default:
                out.println("unknown"); break;
        }
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
