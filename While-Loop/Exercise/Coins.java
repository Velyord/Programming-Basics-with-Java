/*
Условие:
    Производителите на вендинг машини искали да направят машините си да връщат възможно най-малко монети ресто. Напишете програма, която приема сума - рестото, което трябва да се върне и изчислява с колко най-малко монети може да стане това.
Примерен вход и изход:
1.23
-> 4
    Рестото ни е 1 лев и 23 стотинки. Машината ни го връща с 4 монети: монета от 1 лев, монета от 20 стотинки,
    монета от 2 стотинки и монета от 1 стотинка.
2
-> 1
    Рестото ни е 2 лева. Машината ни го връща с 1 монета от 2 лева.
0.56
-> 3
    Рестото ни е 56 стотинки. Машината ни го връща с 3 монети: монета от 50 стотинки,
    монета от 5 стотинки и монета от 1 стотинка.
2.73
-> 5
    Рестото ни е 2 лева и 73 стотинки. Машината ни го връща с 5 монети: монета от 2 лева, монета от 50 стотинки,
    монета от 20 стотинки, монета от 2 стотинки и монета от 1 стотинка.

 */
package SoftUni.Exer12;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Coins {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double money = setValue(0.0, Double.MAX_VALUE);

        moneyIntoCoins(money);
    }

    private static void moneyIntoCoins(double money) {
        int moneyInCoins = (int) (money * 100);
        int coins = 0;

        while (moneyInCoins > 0) {
            coins++;
            
            if      (moneyInCoins >= 200) moneyInCoins -= 200;
            else if (moneyInCoins >= 100) moneyInCoins -= 100;
            else if (moneyInCoins >= 50)  moneyInCoins -= 50;
            else if (moneyInCoins >= 20)  moneyInCoins -= 20;
            else if (moneyInCoins >= 10)  moneyInCoins -= 10;
            else if (moneyInCoins >= 5)   moneyInCoins -= 5;
            else if (moneyInCoins >= 2)   moneyInCoins -= 2;
            else                          moneyInCoins -= 1;
        }
        out.println(coins);
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
                    if ((int) min == 0 && (int) max == Integer.MAX_VALUE)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == Double.MAX_VALUE)
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
