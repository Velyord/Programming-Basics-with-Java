/*
Условие:
    На благотворително събитие плащанията за закупените продукти винаги се редуват: плащане в брой и плащане с карта.
    Установени са следните правила за заплащане:
    •	Ако продуктът надвишава 100лв., за него не може да се плати в брой
    •	Ако продуктът е на цена под 10лв., за него не може да се плати с кредитна карта
    Програмата приключва или след като получим команда "End" или след като средствата бъдат събрани.
Вход:
    От конзолата се четат:
    •	Сумата, която се очаква да бъде събрана от продажбите - цяло число в интервала [1 ... 10000]
    На всеки следващ ред, до получаване на командата "End" или докато не се съберат нужните средства:
    цените на предметите, които ще бъдат закупени - цяло число в интервала [1 ... 500]
Изход:
    На конзолата да се отпечата:
    •	При успешна транзакция: "Product sold!"
    •	При неуспешна транзакция: "Error in transaction!"
    •	Ако сумата на всички закупени продукти надвиши или достигне очакваната сума, програмата трябва да приключи и на
    конзолата да се изпишат два реда:
    o	"Average CS: {средно плащане в кеш на човек}"
    o	"Average CC: {средно плащане с карта на човек}"
                  Плащанията трябва да бъдат форматирани до втората цифра след десетичния знак.
    •	При получаване на команда "End", да се изпише един ред:
    o	"Failed to collect required money for charity."
Примерен вход и изход:
    500
    120
    8
    63
    256
    78
    317
    -> Error in transaction!
    -> Error in transaction!
    -> Product sold!
    -> Product sold!
    -> Product sold!
    -> Product sold!
    -> Average CS: 70.50
    -> Average CC: 286.50
        По условие се редуват първо в брой плащане, след това чрез кредитна карта
        120 >= 100 транзакцията се отхвърля 8 <= 10 транзакцията се отхвърля 63 <= 100 => транзакцията е успешна.
        256 >= 10 => транзакцията е успешна. 78 <= 100 =>  транзакцията е успешна. 317 >= 10 => транзакцията е успешна.
        Обща събрана сума => 63 + 256 + 78 + 317 = 714;  714 >= 500 =>   Обща сума в брой => 63 + 78 = 141
        Средно в брой => 141 / 2 = 70.50 Общо кредитни карти => 256 + 317 = 573
        Средно кредитни карти => 573 / 2 = 286.50
    600
    86
    150
    98
    227
    End
    -> Product sold!
    -> Product sold!
    -> Product sold!
    -> Product sold!
    -> Failed to collect required money for charity.
        86 <= 100 => транзакцията е успешна. 150 >= 10 => транзакцията е успешна.
        98 <= 100 => транзакцията е успешна. 227 >= 10 => транзакцията е успешна.
        Четем от конзолата команда "End". Проверка, дали е достатъчна сумата => 86 + 150 + 98 + 227 = 561;
        561 <= 600 => печатаме, че сумата не е била събрана.
*/
package SoftUni.MoreExercises.WhileLoop;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class ReportSystem {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int sumToBeCollected = setValue(1, 10000);
        collectSum(sumToBeCollected);
    }

    private static void collectSum(int sumToBeCollected) {
        int transactionSum = 0,   transactionCardSum = 0,   transactionCashSum = 0;
        int transactionCount = 0, transactionCardCount = 0, transactionCashCount = 0;

        String input = setValue(null, null);
        while (!input.equals("End")) {
            int transaction = Integer.parseInt(input);
            transactionCount++;
            if (transactionCount % 2 != 0 && (transaction > 100 || transaction < 1)
                ||
                transactionCount % 2 == 0 && transaction < 10)
                out.println("Error in transaction!");
            else {
                if (transactionCount % 2 != 0) {
                    transactionCashCount++;
                    transactionCashSum += transaction;
                }
                else {
                    transactionCardCount++;
                    transactionCardSum += transaction;
                }
                transactionSum += transaction;
                out.println("Product sold!");
            }
            if (transactionSum >= sumToBeCollected)
                break;
            else
                input = setValue(null, null);
        }
        boolean isCollected = (transactionSum >= sumToBeCollected);
        if (isCollected) {
            out.println("Failed to collect required money for charity.");
        }
        else {
            double avgCS, avgCC;
            if (transactionCashCount == 0)
                avgCS = 0;
            else
                avgCS = (double) transactionCashSum / transactionCashCount;
            if (transactionCardCount == 0)
                avgCC = 0;
            else
                avgCC = (double) transactionCardSum / transactionCardCount;
            out.printf("Average CS: %.2f\n", avgCS);
            out.printf("Average CC: %.2f", avgCC);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        // out.println("Въведете :");

        if (min == null && max == null) {
            String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}";
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

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
        }
        else {
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
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
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
