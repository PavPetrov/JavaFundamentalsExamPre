package P34_ExamPrep;

import java.util.*;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Plants> plantsList = new ArrayList<>();
        Map<String, Integer> plantsRarity = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split("<->");
            String plantName = line[0];
            int rarity = Integer.parseInt(line[1]);
            plantsRarity.put(plantName, rarity);
        }

        for (Map.Entry<String, Integer> p : plantsRarity.entrySet()) {
            List<Integer> rating = new ArrayList<>(List.of(0));
            //   List<Integer> rating = new ArrayList<>();
            Plants plant = new Plants(p.getKey(), p.getValue(), rating);
            plantsList.add(plant);
        }
        
        String commandline = scanner.nextLine();
        while (!commandline.equals("Exhibition")) {
            String[] commands = commandline.split("\\s\\-?\\s?");
            String plantName = commands[1];
            boolean isValidName = false;
            switch (commands[0]) {
                case "Rate:":
                    for (Plants plants : plantsList) {
                        if (plants.getName().equals(plantName)) {
                            isValidName = true;
                            if (plants.getRatings().get(0) == 0) {
                                plants.getRatings().set(0, Integer.parseInt(commands[2]));
                            } else {
                                plants.getRatings().add(Integer.parseInt(commands[2]));
                            }
                        }
                    }
                    break;
                case "Update:":
                    for (Plants plants : plantsList) {
                        if (plants.getName().equals(plantName)) {
                            isValidName = true;
                            plants.setRarity(Integer.parseInt(commands[2]));
                        }
                    }

                    break;
                case "Reset:":
                    for (Plants plants : plantsList) {
                        if (plants.getName().equals(plantName)) {
                            isValidName = true;
                            plants.getRatings().clear();
                            plants.getRatings().add(0);
                        }
                    }
                    break;
                default:
                    System.out.println("error");
            }
            if (!isValidName){
                System.out.println("error");
            }

            commandline = scanner.nextLine();
        }
        //Printing Data
        System.out.println("Plants for the exhibition:");
        for (Plants plants : plantsList) {
            Double rating = plants.getRatings().stream().mapToDouble(e -> e).average().getAsDouble();
            System.out.printf("- %s; Rarity: %d; Rating: %.2f%n", plants.getName(), plants.getRarity(), rating);
        }
    }

    static class Plants {
        String name;
        int rarity;
        List<Integer> ratings = new ArrayList<>();

        public Plants(String name, int rarity, List<Integer> ratings) {
            this.name = name;
            this.rarity = rarity;
            this.ratings = ratings;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRarity() {
            return rarity;
        }

        public void setRarity(int rarity) {
            this.rarity = rarity;
        }

        public List<Integer> getRatings() {
            return ratings;
        }

        public void setRatings(List<Integer> ratings) {
            this.ratings = ratings;
        }
    }

}
