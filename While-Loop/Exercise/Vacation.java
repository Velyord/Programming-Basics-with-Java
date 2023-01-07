/*
Условие:
    Джеси е решила да събира пари за екскурзия и иска от вас да ѝ помогнете да разбере дали ще успее да събере
    необходимата сума. Тя спестява или харчи част от парите си всеки ден. Ако иска да похарчи повече от наличните си
    пари, то тя ще похарчи всичките и ще ѝ останат 0 лева.
Вход:
    От конзолата се четат:
    •	Пари нужни за екскурзията - реално число в интервала [1.00…25000.00]
    •	Налични пари - реално число в интервала [0.00...25000.00]
    След това многократно се четат по два реда:
    •	Вид действие - текст с възможности "spend" и "save"
    •	Сумата, която ще спести/похарчи - реално число в интервала [0.01…25000.00]
Изход:
    Програмата трябва да приключи при следните случаи:
    •	Ако 5 последователни дни Джеси само харчи, на конзолата да се изпише:
    o	"You can't save the money."
    o	"{Общ брой изминали дни}"
    •	Ако Джеси събере парите за почивката на конзолата се изписва:
    o	"You saved the money for {общ брой изминали дни} days."
Примерен вход и изход:
    2000
    1000
    spend
    1200
    save
    2000
    -> You saved the money for 2 days.
        Пари, нужни за екскурзията: 2000
        Налични пари: 1000
        spend - изваждаме от парите следващото число
                    (1000 - 1200 = -200, което е по-малко от 0
                       => налични пари = 0)
                ~ последователни дни, в които харчи = 1
                - общо дни : 1
        save - добавяме към парите следващото число
                   (0 + 2000 = 2000)
                ~ последователни дни, в които харчи = 0
                - общо дни : 2
        Наличните пари (2000) >= Пари, нужни за екскурзията (2000)
    110
    60
    spend
    10
    spend
    10
    spend
    10
    spend
    10
    spend
    10
    -> You can't save the money.
    -> 5
        Пари, нужни за екскурзията: 110
        Налични пари: 60
        spend – изваждаме от парите следващото число (60 - 10 = 50)
                ~ последователни дни, в които харчи = 1
                 - общо дни : 1
        spend – изваждаме от парите следващото число (50 - 10 = 40)
                ~ последователни дни, в които харчи = 2
                 - общо дни : 2
        spend – изваждаме от парите следващото число (40 - 10 = 30)
                ~ последователни дни, в които харчи = 3
                 - общо дни : 3
        spend – изваждаме от парите следващото число (30 - 10 = 20)
                ~ последователни дни, в които харчи = 4
                 - общо дни : 4
        spend – изваждаме от парите следващото число (20 - 10 = 10)
                ~ последователни дни, в които харчи = 5
                - общо дни : 5
        5 последователни дни харчи => налични пари: 10
        Наличните пари (10) < Пари, нужни за екскурзията (110)
    250
    150
    spend
    50
    spend
    50
    save
    100
    save
    100
    -> You saved the money for 4 days.
        Пари, нужни за екскурзията: 250
        Налични пари: 150
        spend - изваждаме от парите следващото число (150 - 50 = 100)
                ~ последователни дни, в които харчи = 1
                - общо дни : 1
        spend - изваждаме от парите следващото число (100 - 50 = 50)
                ~ последователни дни, в които харчи = 2
                - общо дни : 2
        save - добавяме към парите следващото число (50 + 100 = 150)
                ~ последователни дни, в които харчи = 0
                - общо дни : 3
        save - добавяме към парите следващото число (150 + 100 = 250)
                ~ последователни дни, в които харчи = 0
                 - общо дни : 4
        Наличните пари (250) >= Пари, нужни за екскурзията (250)
 */
package SoftUni.Exer12;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Vacation {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double neededMoney = setValue(1.00, 25000.00);
        double availableMoney = setValue(0.00, 25000.00);

        checkIfMoneySaved(neededMoney, availableMoney);
    }

    private static void checkIfMoneySaved(double neededMoney, double availableMoney) {
        int spentCount = 0;
        int days = 0;

        while (true) {
            String spendOrSave = setValue(null, null);
            double money = setValue(0.01, 25000.00);
            days++;

            switch (spendOrSave) {
                case "save":
                    spentCount = 0;
                    availableMoney += money;
                    break;
                case "spend":
                    spentCount++;
                    availableMoney -= money;
                    break;
            }

            if (availableMoney >= neededMoney) {
                out.printf("You saved the money for %d days.", days);
                break;
            }

            if (spentCount == 5) {
                out.printf("You can't save the money.\n%d", days);
                break;
            }

            if (availableMoney < 0)
                availableMoney = 0;
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

            String[] required = { "spend", "save" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");

                for (String thing : required)
                    out.print(thing + " | ");

                out.println();
                return setValue(null, null);
            }
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
