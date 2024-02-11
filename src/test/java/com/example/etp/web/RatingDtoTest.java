package com.example.etp.web;

import com.example.etp.domain.Beach;
import com.example.etp.domain.BeachRating;
import com.example.etp.domain.Island;
import org.junit.jupiter.api.Test;

import static com.example.etp.domain.Popularity.Very_Popular;
import static com.example.etp.domain.Region.South_Boracay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RatingDtoTest {

    private RatingDto underTest;

    @Test
    public void testContructor() {
        //given
        Beach beach = beachBuilder();
        BeachRating beachRating = new BeachRating(beach, 10, 10, "comment");

        //when
        underTest = new RatingDto(beachRating);

        //then
        assertNotNull(underTest);
    }

    @Test
    public void testGettersSetters() {
        //given
        RatingDto ratingDto = new RatingDto();

        //when
        ratingDto.setScore(10);
        ratingDto.setComment("Incroyable");
        ratingDto.setUserId(3);

        //then
        assertEquals(10, ratingDto.getScore());
        assertEquals("Incroyable", ratingDto.getComment());
        assertEquals(3, ratingDto.getUserId());
    }

    private Beach beachBuilder() {
        return new Beach("Nacpan Beach",
                "Nacpan Beach is one of the most beautiful stretches of sand in the Philippines. With four kilometres of soft cream-coloured sand, swaying coconut palms.",
                "4km",
                "Soft cream-coulerd",
                "Soft Sand, Beach Hostel, Palawan, Nacpan",
                new Island("PLWN", "Palawan"),
                Very_Popular,
                South_Boracay);

    }

}
