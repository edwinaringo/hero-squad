package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SquadTest {

    @BeforeEach
    void setUp() {
    }

    private Hero setupNewHero() {
        return new Hero("Superman", 24, "Flying", "Kryptonite", "Male");
    }

    private Hero setupHero2() {
        return new Hero("Flash", 30, "Speed", "Slowness", "Male");
    }

    private Squad setupNewSquad() {
        return new Squad("Big men", "Conquer the world");
    }





    @Test
    void squadInstantiatesCorrectly() {
        Squad squad = setupNewSquad(setupNewHero());
        assertTrue(squad instanceof Squad);
    }

    @AfterEach
    void tearDown() {
    }
}