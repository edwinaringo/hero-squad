package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private Hero newHero;
    private String squadName;
    private String cause;
    private List<Hero> heroMembers = new ArrayList<>();
    private static boolean isAddedHero = false;
    private boolean isSquadFull =false;
    private static List<Squad> squadList = new ArrayList<>();
    private int squadId;

    public Squad(String name, String cause, Hero hero) {
        this.squadName = name.trim();
        this.cause = cause.trim();
        this.newHero =  hero;
        crossCheckHero(hero.getHeroID());

        if(isAddedHero) {
            hero.setSquadAlliance(squadName);
            heroMembers.add(newHero);
            squadList.add(this);
            this.squadId = squadList.size();
        }else{
            System.out.println("Hero not found.");
        }
    }

    public String getName() {
        return squadName;
    }

    public int getSquadId() {
        return squadId;
    }

    public String getCause() {
        return cause;
    }

    public static void crossCheckHero(int idToCheck) {
        for (Hero hero : Hero.getAllHeroes()) {
            if (hero.getHeroID() == idToCheck) {
                isAddedHero = true;
                break;
            }
        }
    }


    public void clearMemberLists() {
        heroMembers.clear();
    }

    public static List<Squad> getAllSquads() {
        return squadList;
    }

    public static void clearSquadList() {
        squadList.clear();
    }

    public void addMembers(Hero hero) {
        if (heroMembers.size() >= 5) {
            isSquadFull = true;
        } else {
            heroMembers.add(hero);
        }
    }

    public List<Hero> getMembers() {
        return heroMembers;
    }
}
