/*
Условие:
    Напишете програма, която да принтира на конзолата всички комбинации от 3 букви в зададен интервал,
    като се пропускат комбинациите съдържащи зададена от конзолата буква.
    Накрая трябва да се изпринтира броят на отпечатаните комбинации.
Вход:
    Входът се чете от конзолата и съдържа точно 3 реда:
        Ред 1. Малка буква от английската азбука за начало на интервала – от ‘a’ до ‚z’.
        Ред 2. Малка буква от английската азбука за край на интервала  – от първата буква до ‚z’.
        Ред 3. Малка буква от английската азбука – от ‘a’ до ‚z’ –
        като комбинациите съдържащи тази буквата се пропускат.
Изход:
    Да се отпечатат на един ред всички комбинации отговарящи на условието
    плюс броят им разделени с интервал.
Примерен вход и изход:
    a
    c
    b
    ->
    aaa aac aca acc caa cac cca ccc 8
        Всички възможни комбинации с буквите a, b, и c са:
        aaa aab aac aba abb abc aca acb acc baa bab bac bba bbb bbc bca bcb bcc caa cab cac cba cbb cbc
        cca ccb ccc
        Комбинациите съдържащи b не са валидни.
        Остават 8 валидни комбинации
    f
    k
    h
    ->
    fff ffg ffi ffj ffk fgf fgg fgi fgj fgk fif fig fii fij fik fjf fjg fji fjj fjk fkf fkg fki fkj fkk
    gff gfg gfi gfj gfk ggf ggg ggi ggj ggk gif gig gii gij gik gjf gjg gji gjj gjk gkf gkg gki gkj gkk
    iff ifg ifi ifj ifk igf igg igi igj igk iif iig iii iij iik ijf ijg iji ijj ijk ikf ikg iki ikj ikk
    jff jfg jfi jfj jfk jgf jgg jgi jgj jgk jif jig jii jij jik jjf jjg jji jjj jjk jkf jkg jki jkj jkk
    kff kfg kfi kfj kfk kgf kgg kgi kgj kgk kif kig kii kij kik kjf kjg kji kjj kjk kkf kkg kki kkj kkk
    125
    a
    c
    z
    ->
    aaa aab aac aba abb abc aca acb acc baa bab bac bba bbb bbc bca bcb bcc caa cab cac cba cbb cbc cca
    ccb ccc 27
*/
package SoftUni.MoreExercises.NestedLoops;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class LettersCombinations {
    static int smallestInt = Integer.MIN_VALUE;
    static int biggestInt = Integer.MAX_VALUE;
    static double smallestDouble = -1 * Double.MAX_VALUE;
    static double biggestDouble = Double.MAX_VALUE;
    static int stringCount = 0;
    static boolean requiredString = false;

    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        String input;

        input = setValue(null, null);
        char startLetter = input.toLowerCase().charAt(0);
        input = setValue(null, null);
        char endLetter = input.toLowerCase().charAt(0);
        input = setValue(null, null);
        char failLetter = input.toLowerCase().charAt(0);
        
        int counter = 0;

        for (int i=startLetter; i<=endLetter; i++)
            for (int j=startLetter; j<=endLetter; j++)
                for (int k=startLetter; k<=endLetter; k++)
                    if (i != failLetter && j != failLetter && k != failLetter) {
                        counter++;
                        out.print("" + (char) i + (char) j + (char) k + " ");
                    }
        out.print(counter);
                               
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

            if (requiredString){
                stringCount++;
                String[] required = {};

                if (stringCount == 1)
                    required = new String[] {"Spring", "Summer", "Autumn", "Winter"};
                if (stringCount == 2)
                    required = new String[] {"Y", "N"};
                if (stringCount > 2) {
                    requiredString = false;
                    return (T) value;
                }

                List<String> requiredList = List.of(required);

                if (!requiredList.contains(value)){
                    out.print("Моля въведете един от следните избори: \n| ");
                    for (String thing : required)
                        out.print(thing + " | ");
                    out.println();

                    stringCount--;
                    return setValue(null, null);
                }
            }
        }
        else {
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
            }
            catch (Exception e) {
                out.println("Не сте въвели число. Пробвайте пак!");
                return setValue(min, max);
            }

            if (max instanceof Integer) {
                if ((int) value < (int) min || (int) value > (int) max) {
                    if ((int) min == 0 && (int) max == biggestInt)
                        out.println("Моля въведете положително число:");
                    else
                        out.printf("Моля въведете число между %s и %s:\n", min, max);

                    return setValue(min, max);
                }
            }
            if (max instanceof Double) {
                if ((double) value < (double) min || (double) value > (double) max) {
                    if ((double) min == 0 && (double) max == biggestDouble)
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
