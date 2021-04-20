package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {
    private String squadName;
    private String squadCause;
    private List<Object> heroMembers = new ArrayList<>();
    private static boolean isRegisteredHEro = false;
    private static List<Squad> squadList = new ArrayList<>();
    private int squadId;

}
