/*
Условие:
    Напишете програма, която чете текст от конзолата (стринг) и го принтира, докато не получи командата "Stop".
Примерен вход и изход:
    Nakov
    SoftUni
    Sofia
    Bulgaria
    SomeText
    Stop
    AfterStop
    Europe
    HelloWorld
    -> Nakov
    -> SoftUni
    -> Sofia
    -> Bulgaria
    -> SomeText
    Sofia
    Berlin
    Moscow
    Athens
    Madrid
    London
    Paris
    Stop
    AfterStop
    -> Sofia
    -> Berlin
    -> Moscow
    -> Athens
    -> Madrid
    -> London
    -> Paris
*/
package SoftUni.Lab11;

import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;

public class ReadText {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String input = scanner.nextLine();

        while (!input.equals("Stop")) {
            out.println(input);
            input = scanner.nextLine();
        }
    }
}
