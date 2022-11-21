/*
Условие:
    Напишете програма, която чете цяло число, въведено от потребителя, и отпечатва ден от седмицата
    (на английски език), в граници [1...7] или отпечатва "Error" в случай, че въведеното число е
    невалидно.
Примерен вход и изход"
    1
    -> Monday
    2
    -> Tuesday
    3
    -> Wednesday
    4
    -> Thursday
    5
    -> Friday
    6
    -> Saturday
    7
    -> Sunday
    -1
    -> Error
 */
package SoftUni.Lab7;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class DayOfWeek {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int day = Integer.parseInt(scanner.nextLine());
        
        switch (day) {
            case 1:     out.println("Monday");      break;
            case 2:     out.println("Tuesday");     break;
            case 3:     out.println("Wednesday");   break;
            case 4:     out.println("Thursday");    break;
            case 5:     out.println("Friday");      break;
            case 6:     out.println("Saturday");    break;
            case 7:     out.println("Sunday");      break;
            default:    out.println("Error");       break;
        }
    }
}
