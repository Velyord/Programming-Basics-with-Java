/*
Условие:
    На осемнадесетия си рожден ден на Хосе взел решение, че ще се изнесе да живее на квартира.
    Опаковал багажа си в кашони и намерил подходяща обява за апартамент под наем.
    Той започва да пренася своя багаж на части, защото не може да пренесе целия наведнъж.
    Има ограничено свободно пространство в новото си жилище, където може да разположи вещите,
    така че мястото да бъде подходящо за живеене.
    Напишете програма, която изчислява свободния обем от жилището на Хосе, който остава след като пренесе багажа си.
    Бележка: Един кашон е с точни размери:  1m. x 1m. x 1m.
Вход:
    Потребителят въвежда следните данни на отделни редове:
    1.	Широчина на свободното пространство - цяло число в интервала [1...1000]
    2.	Дължина на свободното пространство - цяло число в интервала [1...1000]
    3.	Височина на свободното пространство - цяло число в интервала [1...1000]
    4.	На следващите редове (до получаване на команда "Done") - брой кашони, които се пренасят в квартирата -
    цяло число в интервала [1...10000]
    Програмата трябва да приключи прочитането на данни при команда "Done" или ако свободното място свърши.
Изход
    Да се отпечата на конзолата един от следните редове:
    •	Ако стигнете до командата "Done" и има още свободно място:
    "{брой свободни куб. метри} Cubic meters left."
    •	Ако свободното място свърши преди да е дошла команда "Done":
    "No more free space! You need {брой недостигащи куб. метри} Cubic meters more."
Примерен вход и изход
    10
    10
    2
    20
    20
    20
    20
    122
    -> No more free space! You need 2 Cubic meters more.
        10 * 10 * 2 = 200 кубични метра налични
        20 + 20 + 20 + 20 + 122 = 202 кубични метра
        200 - 202 = 2 недостигащи кубични метра
    10
    1
    2
    4
    6
    Done
    -> 10 Cubic meters left.
        10 * 1 * 2 = 20 кубични метра налични
        4 + 6 = 10 кубични метра
        20 - 10 = 10 кубични метра
 */
package SoftUni.Exer12;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Moving {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int width = setValue(1, 1000);
        int length = setValue(1, 1000);
        int height = setValue(1, 1000);
        int freeSpace = width * length * height;

        checkSpace(freeSpace);
    }

    private static void checkSpace(int freeSpace) {
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("Done")) {
                out.printf("%d Cubic meters left.", freeSpace);
                break;
            } else {
                int boxes = Integer.parseInt(input);
                freeSpace -= boxes;

                if (freeSpace < 0) {
                    out.printf("No more free space! You need %d Cubic meters more.", abs(freeSpace));
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
            boolean isSpecChar = false;
            value = scanner.nextLine();

            for (int i = 0; i < ((String) value).length(); i++)
                if (specialCharacters.contains(Character.toString(((String) value).charAt(i)))) {
                    isSpecChar = true;
                    break;
                }

            if (isSpecChar) {
                out.println("Моля въведете правилно наименование!");
                return setValue(null, null);
            }
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");

                for (String thing : required)
                    out.print(thing + " | ");

                out.println();
                return setValue(null, null);
            } */
        } else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
