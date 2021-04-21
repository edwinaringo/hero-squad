package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String squadName;
    private Hero newHero;
    private String cause;
    private List<Object> heroMembers = new ArrayList<>();
    private static boolean isRegisteredHEro = false;
    private static List<Squad> squadList = new ArrayList<>();
    private int squadId;

    public Squad(String name, String cause) {
        this.squadName = name;
        this.cause = cause;
    }

    public Hero getHero() {
        return newHero;
    }

    public String getName() {
        return squadName;
    }


    public int getSquadId() {
        return squadId;
    }

}
