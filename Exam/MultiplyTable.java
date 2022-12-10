/*
Условие:
    Най-малкият син на семейство Иванови все още не знае таблицата за умножение.
    Веднага след коледните празници, той ще има контролно и вие трябва да му помогнете да я научи.
    Вашата задача е да съставите програма, в която се въвежда цяло трицифрено число и се извежда таблицата за умножение.
    Поради особености на Вашата програма, тя взима цифрите на въведеното число в обратен ред.
    Максималната стойност на множителите е определена от всяка една от 3-те цифри на въведеното число.
Вход:
    Входът е цяло трицифрено число в интервала [111…999].
Изход:
    Да се отпечатат на конзолата няколко на брой реда в следния формат:
    "{първата цифра} * {втората цифра} * {третата цифра} = {резултатът от умножението на трите цифри};"
    Първата, втората и третата цифра не могат да бъдат отрицателни числа или равни на 0!
Примерен вход и изход:
    324	->
    1 * 1 * 1 = 1;
    1 * 1 * 2 = 2;
    1 * 1 * 3 = 3;
    1 * 2 * 1 = 2;
    1 * 2 * 2 = 4;
    1 * 2 * 3 = 6;
    2 * 1 * 1 = 2;
    2 * 1 * 2 = 4;
    2 * 1 * 3 = 6;
    2 * 2 * 1 = 4;
    2 * 2 * 2 = 8;
    2 * 2 * 3 = 12;
    3 * 1 * 1 = 3;
    3 * 1 * 2 = 6;
    3 * 1 * 3 = 9;
    3 * 2 * 1 = 6;
    3 * 2 * 2 = 12;
    3 * 2 * 3 = 18;
    4 * 1 * 1 = 4;
    4 * 1 * 2 = 8;
    4 * 1 * 3 = 12;
    4 * 2 * 1 = 8;
    4 * 2 * 2 = 16;
    4 * 2 * 3 = 24;
        Вземаме цифрите от числото както следва:
        неговата последна за първо, средната за второ, и първата за трето число.
    222	->
    1 * 1 * 1 = 1;
    1 * 1 * 2 = 2;
    1 * 2 * 1 = 2;
    1 * 2 * 2 = 4;
    2 * 1 * 1 = 2;
    2 * 1 * 2 = 4;
    2 * 2 * 1 = 4;
    2 * 2 * 2 = 8;
*/
package SoftUni.Exam;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class MultiplyTable {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int number = setValue(111, 999);
        int reversedNumber = reverseNumber(number);

        createTable(reversedNumber);
    }

    private static int reverseNumber(int number) {
        int reversedNumber = 0;
        int setNumberPosition = 100;
        int lastDigit;

        for (int i=0; i<3; i++) {
            lastDigit = (number % 10);
            reversedNumber += lastDigit * setNumberPosition;
            number /= 10;
            setNumberPosition /= 10;
        }
        return reversedNumber;
    }

    private static void createTable(int reversedNumber) {
        for (int i=1; i<=reversedNumber/100; i++)
            for (int j=1; j<=reversedNumber/10%10; j++)
                for (int k=1; k<=reversedNumber%10; k++)
                    out.println(
                            i+" * "+j+" * "+k+" = "+(i*j*k)+";"
                    );
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
