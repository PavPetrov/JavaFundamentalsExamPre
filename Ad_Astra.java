package P34_ExamPrep;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ad_Astra {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        ;
        Pattern pattern = Pattern.compile("([#\\|])(?<item>[A-Za-z\\s]+)\\1(?<date>\\d{2}\\/\\d{2}\\/\\d{2})\\1(?<calories>\\d{1,5})\\1");
        Matcher matcher = pattern.matcher(line);
        List<Food> decryptedFoods = new ArrayList<>();
        while (matcher.find()) {
            String itemName = matcher.group("item");
            String date = matcher.group("date");
            int calories = Integer.parseInt(matcher.group("calories"));
            Food food = new Food(itemName, date, calories);
            decryptedFoods.add(food);
        }

        int days = decryptedFoods.stream().mapToInt(f -> f.calories).sum() / 2000;
        System.out.println("You have food to last you for: " + days + " days!");
        for (Food food : decryptedFoods) {
            System.out.println(food.toString());
        }


    }

    static class Food {
        String item;
        String date;
        int calories;

        public Food(String item, String date, int calories) {
            this.item = item;
            this.date = date;
            this.calories = calories;
        }

        @Override
        public String toString() {
            return "Item: " + item + ", " + "Best before: " + date + ", " + "Nutrition: " + calories;
        }
    }
}
