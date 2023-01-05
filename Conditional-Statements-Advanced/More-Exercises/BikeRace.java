/*
Условие:
    Предстои Вело състезание за благотворителност в което участниците са разпределени в
    младша("juniors") и старша("seniors") група. Парите се набавят от таксата за участие на
    велосипедистите. Според възрастовата група и вида на трасето на което ще се провежда състезанието,
    таксата е различна.
        Група
        trail
        cross-country
        downhill
        road
            juniors
            5.50
            8
            12.25
            20
                seniors
                7
                9.50
                13.75
                21.50
    Ако в "cross-country" състезанието се съберат 50 или повече участника(общо младши и старши),
    таксата  намалява с 25%. Организаторите отделят 5% процента от събраната сума за разходи.
Вход:
    От конзолата се четат 2 числа и един стринг, всяко на отделен ред:
        • Първият ред – броят младши велосипедисти. Цяло число в интервала [1…100]
        • Вторият ред – броят старши велосипедисти. Цяло число в интервала [1… 100]
        • Третият ред – вид трасе – "trail", "cross-country", "downhill" или "road"
Изход:
    Да се отпечата на конзолата едно число:
    "{дарената сума}" - форматирана с точност до 2 знака след десетичната запетая.
Примерен вход и изход:
    10
    20
    trail
    -> 185.25
        Trail такса за juniors = 5.5 и за seniors = 7
        Събрана сума = 10*5.5 + 20*7 = 55 + 140 = 195
        Разходи = 5% от 195 = 9.75
        Остават = 185.25
    21
    26
    cross-country
    -> 394.25
    30
    25
    cross-country
    -> 340.22
    10
    10
    downhill
    -> 247.00
    3
    40
    road
    -> 874.00
*/
package SoftUni.MoreExercises.ConditionalStatementsAdvanced;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class BikeRace {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = true;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int juniorsCount = setValue(1, 100);
        int seniorsCount = setValue(1, 100);
        String raceType = setValue(null, null);

        calcAndPrintCharityMoney(juniorsCount, seniorsCount, raceType);
    }

    private static void calcAndPrintCharityMoney(int juniorsCount, int seniorsCount, String raceType) {
        double participants = juniorsCount + seniorsCount;
        double expensePercent = 5;
        double seniorsTax = 0;
        double juniorsTax = 0;

        switch (raceType) {
            case "trail":
                juniorsTax = 5.50;
                seniorsTax = 7.00;
                break;
            case "cross-country":
                if (participants >= 50) {
                    juniorsTax = 8.00 * 0.75;
                    seniorsTax = 9.50 * 0.75;
                } else {
                    juniorsTax = 8.00;
                    seniorsTax = 9.50;
                }
                break;
            case "downhill":
                juniorsTax = 12.25;
                seniorsTax = 13.75;
                break;
            case "road":
                juniorsTax = 20.00;
                seniorsTax = 21.50;
                break;
        }

        double cash = juniorsCount * juniorsTax + seniorsCount * seniorsTax;
        double expenseMoney = cash * expensePercent / 100;
        double charityMoney = cash - expenseMoney;

        out.printf("%.2f", charityMoney);
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
                    String[] required = { "trail", "cross-country", "downhill", "road" };
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
