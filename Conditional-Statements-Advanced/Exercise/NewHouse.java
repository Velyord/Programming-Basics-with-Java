/*
Условие:
    Марин и Нели си купуват къща не далеч от София. Нели толкова много обича цветята, че Ви убеждава
    да напишете програма която да изчисли колко  ще им струва, да си засадят определен брой цветя и
    дали наличния бюджет ще им е достатъчен. Различните цветя са с различни цени.
        Роза -> 5
        Далия -> 3.80
        Лале -> 2.80
        Нарцис -> 3
        Гладиола -> 2.50
    Съществуват следните отстъпки:
        • Ако Нели купи повече от 80 Рози - 10% отстъпка от крайната цена
        • Ако Нели купи повече от 90  Далии - 15% отстъпка от крайната цена
        • Ако Нели купи повече от 80 Лалета - 15% отстъпка от крайната цена
        • Ако Нели купи по-малко от 120 Нарциса - цената се оскъпява с 15%
        • Ако Нели Купи по-малко от 80 Гладиоли - цената се оскъпява с 20%
    От конзолата се четат 3 реда:
        • Вид цветя - текст с възможности - "Roses", "Dahlias", "Tulips", "Narcissus", "Gladiolus"
        • Брой цветя - цяло число в интервала [10…1000]
        • Бюджет - цяло число в интервала [50…2500]
    Да се отпечата на конзолата на един ред:
        • Ако бюджета им е достатъчен - "Hey, you have a great garden with {броя цвета} {вид цветя}
        and {останалата сума} leva left."
        • Ако бюджета им е НЕ достатъчен - "Not enough money, you need {нужната сума} leva more."
    Сумата да бъде форматирана до втория знак след десетичната запетая.
Примерен вход и изход
    Roses
    55
    250
    -> Not enough money, you need 25.00 leva more.
        Нели иска 55 броя Рози. Цената на една роза е 5лв., следователно за 55 броя Нели ще трябва да
        плати: 55 * 5 = 275. Тя обаче разполага с 250 лв. бюджет. Понеже 275 > 250 , то не и достигат
        25 лв.
    Tulips
    88
    260
    -> Hey, you have a great garden with 88 Tulips and 50.56 leva left.
    Narcissus
    119
    360
    -> Not enough money, you need 50.55 leva more.
 */
package SoftUni.Exer8;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class NewHouse {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String flowers = setStringValue();
        int quantity = setValue(10, 1000);
        int budget = setValue(50, 2500);

        checkBudget(flowers, quantity, budget);
    }

    private static void checkBudget(String flowers, int quantity, int budget) {
        boolean error = false;
        double price = 0;

        switch (flowers) {
            case "Roses":
                price = 5 * quantity;
                if (quantity > 80)
                    price *= 0.9;
                break;
            case "Dahlias":
                price = 3.80 * quantity;
                if (quantity > 90)
                    price *= 0.85;
                break;
            case "Tulips":
                price = 2.80 * quantity;
                if (quantity > 80)
                    price *= 0.85;
                break;
            case "Narcissus":
                price = 3 * quantity;
                if (quantity < 120)
                    price *= 1.15;
                break;
            case "Gladiolus":
                price = 2.50 * quantity;
                if (quantity < 80)
                    price *= 1.20;
                break;
            default:
                error = true;
                break;
        }

        double difference = abs(budget - price);

        if (error)
            out.println("error");
        else if (budget >= price)
            out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", quantity, flowers, difference);
        else
            out.printf("Not enough money, you need %.2f leva more.", difference);
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        // out.println("Въведете вид цветя: ");
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
