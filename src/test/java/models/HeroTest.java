package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void heroInstantiatesCorrectly() {
        Hero newHero = setupNewHero();
        assertEquals(true,newHero instanceof Hero);
    }

    private Hero setupNewHero() {
        return new Hero ("Superman",24,"Flying","Kryptonite");
    }

    @Test
    void getHeroName() {
        Hero newHero = setupNewHero();
        assertEquals(newHero.getName() instanceof String);
    }

    @AfterEach
    void tearDown() {
    }
}