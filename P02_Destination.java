package P33_ExamPrep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P02_Destination {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("([=\\/])(?<destination>[A-Z][A-Za-z]{2,})\\1");
        Matcher matcher = pattern.matcher(line);
        int travelPoints = 0;
        List<String> destinations = new ArrayList<>();
        while (matcher.find()) {
            String destination = matcher.group("destination");
            destinations.add(destination);
            travelPoints += destination.length();
        }
        System.out.println("Destinations: " + String.join(", ", destinations));
        System.out.println("Travel Points: " + travelPoints);
        //Destinations: Hawai, Cyprus
        //Travel Points: 11
    }
}
