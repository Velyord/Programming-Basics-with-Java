// Напишете програма за конвертиране на щатски долари (USD) в български лева (BGN). Използвайте фиксиран курс между долар и лев: 1 USD = 1.79549 BGN.
    // Примерен вход и изход
        // 22 -> 39.50078 
        // 100 -> 179.549
        // 12.5 -> 22.443625

package Exe4;

import java.util.Scanner;

import static java.lang.System.out;

public class USDtoBGN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double kurs = 1.79549;
        double bgn = Double.parseDouble(scanner.nextLine());
        double bgnToUsd = convertBGNtoUSD(bgn, kurs);
        out.println(bgnToUsd);
    }

    private static double convertBGNtoUSD(double bgn, double kurs) {
        return bgn*kurs;
    }
}
