/*
Условие:
    Напишете програма, която пресмята нужните разходи за закупуването на храна за кучета и котки. Храната се пазарува от зоомагазин, като една опаковка храна за кучета е на цена 2.50 лв, а опаковка храна за котки струва 4 лв.
Вход:
    От конзолата се четат 2 реда:
    1. Броят на опаковките храна за кучета – цяло число в интервала [0… 100]
    2. Броят на опаковките храна за котки – цяло число в интервала [0… 100]
Изход:
    На конзолата се отпечатва:
    "{крайната сума} lv."
Примерен вход и изход:
    5
    4
    -> 28.5 lv. 
    13
    9
    -> 68.5 lv.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        out.println("Броят на опаковките храна за кучета: ");
        int numOfDogFoodPacks = Integer.parseInt(scanner.nextLine());
        out.println("Броят на опаковките храна за котки: ");
        int numOfCatFoodPacks = Integer.parseInt(scanner.nextLine());

        double priceOfDogFoodPack = 2.50;
        double priceOfCatFoodPack = 4.00;

        double sumPrice = 
                priceCalculator(numOfDogFoodPacks, priceOfDogFoodPack)
                + priceCalculator(numOfCatFoodPacks, priceOfCatFoodPack);

        out.printf("%.2f lv.", sumPrice);
    }

    private static double priceCalculator(int numPacks, double pricePack) {
        return numPacks * pricePack;
    }
}
