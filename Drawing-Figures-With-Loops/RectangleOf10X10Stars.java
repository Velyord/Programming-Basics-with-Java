/*
Условие:
    Напишете програма, която чертае на конзолата правоъгълник от 10 x 10 звездички.
Примерен вход и изход:
    (няма) ->
    **********
    **********
    **********
    **********
    **********
    **********
    **********
    **********
    **********
    **********
*/
package SoftUni.MoreExercises.DrawingFiguresWithLoops;

import static java.lang.System.out;

public class RectangleOf10X10Stars {

    public static void main(String[] args) {
        for (int row=1; row<=10; row++) {
            for (int star=1; star<=10; star++)
                out.print("*");
            out.println();
        }            
    }
}
