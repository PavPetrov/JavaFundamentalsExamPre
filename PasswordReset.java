package P34_ExamPrep;

import java.util.Scanner;

public class PasswordReset {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder message = new StringBuilder(scanner.nextLine());
        String line = scanner.nextLine();
        while (!line.equals("Reveal")) {
            String[] command = line.split(":\\|:");
            switch (command[0]) {
                case "InsertSpace":
                    message.insert(Integer.parseInt(command[1]), " ");
                    System.out.println(message);
                    break;
                case "Reverse":
                    String substring = command[1];
                    if (message.toString().contains(substring)) {
                        int startIndex = message.toString().indexOf(substring);
                        message.replace(startIndex, startIndex + substring.length(), "");
                        String reversedSubstring = new StringBuilder(substring).reverse().toString();
                        message.append(reversedSubstring);
                        System.out.println(message);
                    } else
                        System.out.println("error");
                    break;
                case "ChangeAll":
                    String replace = message.toString().replace(command[1], command[2]);
                    message.setLength(0);
                    message.append(replace);
                    System.out.println(message);
                    break;
            }

            line = scanner.nextLine();
        }
        System.out.println("You have a new text message: " + message);

    }
}
//•	"Reverse:|:{substring}":
//o	If the message contains the given substring, cut it out, reverse it and add it at the end of the message.
//o	If not, print "error".
//o	This operation should replace only the first occurrence of the given substring if there are two or more occurrences.