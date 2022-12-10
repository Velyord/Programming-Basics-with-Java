/*
Условие:
    Група приятели отиват на екскурзия. Първоначално прочитаме от конзолата броя на хората в групата.
    След това на отделни редове получаваме броя на нощувките, картите за транспорт и билети за музеи,
    които ще бъдат закупени от един човек. Трябва да се има предвид следния ценоразпис:
    •	Нощувка - 20 лв.
    •	Карта за транспорт - 1.60 лв.
    •	Билет за музей - 6 лв.
    Към крайната сума се начислява допълнително 25% за непредвидени разходи. Да се напише програма, която изчислява общата сумата, която групата трябва да плати.
Вход:
    От конзолата се четат 4 реда:
    1.	Броят на хората в групата – цяло число в интервала [0 … 50]
    2.	Броят на нощувките – цяло число в интервала [0 … 2000]
    3.	Броят на картите за транспорт – цяло число в интервала [0… 2000]
    4.	Броят на билетите за музеи – цяло число в интервала [0 … 2000]
Изход:
    Да се отпечата на конзолата едно число:
    •	парите, които групата трябва да плати, форматирани до втората цифра след десетичния знак
Примерен вход и изход:
    20
    14
    30
    6
    -> 9100.00
        Изчисляваме сумата, която се заплаща от един човек:
        Нощувки: 14 * 20 = 280 лв.
        Карти за транспорт: 30 * 1.60 =  48 лв.
        Билети за музеи: 6 * 6 = 36 лв.
        Обща сума за един човек от групата: 280 + 48 + 36 =  364 лв.
        Сума за цялата група: 364 * 20 = 7280 лв.
        Сума след добавяне на непредвидените разходи: 7280 + 25% = 9100 лв.
    131
    9
    33
    46
    -> 83316.00
*/
package SoftUni.Exam;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Excursion {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int countOfPeopleInGroup = setValue(0, 50);
        int countOfNights = setValue(0, 2000);
        int countOfTransportCards = setValue(0, 2000);
        int countOfMuseumTickets = setValue(0, 2000);

        calcMoneyGroupHasToPay(
            countOfPeopleInGroup,
            countOfNights,
            countOfTransportCards,
            countOfMuseumTickets
        );
    }

    private static void calcMoneyGroupHasToPay(
        int countOfPeopleInGroup,
        int countOfNights,
        int countOfTransportCards,
        int countOfMuseumTickets
    ) {
        double priceOfNight = 20;
        double priceOfTransportCard = 1.60;
        double priceOfMuseumTicket = 6;
        double percentUnpredictedExpense = 25;

        double priceForNights =
            countOfNights * priceOfNight;
        double priceForTransportCards =
            countOfTransportCards * priceOfTransportCard;
        double priceForMuseumTickets =
            countOfMuseumTickets * priceOfMuseumTicket;
        double priceForOnePerson =
            priceForNights +
            priceForTransportCards +
            priceForMuseumTickets;
        double priceForAllPeople =
            priceForOnePerson *  countOfPeopleInGroup;
        double unexpectedExpense =
            priceForAllPeople * percentUnpredictedExpense / 100;
        double priceAfterUnexpectedExpense =
            priceForAllPeople + unexpectedExpense;
            
        out.printf("%.2f", priceAfterUnexpectedExpense);
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
            } catch (Exception e) {
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
