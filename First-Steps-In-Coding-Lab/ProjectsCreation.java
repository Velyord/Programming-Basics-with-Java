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
import java.util.Scanner;

public class ProjectsCreation {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String archName = scanner.nextLine();
        int numOfProj = setNumOfProj();

        int hoursForProj = 3;
        int sumOfHours = numOfProj * hoursForProj;

        String output = "The architect %s will need %d hours to complete %d project/s.";
        out.printf(output, archName, sumOfHours, numOfProj);
    }
    private static int setNumOfProj() {
        int numOfProj;

        try {
            numOfProj = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            out.println("Не сте въвели число. Пробвайте пак!");
            return setNumOfProj();
        }
        if (numOfProj < 0 || numOfProj > 100) {
            out.println("Моля въведе число между 0 и 100!");
            return setNumOfProj();
        } else
            return numOfProj;
    }
}
