/*
Условие:
    Напишете програма, която изчислява колко часа ще са необходими на един архитект, за да изготви проектите на няколко строителни обекта. Изготвянето на един проект отнема три часа.
Вход:
    От конзолата се четат 2 реда:
    1. Името на архитекта - текст
    2. Брой на проектите, които трябва да изготви - цяло число в интервала [0 … 100]
Изход:
    На конзолата се отпечатва:
    "The architect {името на архитекта} will need {необходими часове} hours to complete {брой на проектите} project/s."
    Примерен вход и изход
        George
        4 
        -> The architect George will need 12 hours to complete 4 project/s. 
        Sanya
        9
        -> The architect Sanya will need 27 hours to complete 9 project/s.
*/
package SoftUni.Lab3;

import static java.lang.System.out;
import static java.lang.System.in;
import java.util.Scanner;

public class ProjectsCreation {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String archName = setStringValue();
        int numOfProj = setIntValue(0, 100);

        int sumOfHours = calculateSumOfHours(numOfProj);

        String output = "The architect %s will need %d hours to complete %d project/s.";
        out.printf(output, archName, sumOfHours, numOfProj);
    }

    private static int calculateSumOfHours(int numOfProj) {
        int hoursForProj = 3;

        return numOfProj * hoursForProj;
    }

    private static int setIntValue(int min, int max) {
        int value;

        try {
            value = Integer.parseInt(scanner.nextLine());
        }
        catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setIntValue(min, max);
        }

        if (value < min || value > max) {
            out.printf("Моля въведе число между %d и %d!", min, max);
            return setIntValue(min, max);
        }
        else
            return value;
    }

    private static String setStringValue() {
        String value;
        value = scanner.nextLine();

        boolean isSpecChar = false;
        String specialCharacters="!#$%&'()*+,./:;<=>?@[]^_`{|}0123456789";

        for (int i = 0; i < value.length(); i++)
            if (specialCharacters.contains(Character.toString(value.charAt(i)))) {
                isSpecChar = true;
            }

        if(isSpecChar) {
            out.println("Моля въведете правилно име!");
            return setStringValue();
        }
        else
            return value;
    }
}
