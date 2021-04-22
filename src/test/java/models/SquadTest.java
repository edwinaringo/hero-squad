package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SquadTest {

    @BeforeEach
    void setUp() throws Exception{
    }

    private Hero setupNewHero() {
        return new Hero("Superman", 24, "Flying", "Kryptonite", "Male");
    }
    private Squad setupNewSquad(Hero hero) {
        return new Squad("Avengers", "Save the World", hero);
    }
    private Squad setupNewSquad2(Hero hero) {
        return new Squad("Big men", "Beating bad folk", hero);
    }

    private Hero setupHero2() {
        return new Hero("Flash", 30, "Speed", "Slowness", "Male");
    }


    @Test
    void squadCanGetInstantiated() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad instanceof Squad);
    }

    @Test
    void testToGetSquadName() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad.getName() instanceof String);
    }

    @Test
    void testToGetSquadCause() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad.getCause() instanceof String);
    }

    @Test
    void testToAddHeroes() {
        Squad squad = setupNewSquad(setupNewHero());
        squad.addMembers(setupHero2());
        assertEquals(2, squad.getMembers().size());
    }

    @Test
    public void deleteHeroMember() {
        Hero hero1 = setupNewHero();
        Hero hero2 = setupHero2();
        Squad squad = setupNewSquad(hero1);
        squad.addMembers(hero2);
        squad.removeMember(hero1);
        assertFalse(squad.getMembers().contains(hero1));
    }

    @AfterEach
    void tearDown() throws Exception{
    }
}