package P33_ExamPrep;

import java.util.Scanner;

public class P01_InitialGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        String line = scanner.nextLine();

        while (!line.equals("Decode")) {
            String[] command = line.split("\\|");
            switch (command[0]) {
                case "Move":
                    message = move(message, Integer.parseInt(command[1]));
                    break;
                case "Insert":
                    message = insert(message, Integer.parseInt(command[1]), command[2]);
                    break;
                case "ChangeAll":
                    message = changeAll(message, command[1], command[2]);
                    break;
            }


            line = scanner.nextLine();
        }
        System.out.println("The decrypted message is: " + message);
    }
    //•	"Move {number of letters}":
    //o	Moves the first n letters to the back of the string
    //•	"Insert {index} {value}":
    //o	Inserts the given value before the given index in the string
    //•	"ChangeAll {substring} {replacement}":
    //o	Changes all occurrences of the given substring with the replacement text

    private static String changeAll(String message, String substring, String replacement) {

        return message.replace(substring, replacement);
    }

    private static String insert(String message, int index, String s) {
        return message.substring(0, index) + s + message.substring(index);

    }

    private static String move(String message, int number) {
        String firstPart = message.substring(0, number);
        String secondPart = message.substring(number);


        return  secondPart + firstPart;
    }
}
