package com.example.etp.domain;

import org.junit.jupiter.api.Test;

import static com.example.etp.domain.Popularity.Very_Popular;
import static com.example.etp.domain.Region.Central_Boracay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BeachTest {

    private Beach underTest;

    @Test
    public void testConstructor() {
        //when
        Beach beach = new Beach(
                "Napcan Beach",
                "Lifes a beach, and then you die",
                "2 miles",
                "white",
                "white sand",
                new Island("PLWN", "Palawan"),
                Very_Popular,
                Central_Boracay);

        //then
        assertNotNull(beach);
    }

    @Test
    public void testGettersSetters() {
        //when
        underTest = beachBuilder();

        //then
        assertEquals(1, underTest.getId());
        assertEquals("Napcan beach", underTest.getTitle());
        assertEquals("I love this beach more than life itself", underTest.getDescription());
        assertEquals("2 miles", underTest.getLength());
        assertEquals("white", underTest.getSand());
        assertEquals("relaxing, fun", underTest.getKeywords());
        assertEquals(new Island("PLWN", "Palawan"), underTest.getIsland());
        assertEquals(Very_Popular, underTest.getPopularity());
        assertEquals(Central_Boracay, underTest.getRegion());
    }

    private Beach beachBuilder() {
        Beach beach = new Beach();
        beach.setId(1);
        beach.setTitle("Napcan beach");
        beach.setDescription("I love this beach more than life itself");
        beach.setLength("2 miles");
        beach.setSand("white");
        beach.setKeywords("relaxing, fun");
        beach.setIsland(new Island("PLWN", "Palawan"));
        beach.setPopularity(Very_Popular);
        beach.setRegion(Central_Boracay);
        return beach;
    }
}
