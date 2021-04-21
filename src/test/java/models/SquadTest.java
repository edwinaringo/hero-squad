package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SquadTest {

    @BeforeEach
    void setUp() {
    }

    private Hero setupNewHero() {
        return new Hero("Superman", 24, "Flying", "Kryptonite", "Male");
    }
    private Squad setupNewSquad(Hero hero) {
        return new Squad("Avengers", "Save the World", hero);
    }

    private Hero setupHero2(Hero hero) {
        return new Hero("Flash", 30, "Speed", "Slowness", "Male");
    }


    @Test
    void squadCanGetInstantiated() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad instanceof Squad);
    }

    @AfterEach
    void tearDown() {
    }
}