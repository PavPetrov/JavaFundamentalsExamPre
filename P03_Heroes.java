package P33_ExamPrep;

import java.util.*;

public class P03_Heroes {

    static class Hero {
        String name;
        int hitPoints;
        int manaPoints;

        public Hero(String name, int hitPoints, int manaPoints) {
            this.name = name;
            this.hitPoints = hitPoints;
            this.manaPoints = manaPoints;
        }

        public String getName() {
            return name;
        }

        public int getHitPoints() {
            return hitPoints;
        }

        public int getManaPoints() {
            return manaPoints;
        }

        public void setManaPoints(int manaPoints) {
            this.manaPoints = manaPoints;
        }

        public void setHitPoints(int hitPoints) {
            this.hitPoints = hitPoints;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Hero> heroes = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            Hero hero = new Hero(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            heroes.put(hero.getName(), hero);
        }
        String line = scanner.nextLine();

        while (!line.equals("End")) {
            String[] command = line.split(" - ");

            switch (command[0]) {
                case "CastSpell":
                    castSpell(command[1], Integer.parseInt(command[2]), command[3], heroes);
                    break;
                case "TakeDamage":
                    takeDamage(command[1], Integer.parseInt(command[2]), command[3], heroes);
                    break;
                case "Recharge":
                    recharge(command[1], Integer.parseInt(command[2]), heroes);
                    break;
                case "Heal":
                    heal(command[1], Integer.parseInt(command[2]), heroes);
                    break;
            }


            line = scanner.nextLine();
        }

        for (Hero hero : heroes.values()) {
            System.out.println(hero.getName() + System.lineSeparator()
                    + "  HP: " + hero.getHitPoints() + System.lineSeparator()
                    + "  MP: " + hero.getManaPoints());
        }
    }

    private static void heal(String heroName, int healAmount, Map<String, Hero> heroes) {
        Hero hero = heroes.get(heroName);
        if (hero.getHitPoints() + healAmount > 100) {
            System.out.printf("%s healed for %d HP!%n", hero.getName(), 100 - hero.getHitPoints());
            hero.setHitPoints(100);
        } else {
            hero.setHitPoints(hero.getHitPoints() + healAmount);
            System.out.printf("%s healed for %d HP!%n", hero.getName(), healAmount);
        }
    }

    private static void recharge(String heroName, int rechargeAmount, Map<String, Hero> heroes) {
        Hero hero = heroes.get(heroName);

        //  hero.setHitPoints(hero.getHitPoints() + rechargeAmount);
        if (hero.getManaPoints() + rechargeAmount > 200) {
            System.out.printf("%s recharged for %d MP!%n", hero.getName(), 200 - hero.getManaPoints());
            hero.setManaPoints(200);
        } else {
            hero.setManaPoints(hero.getManaPoints() + rechargeAmount);
            System.out.printf("%s recharged for %d MP!%n", hero.getName(), rechargeAmount);
        }
    }

    private static void castSpell(String heroName, int manaNeeded, String spellName, Map<String, Hero> heroes) {
        Hero hero = heroes.get(heroName);
        if (hero.getManaPoints() >= manaNeeded) {
            hero.setManaPoints(hero.getManaPoints() - manaNeeded);
            System.out.printf("%s has successfully cast %s and now has %d MP!%n", hero.getName(), spellName, hero.getManaPoints());
        } else {
            System.out.printf("%s does not have enough MP to cast %s!%n", hero.getName(), spellName);
        }

    }

    private static void takeDamage(String heroName, int damage, String attacker, Map<String, Hero> heroes) {
        Hero hero = heroes.get(heroName);
        hero.setHitPoints(hero.getHitPoints() - damage);
        if (hero.getHitPoints() > 0) {
            System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",
                    hero.getName(), damage, attacker, hero.getHitPoints());
        } else {
            System.out.printf("%s has been killed by %s!%n", hero.getName(), attacker);
            heroes.remove(hero.getName());
        }
    }
}
