/*
Условие:
    Джеси решава, че иска да се занимава с баскетбол, но за да тренира е нужна екипировка. Напишете програма, която изчислява какви разходи ще има Джеси, ако започне да тренира, като знаете колко е таксата за тренировки по баскетбол за период от 1 година. Нужна екипировка: 
    • Баскетболни кецове – цената им е 40% по-малка от таксата за една година
    • Баскетболен екип – цената му е 20% по-евтина от тази на кецовете
    • Баскетболна топка – цената ѝ е 1 / 4 от цената на баскетболния екип
    • Баскетболни аксесоари – цената им е 1 / 5 от цената на баскетболната топка
Вход:
    От конзолата се четe 1 ред:
    • Годишната такса за тренировки по баскетбол – цяло число в интервала [0… 9999]
Изход:
    Да се отпечата на конзолата колко ще са разходите на Джеси, ако започне да спортува баскетбол.
Примерен вход и изход:
    365
    -> 811.76
        Цена на тренировките за година: 365
        Цена на баскетболните кецове: 365 – 40% = 219
        Цена на баскетболен екип: 219 – 20% = 175.20
        Цена на баскетболна топка: 1 / 4 от 175.20 = 43.80
        Цена на баскетболни аксесоари: 1 /  5 от 43.80 = 8.76
        Обща цена за екипировката: 365 + 219 + 175.20 + 43.80 + 8.76 = 811.76
    550
    -> 1223.2
        Цена на тренировките за година: 550
        Цена на баскетболните кецове: 550 – 40% = 330
        Цена на баскетболен екип: 330 – 20% = 264
        Цена на баскетболна топка: 1 / 4 от 264 = 66
        Цена на баскетболни аксесоари: 1 /  5 от 66 = 13.20
        Обща цена за екипировката: 550 + 330 + 264 + 66 + 13.20 = 711.68
*/
package SoftUni.Exer4;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class BasketballEquipment {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int yearlyTax = setIntValue(0, 9999);
        
        double price = calculatePrice(yearlyTax);
        
        out.printf("%.2f", price);
    }
    private static double calculatePrice(int yearlyTax) {
        double kecove = yearlyTax * 0.6;
        double ekip = kecove * 0.8;
        double topka = ekip * 0.25;
        double aksesors = topka * 0.2;
        
        return yearlyTax + kecove + ekip + topka + aksesors;
    }
    private static int setIntValue(int min, int max) {
        out.println("Въведете годишна такса за тренировки по баскетбол:");
        int value;
        
        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max);
        }
        
        if (value < min || value > max) {
            out.printf("Моля въведете число между %d и %d!\n", min, max);
            return setIntValue(min, max);
        }
        else
            return value;
    }
}
