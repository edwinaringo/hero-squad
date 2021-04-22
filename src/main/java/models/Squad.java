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
    public boolean getSquadFUll() {
        return isSquadFull;
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

    public void removeMember(Hero hero) {
        if (isSquadFull) {
            isSquadFull = false;
        }
        hero.updateSquad("");
        heroMembers.remove(hero);

        if (heroMembers.isEmpty()) {
            selfDelete();
        }
    }

    private void selfDelete(){
        Squad.squadList.remove(this);
    }

    public static Squad findSquad(int searchId) {
        return squadList.get(searchId - 1);
    }

    public List<Hero> getMembers() {
        return heroMembers;
    }

    public void changeHeroSquad(Hero hero, Squad newSquad) {
        if (heroMembers.size() >= 5) {
            isSquadFull = true;
        } else {
            Squad currentSquad = null;
            for (Squad squad : squadList) {
                if (hero.getSquadList().equalsIgnoreCase(squad.squadName)) {
                    currentSquad = squad;
                    break;
                }
            }
            for (Squad squad : squadList) {
                if (newSquad.squadName.equalsIgnoreCase(squad.squadName)) {
                    if (!hero.getSquadList().equalsIgnoreCase("")) {
                        currentSquad.heroMembers.remove(hero);
                        newSquad.heroMembers.add(hero);
                        hero.updateSquad(newSquad.squadName);
                        break;
                    } else {
                        newSquad.heroMembers.add(hero);
                        hero.setSquadAlliance(newSquad.squadName);
                    }
                } else {
                    System.out.println("Cannot find squad! :(");
                }
            }
        }
    }

}
