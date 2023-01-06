/*
Условие:
    Атанас решава да прекара отпуската си в Банско и да кара ски. Преди да отиде обаче, трябва да резервира хотел
    и да изчисли колко ще му струва престоя. Налични са следните видове помещения, със следните цени за престой:
    	"room for one person" – 18.00 лв за нощувка
    	"apartment" – 25.00 лв за нощувка
    	"president apartment" – 35.00 лв за нощувка
    Според броят на дните, в които ще остане в хотела (пример: 11 дни = 10 нощувки) и видът на помещението,
    което ще избере, той може да ползва различно намаление.
    Намаленията са както следва:
    вид помещение
        по-малко от 10 дни
        между 10 и 15 дни
        повече от 15 дни
    room for one person
        не ползва намаление
        не ползва намаление
        не ползва намаление
    apartment
        30% от крайната цена
        35% от крайната цена
        50% от крайната цена
    president apartment
        10% от крайната цена
        15% от крайната цена
        20% от крайната цена
    След престоя, оценката на Атанас за услугите на хотела може да е позитивна (positive) или негативна (negative) . Ако оценката му е позитивна, към цената с вече приспаднатото намаление Атанас добавя 25% от нея. Ако оценката му е негативна приспада от цената 10%.
Вход:
    Входът се чете от конзолата и се състои от три реда:
    •	Първи ред - дни за престой - цяло число в интервала [0...365]
    •	Втори ред - вид помещение - "room for one person", "apartment" или "president apartment"
    •	Трети ред - оценка - "positive"  или "negative"
Изход:
    На конзолата трябва да се отпечата един ред:
    •	Цената за престоят му в хотела, форматирана до втория знак след десетичната запетая.
Примерен вход и изход:
    14
    apartment
    positive
    -> 264.06
        14 дни => 13 нощувки => 13 * 25.00 = 325 лв.
        10 < 13 дни < 15 => 325 – 35%= 211.25 лв.
        Оценката е positive => 211.25 + 25% = 264.0625 -> 264.06 лв.
    30
    president apartment
    negative
    -> 730.80
    12
    room for one person
    positive
    -> 247.50
    2
    apartment
    positive
    -> 21.88
 */
package SoftUni.Exer8;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class SkiTrip {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int days = setValue(0, 365);
        String type = setStringValue();
        String rating = setStringValue();

        calcPrice(days, type, rating);
    }

    private static void calcPrice(int days, String type, String rating) {
        double priceForNight = 0;
        double discountPercent = 0;
        double ratingPercent = 0;

        switch (type) {
            case "room for one person":
                priceForNight = 18;
                break;
            case "apartment":
                priceForNight = 25;
                if (days < 10)
                    discountPercent = 30;
                else if (days <= 15)
                    discountPercent = 35;
                else
                    discountPercent = 50;
                break;
            case "president apartment":
                priceForNight = 35;
                if (days < 10)
                    discountPercent = 10;
                else if (days <= 15)
                    discountPercent = 15;
                else
                    discountPercent = 20;
                break;
            default:
                out.println("ГРЕШКА!");
                out.println("Променливата 'type' трябва да съдържа една от следните стойности:");
                out.println("room for one person | apartment | president apartment");
                out.println("Рестартирайте програмата и пробвайте отново.");
                exit(0);
                break;
        }

        switch (rating) {
            case "positive":
                ratingPercent = 25;
                break;
            case "negative":
                ratingPercent = 10;
                break;
            default:
                out.println("ГРЕШКА!");
                out.println("Променливата 'rating' трябва да съдържа една от следните стойности:");
                out.println("positive | negative");
                out.println("Рестартирайте програмата и пробвайте отново.");
                exit(0);
                break;
        }

        double finalPrice = calcFinalPrice(priceForNight, days, discountPercent, ratingPercent, rating);

        out.printf("%.2f",finalPrice);
    }

    private static double calcFinalPrice(
        double priceForNight,
        int days,
        double discountPercent,
        double ratingPercent,
        String rating
    ) {
        double priceForAllNights = priceForNight * (days - 1);
        double discountPrice = priceForAllNights * (discountPercent / 100);
        double priceAfterDiscount = priceForAllNights - discountPrice;
        double ratingPrice = priceAfterDiscount * (ratingPercent / 100);

        if (rating.equals("positive"))
            return priceAfterDiscount + ratingPrice;
        else
            return priceAfterDiscount - ratingPrice;
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете :");

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
        // out.println("Въведете:");
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        }

        return value;
    }
}
