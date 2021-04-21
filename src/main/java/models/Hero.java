package models;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private int age;
    private String specialPower;
    private String weakness;
    private String gender;
    private String squadList;
    private static List<Hero> allHeroes = new ArrayList<>();
    private int heroID;

    public Hero(String name, int age, String specialPower, String weakness, String gender) {
        this.name = name;
        this.age = age;
        this.specialPower = specialPower;
        this.weakness = weakness;
        this.gender = gender;
        this.allHeroes.add(this);
        this.heroID = allHeroes.size();
    }

    public void setSpecialPower(String specialPower) {
        this.specialPower = specialPower;
    }
    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }
    public static List<Hero> getAllHeroes() {
        return allHeroes;
    }
    public void setSquadAlliance(String squadList) {
        this.squadList = squadList;
    }

    public static void clearHeroList(){
        allHeroes.clear();
    }

    public static Hero searchHero(int searchID) {
        return allHeroes.get(searchID - 1);
    }

    public int getHeroID() {
        return heroID;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getSpecialPower() {
        return specialPower;
    }
    public String getWeakness() {
        return weakness;
    }
    public String getGender() {
        return gender;
    }

    public String getSquadList() {
        return squadList;
    }
}

