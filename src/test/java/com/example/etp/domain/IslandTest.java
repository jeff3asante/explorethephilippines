package com.example.etp.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IslandTest {

    private Island underTest;

    @Test
    public void testConstructor() {
        //when
        underTest = new Island("PLWN", "Palawan");

        //then
        assertEquals("PLWN", underTest.getCode());
        assertEquals("Palawan", underTest.getName());
    }


}
