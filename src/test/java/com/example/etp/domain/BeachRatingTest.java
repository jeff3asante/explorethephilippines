package com.example.etp.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BeachRatingTest {

    private BeachRating underTest;

    @Test
    public void testConstructor() {
        //when
        underTest = new BeachRating(new Beach(), 1, 9, "yes");

        //then
        assertNotNull(underTest);
    }

    @Test
    public void testGettersSetters() {
        //given
        underTest = new BeachRating();
        Beach beach = new Beach();

        //when
        underTest.setBeach(beach);
        underTest.setUserId(1);
        underTest.setScore(5);
        underTest.setComment("Best beach I have ever seen in my entire life");

        //then
        assertEquals(beach, underTest.getBeach());
        assertEquals(1, underTest.getUserId());
        assertEquals(5, underTest.getScore());
        assertEquals("Best beach I have ever seen in my entire life", underTest.getComment());
    }
}
