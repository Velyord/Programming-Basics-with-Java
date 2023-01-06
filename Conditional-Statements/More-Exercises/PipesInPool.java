/*
Условие:
    Басейн с обем V има две тръби от които се пълни.
    Всяка тръба има определен дебит (литрите вода минаващи през една тръба за един час).
    Работникът пуска тръбите едновременно и излиза за N часа.
    Напишете програма, която изкарва състоянието на басейна, в момента, когато работникът се върне.
Вход:
    От конзолата се четат четири реда:
        • Първият ред съдържа числото V –
            Обем на басейна в литри – цяло число в интервала [1…10000].
        • Вторият ред съдържа числото P1 –
            дебит на първата тръба за час – цяло число в интервала [1…5000].
        • Третият ред съдържа числото P2 –
            дебит на втората тръба за час– цяло число в интервала [1…5000].
        • Четвъртият ред съдържа числото H –
            часовете които работникът отсъства – реално число в интервала [1.0…24.00]
Изход:
    Да се отпечата на конзолата едно от двете възможни състояния:
        • До колко се е запълнил басейна и коя тръба с колко процента е допринесла.
            ◦ "The pool is {запълненост на басейна в проценти}% full.
                Pipe 1: {процент вода от първата тръба}%. Pipe 2: {процент вода от втората тръба}%."
    Aко басейнът се е препълнил – с колко литра е прелял за даденото време.
            ◦ "For {часовете, които тръбите са пълнили вода} hours
                the pool overflows with {литрите вода в повече} liters."
Примерен вход и изход:
    1000
    100
    120
    3
    -> The pool is 66.00% full. Pipe 1: 45.45%. Pipe 2: 54.55%.
        За 3 часа:
        Първата тръба е напълнила – 300 л.
        Втората тръба е напълнила – 360 л.
        Общо – 660 л. < 1000 л. => 66% са запълнени
        Първата тръба е допринесла с 45% (300 от 660 л.).
        Втората тръба е допринесла с 54% (360 от 660 л.).
    100
    100
    100
    2.5
    -> For 2.50 hours the pool overflows with 400.00 liters.
        За 2.5 часа:
        Първата тръба е напълнила – 250 л.
        Втората тръба е напълнила – 250 л.
        Общо – 500 л. > 100 л. => 400 л. са прелели.
*/
package SoftUni.MoreExercises.ConditionalStatements;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class PipesInPool {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int V = Integer.parseInt(scanner.nextLine()); // setValue(1, 10000);
        int P1 = Integer.parseInt(scanner.nextLine());
        int P2 = Integer.parseInt(scanner.nextLine());
        double H = Double.parseDouble(scanner.nextLine());

        poolCondition(V, P1, P2, H);
    }

    private static void poolCondition(int v, int p1, int p2, double h) {
        double p1Filled = h * p1;
        double p2Filled = h * p2;
        double p1AndP2Filled = p1Filled + p2Filled;
        double percentFull = p1AndP2Filled / v * 100;
        double percentP1 = p1Filled / p1AndP2Filled * 100;
        double percentP2 = p2Filled / p1AndP2Filled * 100;
        double overflow = p1AndP2Filled - v;

        if (percentFull <= 100)
            out.printf(
                    "The pool is %.2f%% full. Pipe 1: %.2f%%. Pipe 2: %.2f%%.",
                    percentFull, percentP1, percentP2
            );
        else
            out.printf(
                    "For %.2f hours the pool overflows with %.2f liters.",
                    h, overflow
            );
    }
}
