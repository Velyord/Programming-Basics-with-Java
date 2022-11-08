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
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {

        out.println("Броят на опаковките храна за кучета: ");
        int numOfDogFoodPacks = setNumOfFoodPacks(); // Проверява дали са в диапазон [0 ... 100]
        out.println("Броят на опаковките храна за котки: ");
        int numOfCatFoodPacks = setNumOfFoodPacks();

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
    private static int setNumOfFoodPacks() {
        int numOfFoodPacks;

        try {
            numOfFoodPacks = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setNumOfFoodPacks();
        }
        if (numOfFoodPacks < 0 || numOfFoodPacks > 100) {
            out.println("Моля въведе число между 0 и 100!");
            return setNumOfFoodPacks();
        } else
            return numOfFoodPacks;
    }
}
