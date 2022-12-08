/*
Условие:
    Напишете програма, която чете от конзолата две шестцифрени цели числа в диапазона от 100000 до 300000.
    Винаги първото въведено число ще бъде по малко от второто. На конзолата да се отпечатат на 1 ред разделени с
    интервал всички числа, които се намират между двете, прочетени от конзолата числа и отговарят на следното условие:
    •	сумата от цифрите на четни и нечетни позиции да са равни. Ако няма числа, отговарящи на условието на конзолата
    не се извежда резултат.
Примерен вход и изход
    100000
    100050
    -> 100001 100012 100023 100034 100045
        Първото число, което генерираме е числото 100000. Сумата от цифрите на четни позиции (жълто) е 0+0+0=0.
        Сумата от цифрите на нечетни позиции (зелено) е 0+0+1=1. Тъй като двете суми са различни числото не се
        отпечатва. Следващото, число е 100001. Сумата на четни позиции е  1+0+0=1, а на нечетни 0+0+1=1. Двете суми са
        равни и числото се отпечатва.
        Следващото число за проверка е 100002. То не отговаря на условието и не се отпечатва.
        ……
        При числото 100045 сумата от четните позиции е 5+0+0=5, а на нечетни 4+0+1=5. Двете суми са равни числото се
        отпечатва. И т.н.
    123456
    124000
    -> 123464 123475 123486 123497 123530 123541 123552 123563 123574 123585 123596 123640 123651 123662 123673 123684
    -> 123695 123750 123761 123772 123783 123794 123860 123871 123882 123893 123970 123981 123992
    299900
    300000
    -> 299970 299981 299992
    100115
    100120
    -> Няма изход

*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class EqualSumsEvenOddPosition {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int sixDigits1 = setValue(100000, 300000);
        int sixDigits2 = setValue(sixDigits1, 300000);

        calcTask(sixDigits1, sixDigits2);
    }

    private static void calcTask(int sixDigits1, int sixDigits2) {
        for (int i = sixDigits1; i <= sixDigits2; i++) {
            int current = i;
            int sumOdd = 0, sumEven = 0;

            for (int j = 6; j > 0; j--) {
                int lastDigit = current % 10;
                current /= 10;

                if (j % 2 == 0)
                    sumEven += lastDigit;
                else
                    sumOdd += lastDigit;
            }

            if (sumEven == sumOdd) {
                out.printf(i + " ");
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
