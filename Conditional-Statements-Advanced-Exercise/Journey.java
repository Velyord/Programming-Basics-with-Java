/*
Условие:
    Странно, но повечето хора си плануват от рано почивката. Млад програмист разполага с определен
    бюджет и свободно време в даден сезон. Напишете програма, която да приема на входа бюджета и сезона,
    а на изхода да изкарва, къде ще почива програмиста и колко ще похарчи.
    Бюджета определя дестинацията, а сезона определя колко от бюджета ще изхарчи. Ако е лято ще почива на къмпинг, а зимата в хотел. Ако е в Европа, независимо от сезона ще почива в хотел. Всеки къмпинг или хотел, според дестинацията, има собствена цена която отговаря на даден процент от бюджета:
    • При 100лв. или по-малко – някъде в България
        ◦ Лято – 30% от бюджета
        ◦ Зима – 70% от бюджета
    • При 1000лв. или по малко – някъде на Балканите
        ◦ Лято – 40% от бюджета
        ◦ Зима – 80% от бюджета
    • При повече от 1000лв. – някъде из Европа
        ◦ При пътуване из Европа, независимо от сезона ще похарчи 90% от бюджета.
Вход:
    Входът се чете от конзолата и се състои от два реда, въведени от потребителя:
        • Първи ред – Бюджет, реално число в интервала [10.00...5000.00].
        • Втори ред –  Един от двата възможни сезона: „summer” или “winter”
Изход:
    На конзолата трябва да се отпечатат два реда.
        • Първи ред – "Somewhere in [дестинация]“ измежду "Bulgaria", "Balkans" и "Europe"
        • Втори ред – "{Вид почивка} – {Похарчена сума}"
            ◦ Почивката може да е между "Camp" и "Hotel"
            ◦ Сумата трябва да е закръглена с точност до вторият знак след запетаята.
Примерен вход и изход:
    50
    summer
    -> Somewhere in Bulgaria
    -> Camp - 15.00
    75
    winter
    -> Somewhere in Bulgaria
    -> Hotel - 52.50
    312
    summer
    -> Somewhere in Balkans
    -> Camp - 124.80
    678.53
    winter
    -> Somewhere in Balkans
    -> Hotel - 542.82
    1500
    summer
    -> Somewhere in Europe
    -> Hotel - 1350.00
 */
package SoftUni.Exer8;

import java.util.Scanner;

import static java.lang.System.*;

public class Journey {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        double budget = setValue(10.0, 5000.0);
        String season = setStringValue();

        displayDestinationAndMoneySpent(budget, season);
    }

    private static void displayDestinationAndMoneySpent(double budget, String season) {
        String destination = setDestination(budget);
        String vacation = setVacation(season, destination);
        double spentMoney = calculateSpentMoney(season, destination, budget);

        out.printf("Somewhere in %s\n%s - %.2f", destination, vacation, spentMoney);
    }

    private static double calculateSpentMoney(String season, String destination, double budget) {
        int percentOfBudget = setPercentOfBudget(season, destination);

        return budget * (percentOfBudget / 100.0);
    }

    private static int setPercentOfBudget(String season, String destination) {
        switch (destination) {
            case "Bulgaria":
                switch (season) {
                    case "summer":  return 30;
                    case "winter":  return 70;
                } break;
            case "Balkans":
                switch (season) {
                    case "summer":  return 40;
                    case "winter":  return 80;
                } break;
            case "Europe":
                switch (season) {
                    case "summer":
                    case "winter":  return 90;
                } break;
        }
        return 0;
    }

    private static String setVacation(String season, String destination) {
        if (season.equals("summer") && !destination.equals("Europe"))
            return "Camp";
        else
            return "Hotel";
    }

    private static String setDestination(double budget) {
        if (budget <= 100)
            return "Bulgaria";
        else if (budget <= 1000)
            return "Balkans";
        else
            return "Europe";
    }

    @SuppressWarnings("unchecked")
    private static <T> T setValue(T min, T max) {
        Object value;
        boolean isIntArgs = min instanceof Integer && max instanceof Integer;
        // out.println("Въведете :");

        try {
            if (isIntArgs)
                value = Integer.parseInt(scanner.nextLine());
            else
                value = Double.parseDouble(scanner.nextLine());
        } catch (Exception e) {
            out.println("Не сте въвели число. Пробвайте пак!");
            return setValue(min, max);
        }

        if (isIntArgs) {
            if ((int) value < (int) min || (int) value > (int) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        } else {
            if ((double) value < (double) min || (double) value > (double) max) {
                out.printf("Моля въведете число между %s и %s!\n", min, max);
                return setValue(min, max);
            }
        }

        return (T) value;
    }

    private static String setStringValue() {
        String specialCharacters = "!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";
        boolean isSpecChar = false;
        // out.println("Въведете:");
        String value = scanner.nextLine();

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
                break;
            }

        if (isSpecChar) {
            out.println("Моля въведете правилно наименование!");
            return setStringValue();
        } else
            return value;
    }
}
