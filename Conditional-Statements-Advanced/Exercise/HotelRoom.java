/*
Условие:
    Хотел предлага 2 вида стаи: студио и апартамент. Напишете програма, която изчислява цената за целия престой за
    студио и апартамент. Цените зависят от месеца на престоя:
        Май и октомври	Юни и септември	Юли и август
        Студио – 50 лв./нощувка	Студио – 75.20 лв./нощувка	Студио – 76 лв./нощувка
        А  партамент – 65 лв./нощувка	Апартамент – 68.70 лв./нощувка	Апартамент – 77 лв./нощувка
    Предлагат се и следните отстъпки:
    •	За студио, при повече от 7 нощувки през май и октомври : 5% намаление.
    •	За студио, при повече от 14 нощувки през май и октомври : 30% намаление.
    •	За студио, при повече от 14 нощувки през юни и септември: 20% намаление.
    •	За апартамент, при повече от 14 нощувки, без значение от месеца : 10% намаление.
Вход:
    Входът се чете от конзолата и съдържа точно 2 реда, въведени от потребителя:
    •	На първия ред е месецът – May, June, July, August, September или October
    •	На втория ред е броят на нощувките – цяло число в интервала [0 ... 200]
Изход:
    Да се отпечатат на конзолата 2 реда:
    •	На първия ред: “Apartment: {цена за целият престой} lv.”
    •	На втория ред: “Studio: {цена за целият престой} lv.“
    Цената за целия престой форматирана с точност до два знака след десетичната запетая.
Примерен вход и изход:
    May
    15
    -> Apartment: 877.50 lv.
        Studio: 525.00 lv.	През май, при повече от 14 нощувки, намаляваме цената на студиото с 30% (50 – 15 = 35), а на апартамента – с 10% (65 – 6.5 =58.5).
        Целият престой в апартамент – 877.50 лв.
        Целият престой в студио – 525.00 лв.
    June
    14
    -> Apartment: 961.80 lv.
        Studio: 1052.80 lv.	August
        20	Apartment: 1386.00 lv.
        Studio: 1520.00 lv.
 */
package SoftUni.Exer8;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.List;
import java.util.Scanner;

public class HotelRoom {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String month = setStringValue();
        int nights = setValue(0, 200);

        calculateStay(month, nights);
    }

    private static void calculateStay(String month, int nights) {
        double priceForNightInStudio = 0;
        double priceForNightInApartment = 0;
        double discountPercentForStudio = 0;
        double discountPercentForApartment = 0;

        switch (month) {
            case "May":
            case "October":
                priceForNightInStudio = 50;
                priceForNightInApartment = 65;
                if (nights > 7)
                    discountPercentForStudio = 5;
                if (nights > 14)
                    discountPercentForStudio = 30;
                break;
            case "June":
            case "September":
                priceForNightInStudio = 75.20;
                priceForNightInApartment = 68.70;
                if (nights > 14)
                    discountPercentForStudio = 20;
                break;
            case "July":
            case "August":
                priceForNightInStudio = 76;
                priceForNightInApartment = 77;
                break;
            default:
                break;
        }

        if (nights > 14)
            discountPercentForApartment = 10;

        double priceForStudioAfterDiscount = calculatePriceAfterDiscount(
                priceForNightInStudio,
                discountPercentForStudio,
                nights
        );
        double priceForApartmentAfterDiscount = calculatePriceAfterDiscount(
                priceForNightInApartment,
                discountPercentForApartment,
                nights
        );
        
        displayPrices(priceForApartmentAfterDiscount, priceForStudioAfterDiscount);        
    }

    private static void displayPrices(double priceForApartmentAfterDiscount, double priceForStudioAfterDiscount) {
        out.printf("Apartment: %.2f lv.\n", priceForApartmentAfterDiscount);
        out.printf("Studio: %.2f lv.", priceForStudioAfterDiscount);
    }

    private static double calculatePriceAfterDiscount(double priceForNight, double discountPercent, int nights) {
        double price = priceForNight * nights;
        double discountPrice = price * discountPercent / 100;

        return price - discountPrice;
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

        String[] required = { "May", "June", "July", "August", "September", "October" };
        List<String> requiredList = List.of(required);

        if (!requiredList.contains(value)){
            out.print("Моля въведете един от следните месеци: | ");
            for (String month : required)
                out.print(month + " | ");

            return setStringValue();
        }

        return value;
    }
}
