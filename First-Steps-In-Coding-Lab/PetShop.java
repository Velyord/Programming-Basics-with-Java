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
import static java.lang.System.in;
import java.util.Scanner;

public class PetShop {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int numOfDogFoodPacks = setIntValue(0, 100, "кучета");
        int numOfCatFoodPacks = setIntValue(0, 100, "котки");
        
        double price = calculatePrice(numOfDogFoodPacks, numOfCatFoodPacks);

        out.printf("%.2f lv.", price);
    }

    private static double calculatePrice(int numOfDogFoodPacks, int numOfCatFoodPacks) {
        double priceOfDogFoodPack = 2.50;
        double priceOfCatFoodPack = 4.00;
        
        double priceForDogFoodPacks = numOfDogFoodPacks * priceOfDogFoodPack;
        double priceForCatFoodPacks = numOfCatFoodPacks * priceOfCatFoodPack;
        
        return priceForDogFoodPacks + priceForCatFoodPacks;
    }
    
    private static int setIntValue(int min, int max, String object) {
        int value;
        out.println("Въведете броят на опаковките храна за " + object + ":");

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max, object);
        }

        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max, object);
        }
        else
            return value;
    }
}
