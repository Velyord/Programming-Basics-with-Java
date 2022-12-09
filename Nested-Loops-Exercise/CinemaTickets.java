/*
Условие:
    Вашата задача е да напишете програма, която да изчислява процента на билетите за всеки тип от продадените билети:
    студентски(student), стандартен(standard) и детски(kid), за всички прожекции. Трябва да изчислите и колко процента
    от залата е запълнена за всяка една прожекция.
Вход:
    Входът е поредица от цели числа и текст:
    •	На първия ред до получаване на командата "Finish" - име на филма – текст
    •	На втори ред – свободните места в салона за всяка прожекция – цяло число [1 … 100]
    •	За всеки филм, се чете по един ред до изчерпване на свободните места в залата
    или до получаване на командата "End":
    o	Типа на закупения билет - текст ("student", "standard", "kid")
Изход:
    На конзолата трябва да се печатат следните редове:
    •	След всеки филм да се отпечата, колко процента от кино залата е пълна
    "{името на филма} - {процент запълненост на залата}% full."
    •	При получаване на командата "Finish" да се отпечатат четири реда:
    o	"Total tickets: {общият брой закупени билети за всички филми}"
    o	"{процент на студентските билети}% student tickets."
    o	"{процент на стандартните билети}% standard tickets."
    o	"{процент на детските билети}% kids tickets."
Примерен вход и изход:
    Taxi
    10
    standard
    kid
    student
    student
    standard
    standard
    End
    Scary Movie
    6
    student
    student
    student
    student
    student
    student
    Finish
    -> Taxi - 60.00% full.
    -> Scary Movie - 100.00% full.
    -> Total tickets: 12
    -> 66.67% student tickets.
    -> 25.00% standard tickets.
    -> 8.33% kids tickets.
        Първи филм – Taxi, местата в залата са 10
        Купуват се 3 стандарти, 2 студентски, 1 детски билет и получаваме командата End.
        Общо 6 билета от 10 места -> 60% от залата е заета.
        Втори филм – Scary Movie, места в залата са 6
        Купуват се 6 студентски билета и местата в залата свършват.
        Общо 6 билета от 6 места -> 100% от залата е заета.
        Получаваме командата Finish
        Общо закупените билети за всички филми са 12.
        За всички филми са закупени общо:
        8 студентски билета. 8 билета от общо 12 е 66.67%
        3 стандартни билета. 3 билета от общо 12 е 25%
        1 детски билет. 1 билет от общо 12 е 8.33%
    The Matrix
    20
    student
    standard
    kid
    kid
    student
    student
    standard
    student
    End
    The Green Mile
    17
    student
    standard
    standard
    student
    standard
    student
    End
    Amadeus
    3
    standard
    standard
    standard
    Finish
    -> The Matrix - 40.00% full.
    -> The Green Mile - 35.29% full.
    -> Amadeus - 100.00% full.
    -> Total tickets: 17
    -> 41.18% student tickets.
    -> 47.06% standard tickets.
    -> 11.76% kids tickets.
        Първи филм – The Matrix, местата в залата са 20
        Купуват се 2 стандартни, 4 студентски, 2 детски билета и получаваме командата End.
        Общо 8 билета от 20 места -> 41.18% от залата е заета
        Втори филм - The Green Mile, местата в залата са 17
        Купуват се 3 стандартни, 3 студентски билета и получаваме командата End.
        Общо 6 билета от 17 места -> 47.06% от залата е заета
        Трети филм – Amadeus, местата в залата са 3
        Купуват се 3 стандартни билета и местата в залата свършват.
        Общо 3 билета от 3 места -> 100% от залата е заета.
        Получаваме командата Finish
        Общо закупените билети за всички филми са 17.
        За всички филми са закупени общо:
        7 студентски билета. 7 билета от общо 17 е 41.18%
        8 стандартни билета. 8 билета от общо 17 е 47.06%
        2 детски билета. 2 билета от общо 17 е 11.76%
*/
package SoftUni.Exer14;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class CinemaTickets {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        movieAnalytics();
    }

    private static void movieAnalytics() {
        int totalTickets = 0;
        int studentTickets = 0;
        int standardTickets = 0;
        int kidsTickets = 0;
        String input = scanner.nextLine();

        while (!input.equals("Finish")) {
            String movieName = input;
            int freeSpaces = setValue(1, 100);
            int freeSpacesInTheBeginning = freeSpaces;
            String type = setValue(null, null);

            loop: while (!type.equals("End") && freeSpaces > 0) {
                switch (type) {
                    case "student":  studentTickets++;  break;
                    case "standard": standardTickets++; break;
                    case "kid":      kidsTickets++;     break;
                    default: break loop;
                }
                totalTickets++;
                --freeSpaces;
                
                if (freeSpaces == 0)
                    break;
                
                type = setValue(null, null);
            }
            double percentFull = 100 - (double) freeSpaces / freeSpacesInTheBeginning * 100;

            out.printf("%s - %.2f%% full.\n", movieName, percentFull);

            input = scanner.nextLine();
        }
        displayResult(totalTickets, studentTickets, standardTickets, kidsTickets);
    }

    private static void displayResult(int totalTickets, int studentTickets, int standardTickets, int kidsTickets) {
        double percentStudentTickets =  (double) studentTickets  / totalTickets * 100;
        double percentStandardTickets = (double) standardTickets / totalTickets * 100;
        double percentKidsTickets =     (double) kidsTickets     / totalTickets * 100;

        out.printf("Total tickets: %d\n", totalTickets);
        out.printf("%.2f%% student tickets.\n", percentStudentTickets);
        out.printf("%.2f%% standard tickets.\n", percentStandardTickets);
        out.printf("%.2f%% kids tickets.\n", percentKidsTickets);
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

            String[] required = { "student", "standard", "kid", "End" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");

                for (String thing : required)
                    out.print(thing + " | ");

                out.println();
                return setValue(null, null);
            }
        }
        else {
            try {
                if (max instanceof Integer)
                    value = Integer.parseInt(scanner.nextLine());
                else if (max instanceof Double)
                    value = Double.parseDouble(scanner.nextLine());
                else {
                    out.println("Грешка! Не сте въвели правилен тип. Възможности [int, double]");
                    value = null;
                    exit(1);
                }
            } catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    out.println("Моля въведете положително число между");
                    return setValue(min, max);
                }
            }
        }

        return (T) value;
    }
}
