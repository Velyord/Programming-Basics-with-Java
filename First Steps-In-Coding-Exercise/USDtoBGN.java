/*
Условие:
    Напишете програма за конвертиране на щатски долари (USD) в български лева (BGN). Използвайте фиксиран курс между долар и лев: 1 USD = 1.79549 BGN.
Примерен вход и изход:
    22
    -> 39.50078
    100
    -> 179.549
    12.5
    -> 22.443625
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class USDtoBGN {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double bgn = setDoubleValue();

        double bgnToUsd = convertBGNtoUSD(bgn);
        out.println(bgnToUsd);
    }

    private static double convertBGNtoUSD(double bgn) {
        double kurs = 1.79549;
        return bgn * kurs;
    }

    private static double setDoubleValue() {
        double value;
        out.println("Въведи сума български пари: ");
        
        try {
            value = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setDoubleValue();
        }
        
        if (value < 0) {
            out.println("Моля въведе положително число");
            return setDoubleValue();
        }
        else
            return value;
    }
}
