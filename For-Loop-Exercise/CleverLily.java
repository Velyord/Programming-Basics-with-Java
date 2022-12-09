/*
Условие:
    Лили вече е на N години. За всеки свой рожден ден тя получава подарък.
    •	За нечетните рождени дни (1, 3, 5...n) получава играчки.
    •	За четните рождени дни (2, 4, 6...n) получава пари.
    За втория рожден ден получава 10.00 лв, като сумата се увеличава с 10.00 лв., за всеки следващ четен рожден ден (2 -> 10, 4 -> 20, 6 -> 30...и т.н.). През годините Лили тайно е спестявала парите. Братът на Лили, в годините, които тя получава пари, взима по 1.00 лев от тях. Лили продала играчките получени през годините, всяка за P лева и добавила сумата към спестените пари. С парите искала да си купи пералня за X лева. Напишете програма, която да пресмята, колко пари е събрала и дали ѝ стигат да си купи пералня.
Вход:
    Програмата прочита 3 числа, въведени от потребителя, на отделни редове:
    •	Възрастта на Лили - цяло число в интервала [1...77]
    •	Цената на пералнята - число в интервала [1.00...10 000.00]
    •	Единична цена на играчка - цяло число в интервала [0...40]
Изход:
    Да се отпечата на конзолата един ред:
    •	Ако парите на Лили са достатъчни:
    o	"Yes! {N}" - където N е остатъка пари след покупката
    •	Ако парите не са достатъчни:
    o	"No! {М}" - където M е сумата, която не достига
    Числата N и M трябва да за форматирани до вторият знак след десетичната запетая.
Примерен вход и изход:
    10
    170.00
    6
    -> Yes! 5.00
        Първи рожден ден получава играчка
        Втори рожден ден получава пари (10 лв.)
        Трети рожден ден получава играчка
        Четвърти рожден ден получава пари (20 лв.)
        Пети рожден ден получава играчка
        Шести рожден ден получава пари (30 лв.)
        Седми рожден ден получава играчка
        Осми рожден ден получава пари (40 лв.)
        Девети рожден ден получава играчка
        Десети рожден ден получава пари (50 лв.)
        Спестила е: 10 + 20 + 30 + 40 + 50 = 150 лв.
        Продала е 5 броя играчки * 6 лв. = 30 лв.
        Брат ѝ взел 5 пъти * 1 лев = 5 лв.
        Общо спестени пари: (150 + 30) – 5 = 175 лв.
        175 >= 170 (цената на пералнята)
        => успяла е да я купи
        Остават: 175 - 170 = 5 лв.
    21
    1570.98
    3
    -> No! 997.98
        Спестила е 550 лв.
        Продала е 11 играчки * 3 лв. = 33 лв.
        Брат ѝ взимал 10 години * 1 лев = 10 лв.
        Общо спестени пари: (550 + 33) – 10 = 573 лв.
        573 < 1570.98 – не е успяла да купи пералня
        Нужни пари: 1570.98 – 573 = 997.98 лв.
 */
package SoftUni.Exer10;

import static java.lang.Math.abs;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class CleverLily {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int age = setValue(1, 77);
        double washer = setValue(1.0, 10000.0);
        int toyPrice = setValue(0, 40);

        calculate(age, washer, toyPrice);
    }

    private static void calculate(int age, double washer, int toyPrice) {
        double money = 0;
        int moneyIncrease = 10;

        for (int currentAge=1; currentAge<=age; currentAge++) {
            if (currentAge % 2 != 0)
                money += toyPrice; // 1 toy = toyPrice
            else {
                money += moneyIncrease - 1;
                moneyIncrease += 10;
            }
        }

        displayResult(money, washer);
    }

    private static void displayResult(double money, double washer) {
        double difference = abs(money - washer);

        if (washer > money)
            out.printf("No! %.2f", difference);
        else
            out.printf("Yes! %.2f", difference);
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

            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            }
        } else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка!");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == Integer.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == Double.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
        }
        return (T) value;
    }
}
