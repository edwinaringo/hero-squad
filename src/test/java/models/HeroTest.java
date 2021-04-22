package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() throws Exception{
    }

    @Test
    void heroInstantiatesCorrectly() {
        Hero newHero = setupNewHero();
        assertEquals(true,newHero instanceof Hero);
    }

    @Test
    void testGetHeroName() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getName() instanceof String);
    }

    private Hero setupNewHero() {
        return new Hero ("Superman",24,"Flying","Kryptonite", "Male");
    }

    private Hero setupHero2() {
        return new Hero ("Flash",30,"Speed","Slowness", "Male");
    }

    @Test
    void testGetHeroAge() {
        Hero newHero = setupNewHero();
        assertEquals(24, newHero.getAge());
    }

    @Test
    void testGetHeroSpecialPower() {
        Hero newHero = setupNewHero();
        assertTrue(newHero.getSpecialPower() instanceof String);
    }

    @Test
    void testGetHeroWeakness() {
        Hero newHero = setupNewHero();
        assertEquals("Kryptonite", newHero.getWeakness());
    }

    @Test
    void testGetHeroGender() {
        Hero newHero = setupNewHero();
        assertEquals("Male", newHero.getGender());
    }


    @Test
    void testAddingHero() {
        Hero newHero = setupNewHero();
        Hero anotherNewHero = new Hero("Flash",30,"Speed","Slowness", "male");
        assertTrue(Hero.getAllHeroes().contains(newHero));
        assertTrue(Hero.getAllHeroes().contains(anotherNewHero));
    }

    @Test
    void heroListToHaveTwoHeroes() {
        Hero newHero = setupNewHero();
        Hero anotherNewHero = new Hero("Hulk", 34, "Smashing", "Love", "Male");
        assertTrue(Hero.getAllHeroes().contains(newHero));
        assertTrue(Hero.getAllHeroes().contains(anotherNewHero));
    }

    @Test
    void searchHeroById() {
        Hero newHero = setupNewHero();
        Hero anotherNewHero = new Hero("Wonder woman", 23, "Strength", "Immortality", "Female");
        assertEquals("Superman", Hero.searchHero(1).getName());
    }

    @Test
    void testToDeleteHero() {
        Hero newHero = setupNewHero();
        Hero anotherNewHero = setupHero2();
        Hero thirdHero = new Hero("Avatar", 10, "Bending", "Emotions","Male");
        Hero.removeHero(anotherNewHero.getHeroID());
        assertFalse(Hero.getAllHeroes().contains(anotherNewHero));
        assertEquals(5, Hero.getAllHeroes().get(1).getHeroID());
    }

    @AfterEach
    void tearDown() throws Exception{
        Hero.clearAllHeroes();
    }
}