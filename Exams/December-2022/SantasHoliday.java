/*
Условие:
    Всяка година Дядо Коледа избира различни дестинации за почивка.
    Тази година той решава да прекара почивните си дни в България.
    Неговите верни приятели – джуджетата, му предлагат да се настани в един от най-престижните хотели,
    а именно "Четири сезона". По време на престоя си там, той трябва да избере между следните видове помещения,
    със следните цени за престой:
    •	"room for one person" – 18.00 лв за нощувка
    •	"apartment" – 25.00 лв за нощувка
    •	"president apartment" – 35.00 лв за нощувка
    Според броят на дните, в които Дядо Коледа ще остане в хотела (пример: 11 дни = 10 нощувки)
    и видът на помещението, което ще избере, той може да ползва различно намаление.
    Намаленията спрямо дните и помещението са както следва:
    вид помещение	по-малко от 10 дни	между 10 и 15 дни	повече от 15 дни
    room for one person	не ползва намаление	не ползва намаление	не ползва намаление
    apartment	30% от крайната цена	35% от крайната цена	50% от крайната цена
    president apartment	10% от крайната цена	15% от крайната цена	20% от крайната цена
    След престоя си в хотела, оценката на Дядо Коледа за услугите на хотела може да е
    позитивна (positive) или негативна (negative). Ако оценката му е позитивна,
    към цената с вече приспаднатото намаление Дядо Коледа добавя 25%  към нея.
    Ако оценката му е негативна приспада от цената 10%.
Вход:
    Входът се чете от конзолата и се състои от три реда:
    •	Първи ред – дни за престой – цяло число в интервала [0...365]
    •	Втори ред –  вид помещение –  "room for one person",  "apartment" или "president apartment"
    •	Трети ред –  оценка - "positive"  или "negative"
Изход:
    На конзолата трябва да се отпечата един ред.
    •	Цената за престоят му в хотела, форматирана до втория знак след десетичната запетая
Примерен вход и изход:
    14
    apartment
    positive
    -> 264.06
        Брой нощувки: 14 дни = 13 нощувки
        Цена за престоя: 13 по 25.00 = 325 лв
        Проверяваме дали има отстъпка: 10 < 14 дни < 15 => 35%
        Цена за престоя с отстъпка: 325 – 35%= 211.25 лв
        Оценка: positive
        Крайна цена на престоя с оценката: 211.25 + 25%= 264.06 лв
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
package SoftUni.Exam;

import java.util.Scanner;
import java.util.List;

import static java.lang.System.*;

public class SantasHoliday {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int daysStay = setValue(0, 365);
        String typeOfRoom = setValue(null, null);
        String rating = setValue(null, null);
        
        calcPrice(daysStay, typeOfRoom, rating);
    }

    private static void calcPrice(
            int daysStay, 
            String typeOfRoom, 
            String rating
    ) {
        double priceOfNight = 0;
        double discount = 0;
        double tip = 0;
        
        switch (typeOfRoom) {
            case "room for one person": 
                priceOfNight = 18; 
                break;
            case "apartment":
                priceOfNight = 25;
                if (daysStay < 10)
                    discount = 30;
                else if (daysStay <= 15)
                    discount = 35;
                else 
                    discount = 50;
                break;
            case "president apartment":
                priceOfNight = 35;
                if (daysStay < 10)
                    discount = 10;
                else if (daysStay <= 15)
                    discount = 15;
                else
                    discount = 20;
        }
        int nights = daysStay - 1;
        
        double priceForStay = 
                nights * priceOfNight;
        double priceOfDiscount = 
                priceForStay * discount / 100;
        double priceAfterDiscount = 
                priceForStay - priceOfDiscount;
        
        switch (rating) {
            case "positive":
                tip = 25;
                break;
            case "negative":
                tip = -10;
                break;    
        }
        
        double priceOfTip = 
                priceAfterDiscount * tip / 100;
        double priceAfterTip =
                priceAfterDiscount + priceOfTip;
        
        out.printf("%.2f", priceAfterTip);
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

            if (requiredString){
                stringCount++;

                if (stringCount == 1) {
                    String[] required = { "room for one person", "apartment", "president apartment" };
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

                if (stringCount == 2) {
                    String[] required = { "positive", "negative" };
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
