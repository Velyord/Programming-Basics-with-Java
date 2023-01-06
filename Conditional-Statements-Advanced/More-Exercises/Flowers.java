/*
Условие:
    Магазин за цветя предлага 3 вида цветя: хризантеми, рози и лалета. Цените зависят от сезона.
        Сезон
        Хризантеми
        Рози
        Лалета
        Пролет / Лято
        2.00 лв./бр.
        4.10 лв./бр.
        2.50 лв./бр.
        Есен / Зима
        3.75 лв./бр.
        4.50 лв./бр.
        4.15 лв./бр.
В празнични дни цените на всички цветя се увеличават с 15%. Предлагат се следните отстъпки:
    • За закупени повече от 7 лалета през пролетта – 5% от цената на целият букет.
    • За закупени 10 или повече рози през зимата – 10% от цената на целият букет.
    • За закупени повече от 20 цветя общо през всички сезони – 20% от цената на целият букет.
    Отстъпките се правят по така написания ред и могат да се наслагват!
    Всички отстъпки важат след оскъпяването за празничен ден!
    Цената за аранжиране на букета винаги е 2лв. Напишете програма,
    която изчислява цената за един букет.
Вход:
    Входът се чете от конзолата и съдържа точно 5 реда:
        • На първия ред е броят на закупените хризантеми – цяло число в интервала [0 ... 200]
        • На втория ред е броят на закупените рози – цяло число в интервала [0 ... 200]
        • На третия ред е броят на закупените лалета – цяло число в интервала [0 ... 200]
        • На четвъртия ред е посочен сезона – [Spring, Summer, Аutumn, Winter]
        • На петия ред е посочено дали денят е празник – [Y – да / N - не]
Изход:
    Да се отпечата на конзолата 1 число – цената на цветята,
    форматирана до вторият знак след десетичната запетая.
Примерен вход и изход:
    2
    4
    8
    Spring
    Y
    -> 46.14
        Цена: 2*2.00 + 4*4.10 + 8*2.50 = 40.40 лв.
        Празничен ден  40.40 + 15% = 46.46 лв.
        5% намаление за повече от 7 лалета през пролетта – 44.14.
        Общо цветята са 20 или по-малко – няма намаление
        44.14 + 2 за аранжиране = 46.14 лв.
    3
    10
    9
    Winter
    N
    -> 69.39
        Цена: 3*3.75 + 10*4.50 + 9*4.15 = 93.60 лв.
        Не е празничен ден – няма увеличение
        10 % намаление за 10 или повече рози през зимата – 84.24.
        Общо цветята са повече от 20 – 20% намаление = 67.392
        67.392 + 2 за аранжиране = 69.392 лв.
    10
    10
    10
    Autumn
    N
    -> 101.20
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Flowers {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int boughtHrizantemi = setValue(0, 200);
        int boughtRozi = setValue(0, 200);
        int boughtLaleta = setValue(0, 200);
        String season = setValue(null, null);
        String isHoliday = setValue(null, null);

        double flowersPrice = flowersPrice(
                boughtHrizantemi,
                boughtRozi,
                boughtLaleta,
                season,
                isHoliday
        );

        out.printf("%.2f", flowersPrice);
    }

    private static double flowersPrice(
            int boughtHrizantemi,
            int boughtRozi,
            int boughtLaleta,
            String season,
            String isHoliday
    ) {
        double priceOfHrizantemi = 0;
        double priceOfRozi = 0;
        double priceOfLaleta = 0;

        switch (season) {
            case "Spring":
            case "Summer":
                priceOfHrizantemi = 2;
                priceOfRozi = 4.10;
                priceOfLaleta = 2.50;
                break;
            case "Autumn":
            case "Winter":
                priceOfHrizantemi = 3.75;
                priceOfRozi = 4.50;
                priceOfLaleta = 4.15;
        }
        if (isHoliday.equals("Y")) {
            priceOfHrizantemi *= 1.15;
            priceOfRozi *= 1.15;
            priceOfLaleta *= 1.15;
        }
        double priceForHrizantemi = boughtHrizantemi * priceOfHrizantemi;
        double priceForRozi = boughtRozi * priceOfRozi;
        double priceForLaleta = boughtLaleta * priceOfLaleta;
        double rawPriceForFlowers = priceForHrizantemi + priceForRozi + priceForLaleta;

        if (season.equals("Spring") && boughtLaleta > 7)
            rawPriceForFlowers *= 0.95;
        if (season.equals("Winter") && boughtRozi >= 10)
            rawPriceForFlowers *= 0.9;
        if (boughtHrizantemi + boughtRozi + boughtLaleta > 20)
            rawPriceForFlowers *= 0.8;

        double arrangement = 2;

        return rawPriceForFlowers + arrangement;
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
