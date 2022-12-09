/*
Условие:
    Напишете програма, която извежда на конзолата номерата на стаите в една сграда (в низходящ ред), като са изпълнени следните условия:
    •	На всеки четен етаж има само офиси
    •	На всеки нечетен етаж има само апартаменти
    •	Всеки апартамент се означава по следния начин : А{номер на етажа}{номер на апартамента}, номерата на апартаментите започват от 0.
    •	Всеки офис се означава по следния начин : О{номер на етажа}{номер на офиса}, номерата на офисите също започват от 0.
    •	На последният етаж винаги има апартаменти и те са по-големи от останалите, за това пред номера им пише 'L', вместо 'А'. Ако има само един етаж, то има само големи апартаменти!
    От конзолата се прочитат две цели числа - броят на етажите и броят на стаите за един етаж.
Примерен вход и изход:
    6
    4
    -> L60 L61 L62 L63
    -> A50 A51 A52 A53
    -> O40 O41 O42 O43
    -> A30 A31 A32 A33
    -> O20 O21 O22 O23
    -> A10 A11 A12 A13
        Имаме общо 6 етажа, с по 4 стаи на етаж.
        Нечетните етажи имат само апартаменти,
        а четните само офиси.
    9
    5
    -> L90 L91 L92 L93 L94
    -> O80 O81 O82 O83 O84
    -> A70 A71 A72 A73 A74
    -> O60 O61 O62 O63 O64
    -> A50 A51 A52 A53 A54
    -> O40 O41 O42 O43 O44
    -> A30 A31 A32 A33 A34
    -> O20 O21 O22 O23 O24
    -> A10 A11 A12 A13 A14
    4
    4
    -> L40 L41 L42 L43
    -> A30 A31 A32 A33
    -> O20 O21 O22 O23
    -> A10 A11 A12 A13
 */
package SoftUni.Lab13;

import static java.lang.System.exit;
import static java.lang.System.out;
import static java.lang.System.in;

import java.util.Scanner;
import java.util.List;

public class Building {
    static Scanner scanner = new Scanner(in);

    public static void main(String[] args) {
        int floors = setValue(0, Integer.MAX_VALUE);
        int roomsOnFloor = setValue(0, Integer.MAX_VALUE);

        displayBuilding(floors, roomsOnFloor);
    }

    private static void displayBuilding(int floors, int roomsOnFloor) {
        char type;

        for (int floor = floors; floor > 0; floor--) {
            if (floor == floors)     type = 'L';
            else if (floor % 2 == 0) type = 'O';
            else                     type = 'A';              

            for (int room = 0; room < roomsOnFloor; room++)
                out.printf("%c%d%d ", type, floor, room);
            
            out.print("\n");
        }
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
            /*
            String[] required = { "" };
            List<String> requiredList = List.of(required);

            if (!requiredList.contains(value)){
                out.print("Моля въведете един от следните избори: | ");

                for (String thing : required)
                    out.print(thing + " | ");

                out.println();
                return setValue(null, null);
            } */
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
