package P34_ExamPrep;

import java.util.*;

public class PIrates {
    static class Cities {

        String townName;
        int population;
        int gold;

        public Cities(String townName, int population, int gold) {
            this.townName = townName;
            this.population = population;
            this.gold = gold;
        }

        @Override
        public String toString() {
            //{town1} -> Population: {people} citizens, Gold: {gold} kg
            return townName + " -> Population: " + population + " citizens, Gold: " + gold + " kg";
        }

        public String getTownName() {
            return townName;
        }

        public void setTownName(String townName) {
            this.townName = townName;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Cities> citiesList = new LinkedHashMap<>();

        while (!line.equals("Sail")) {
            String[] citiesLine = line.split("\\|\\|");
            Cities town = new Cities(citiesLine[0], Integer.parseInt(citiesLine[1]), Integer.parseInt(citiesLine[2]));

            if (citiesList.containsKey(citiesLine[0])) {
                citiesList.get(citiesLine[0]).setGold(citiesList.get(citiesLine[0]).getGold() +
                        Integer.parseInt(citiesLine[2]));
                citiesList.get(citiesLine[0]).setPopulation(citiesList.get(citiesLine[0]).getPopulation() +
                        Integer.parseInt(citiesLine[1]));
            } else {
                citiesList.put(town.townName, town);
            }
            line = scanner.nextLine();
        }
        line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] command = line.split("=>");

            switch (command[0]) {
                case "Plunder":
                    plunder(citiesList, command[1], Integer.parseInt(command[2]), Integer.parseInt(command[3]));
                    break;
                case "Prosper":
                    prosper(citiesList, command[1], Integer.parseInt(command[2]));
                    break;

            }

            line = scanner.nextLine();
        }
        if (citiesList.isEmpty()) {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");

        } else {
            System.out.println("Ahoy, Captain! There are " + citiesList.size() + " wealthy settlements to go to:");
            citiesList.forEach((key, value) -> System.out.println(key + " -> Population: " + value.population +
                    " citizens, Gold: " + value.gold + " kg"));
        }

    }


    private static void prosper(Map<String, Cities> citiesList, String townName, int prosperGold) {
        if (prosperGold > 0) {
            citiesList.get(townName).setGold(citiesList.get(townName).getGold() + prosperGold);
            System.out.printf("%d gold added to the city treasury. %s now has %d gold.%n", prosperGold, townName, citiesList.get(townName).getGold());
        } else System.out.println("Gold added cannot be a negative number!");
    }

    private static void plunder(Map<String, Cities> citiesList, String plunderTown, int peopleKilled, int goldStolen) {
        int gold = citiesList.get(plunderTown).gold;
        int population = citiesList.get(plunderTown).population;
        if (gold <= goldStolen || population <= peopleKilled) {
            if (gold - goldStolen < 0) {
                goldStolen = gold;
            }
            if (population - peopleKilled < 0) {
                peopleKilled = population;
            }
            System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", plunderTown, goldStolen, peopleKilled);
            citiesList.remove(plunderTown);
            System.out.println(plunderTown + " has been wiped off the map!");
        } else {
            citiesList.get(plunderTown).setGold(gold - goldStolen);
            citiesList.get(plunderTown).setPopulation(population - peopleKilled);
            System.out.printf("%s plundered! %d gold stolen, %d citizens killed.%n", plunderTown, goldStolen, peopleKilled);
        }
    }
}
