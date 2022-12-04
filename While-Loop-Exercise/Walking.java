/*
Условие:
    Габи иска да започне здравословен начин на живот и си е поставила за цел да върви 10 000 стъпки всеки ден.
    Някои дни обаче е много уморена от работа и ще иска да се прибере преди да постигне целта си. Напишете програма,
    която чете от конзолата по колко стъпки изминава тя всеки път като излиза през деня и когато постигне целта си
    да се изписва "Goal reached! Good job!"  и колко стъпки повече е извървяла
    "{разликата между стъпките} steps over the goal!".
    Ако иска да се прибере преди това, тя ще въведе командата "Going home" и ще въведе стъпките,
    които е извървяла докато се прибира. След което, ако не е успяла да постигне целта си,
    на конзолата трябва да се изпише: "{разликата между стъпките} more steps to reach goal."
Примерен вход и изход:
    1000
    1500
    2000
    6500
    -> Goal reached! Good job!
    -> 1000 steps over the goal!
    1500
    300
    2500
    3000
    Going home
    200
    -> 2500 more steps to reach goal.
    1500
    3000
    250
    1548
    2000
    Going home
    2000
    -> Goal reached! Good job!
    -> 298 steps over the goal!
    125
    250
    4000
    30
    2678
    4682
    -> Goal reached! Good job!
    -> 1765 steps over the goal
 */
package SoftUni.Exer12;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class Walking {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        stepsCalc();
    }

    private static void stepsCalc() {
        int stepsCount = 0;
        int goal = 10000;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("Going home")) {
                input = scanner.nextLine();
                int inputInNumber = Integer.parseInt(input);
                stepsCount += inputInNumber;
                int stepDiff = abs(stepsCount - goal);

                if (stepsCount >= goal)
                    displaySuccess(stepDiff);
                else
                    displayFailure(stepDiff);

                break;
            } else {
                int inputInNumber = Integer.parseInt(input);
                stepsCount += inputInNumber;

                if (stepsCount >= goal) {
                    int stepDiff = abs(stepsCount - goal);
                    displaySuccess(stepDiff);
                    break;
                }
            }
        }
    }

    private static void displaySuccess(int stepDiff) {
        out.println("Goal reached! Good job!");
        out.printf("%d steps over the goal!", stepDiff);
    }

    private static void displayFailure(int stepDiff) {
        out.printf("%d more steps to reach goal.", stepDiff);
    }
}
