/*
Условие:
    Преди време Петър си е купил биткойн.
    Сега ще ходи на екскурзия из Европа и ще му трябва евро.
    Освен биткойн има и китайски юанa.
    Той иска да обмени парите си в евро за екскурзията.
    Напишете програма, която да пресмята колко евро може да
    купи спрямо следните валутни курсове:
    •	1 биткойн = 1168 лева.
    •	1 китайски юан = 0.15 долара.
    •	1 долар = 1.76 лева.
    •	1 евро = 1.95 лева.
    Обменното бюро има комисионна от 0 до 5 процента от крайната сума в евро.
Вход:
    От конзолата се четат 3 числа:
    •	На първия ред – броят биткойни.
    Цяло число в интервала [0…20]
    •	На втория ред – броят китайски юана.
    Реално число в интервала [0.00… 50 000.00]
    •	На третия ред – комисионната.
    Реално число в интервала [0.00 ... 5.00]
Изход:
    На конзолата да се отпечата 1 число -
    резултатът от обмяната на валутите.
    Резултатът да се форматира до втората цифра след
    десетичната запетая.
Примерен вход и изход:
    1
    5
    5
    -> 569.67
        1 биткойн = 1168 лева
        5 юана = 0.75 долара
        0.75 долара = 1.32 лева
        1168 + 1.32 = 1169.32 лева = 599.651282051282 евро
        Комисионна: 5% от 599.651282051282 = 29.9825641025641
        Резултат: 599.651282051282 - 29.9825641025641 =
        569.668717948718 евро ~ 569.67
    20
    5678
    2.4
    -> 12442.24
    7
    50200.12
    3
    -> 10659.47
*/
package SoftUni.March2020;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class ChangeBureau {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int bitCoin = setValue(0, 20);
        double chineseYuan = Double.parseDouble(scanner.nextLine());
        double commission = setValue(0.0, 5.0);

        calculateMoneyForExcursion(
                bitCoin,
                chineseYuan,
                commission
        );
    }

    private static void calculateMoneyForExcursion(
            int bitCoin,
            double chineseYuan,
            double commission
    ) {
        double bitCoinToBGN = bitCoin * 1168;
        double chineseYuanToUSD = chineseYuan * 0.15;
        double dollarsToBGN = chineseYuanToUSD * 1.76;

        double moneyCollectedInBGN =
                bitCoinToBGN + dollarsToBGN;
        double moneyCollectedInEUR =
                moneyCollectedInBGN / 1.95;
        double commissionMoney =
                moneyCollectedInEUR * commission / 100;

        double moneyForExcursion =
                moneyCollectedInEUR - commissionMoney;

        out.printf("%.2f", moneyForExcursion);
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
