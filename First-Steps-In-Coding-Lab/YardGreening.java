/*
Условие:
    Божидара разполага с няколко къщи на Черноморието и желае да озелени дворовете на някои от тях, като по този начин създаде уютна обстановка и комфорт на гостите си. За целта е наела фирма.
    Напишете програма, която изчислява необходимате сума, които Божидара ще трябва да заплати на фирмата изпълнител на проекта. Цената на един кв. м. е 7.61 лв със ДДС. Понеже нейният двор е доста голям, фирмата изпълнител предлага 18% отстъпка от крайната цена.
Вход:
    От конзолата се прочита само един ред:
    1. Кв. метри, които ще бъдат озеленени – реално число в интервала [0.00 … 10000.00]
Изход:
    На конзолата се отпечатват два реда:
    "The final price is: {крайна цена на услугата} lv."
    "The discount is: {отстъпка} lv."
Примерен вход и изход:
    550
    -> The final price is: 3432.11 lv.
       The discount is: 753.39 lv.
            Пресмятаме цената за озеленяване на целия двор: 550 * 7.61 = 4185.50 лв.
            Приспадаме отстъпката (18% = 0.18) от общата сума: 0.18 * 4185.5 = 753.39 лв.
            Калкулираме крайната цена на услугата: 4185.50 – 753.39 = 3432.11 лв.
    150
    -> The final price is: 936.03 lv.
       The discount is: 205.47 lv.
            Пресмятаме цената за озеленяване на целия двор: 150 * 7.61 = 1141.50 лв.
            Приспадаме отстъпката (18% = 0.18) от общата сума: 0.18 * 1141.50 = 205.47 лв.
            Калкулираме крайната цена на услугата: 1141.50 – 205.47 = 936.03 лв.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class YardGreening {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double kvm = setDoubleValue(0, 10000);

        double priceForKvm = 7.61;
        double discountPercentage = 0.18;

        double priceOfGreening = kvm * priceForKvm;
        double discount = priceOfGreening * discountPercentage;
        double finalPrice = priceOfGreening - discount;

        String output = "The final price is: %.2f lv. %nThe discount is: %.2f lv.";
        out.printf(output, finalPrice, discount);
    }
    private static double setDoubleValue(double min, double max) {
        double value;
        out.println("Кв. метри, които ще бъдат озеленени: ");

        try {
            value = Double.parseDouble(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setDoubleValue(min, max);
        }
        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setDoubleValue(min, max);
        }
        else
            return value;
    }
}
