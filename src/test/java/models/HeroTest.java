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
        return new Hero ("Superman",24,"Flying","Kryptonite", "Male");
    }

    @Test
    void testGetHeroName() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getName() instanceof String);
    }

    @Test
    void testGetHeroAge() {
        Hero newHero = setupNewHero();
        assertEquals(24, newHero.getAge());
    }

    @Test
    void testGetHeroSpecialPower() {
        Hero newHero = setupNewHero();
        assertEquals("Flying", newHero.getSpecialPower());
    }

    @Test
    void testGetHeroWeakness() {
        Hero newHero = setupNewHero();
        assertEquals("Kryptonite", newHero.getWeakness());
    }

    @Test
    void testGetHeroGender() {
        Hero newHero = setupNewHero();
        assertEquals("male", newHero.getGender());
    }

    @AfterEach
    void tearDown() {
    }
}