/*
Условие:
    Григор Димитров е тенисист, чиято следваща цел е изкачването в световната ранглиста по тенис за мъже.
    През годината Гришо участва в определен брой турнири, като за всеки турнир получава точки, които зависят от
    позицията, на която е завършил в турнира. Има три варианта за завършване на турнир:
    	W - ако е победител получава 2000 точки
    	F - ако е финалист получава 1200 точки
    	SF - ако е полуфиналист получава 720 точки
    Напишете програма, която изчислява колко ще са точките на Григор след изиграване на всички турнири, като знаете с
    колко точки стартира сезона. Също изчислете колко точки средно печели от всички изиграни турнири и колко процента от
    турнирите е спечелил.
Вход:
    От конзолата първо се четат два реда:
    •	Брой турнири, в които е участвал – цяло число в интервала [1…20]
    •	Начален брой точки в ранглистата - цяло число в интервала [1...4000]
    За всеки турнир се прочита отделен ред:
    •	Достигнат етап от турнира – текст – "W", "F" или "SF"
Изход:
    Отпечатват се три реда в следния формат:
    •	"Final points: {брой точки след изиграните турнири}"
    •	"Average points: {средно колко точки печели за турнир}"
    •	"{процент спечелени турнири}%"
    Средните точки да бъдат закръглени към най-близкото цяло число надолу, а процентът да се форматира до втората цифра
    след десетичния знак.
Примерен вход и изход:
5
1400
F
SF
W
W
SF
-> Final points: 8040
-> Average points: 1328 40.00%
    5 турнира и начален брой точки: 1400
        1-ви турнир -> финал (F) -> точки = 1400 + 1200 = 2600
        2-ри турнир -> полуфинал (SF) -> точки = 2600 + 720 = 3320
        3-ти турнир -> победител (W) -> точки = 3320 + 2000 = 5320
        4-ти турнир -> победител (W) -> точки = 5320 + 2000 = 7320
        5-ти турнир -> полуфинал (SF) -> точки = 7320 + 720 = 8040
    Точки след изиграване на турнирите: 8040
    Средно спечелени точки за турнир: (1200 + 720 + 2000 + 2000 + 720) / 5 = 6640 / 5 = 1328
    Брой спечелени турнири: 2
    Процент спечелени турнири: (2 / 5) * 100 =  40 %
4
750
SF
W
SF
W
-> Final points: 6190
-> Average points: 1360 50.00%
7
1200
SF
F
W
F
W
SF
W
-> Final points: 11040
-> Average points: 1405 42.86%
 */
package SoftUni.Exer10;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class TennisRanklist {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int tournamentCount = setValue(1, 20);
        int startingPoints = setValue(1, 4000);

        result(tournamentCount, startingPoints);
    }

    private static void result(int tournamentCount, int startingPoints) {
        int positionPoints = 0;
        int wonTournaments = 0;
        
        for (int i=0; i<tournamentCount; i++) {
            String position = setValue(null, null);
            
            switch (position) {
                case "W":  
                    positionPoints += 2000; 
                    wonTournaments += 1;
                    break;
                case "F":  
                    positionPoints += 1200; 
                    break;
                case "SF": 
                    positionPoints += 720; 
                    break;
            }
        }
        
        displayResult(startingPoints, positionPoints, tournamentCount, wonTournaments);
    }

    private static void displayResult(int startingPoints, int positionPoints, int tournamentCount, int wonTournaments) {
        int allPoints = positionPoints + startingPoints;
        int averagePoints = positionPoints / tournamentCount;
        double percentWonTournaments = (double) wonTournaments / tournamentCount * 100;

        out.printf("Final points: %d\n", allPoints);
        out.printf("Average points: %d\n", averagePoints);
        out.printf("%.2f%%", percentWonTournaments);
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

            String[] required = { "W", "F", "SF" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");
                for (String thing : required)
                    out.print(thing + " | ");

                return setValue(null, null);
            }
        } else {
            try {
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
                    if ((int) min == 0 && (int) max == Double.MAX_VALUE)
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
