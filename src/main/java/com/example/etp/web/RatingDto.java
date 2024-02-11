package com.example.etp.web;

import com.example.etp.domain.BeachRating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for Rating a Beach.
 * <p>
 * Created by Jeffrey Asante
 */
public class RatingDto {
    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    @NotNull
    private Integer userId;

    /**
     * Construct a RatingDto from a fully instantiated BeachRating.
     *
     * @param beachRating Beach Rating Object
     */
    public RatingDto(BeachRating beachRating) {
        this(beachRating.getScore(), beachRating.getComment(), beachRating.getUserId());
    }

    /**
     * Constructor to fully initialize the RatingDto
     *
     * @param score     score 1-5
     * @param comment   comment
     * @param userId user identifier
     */
    public RatingDto(Integer score, String comment, Integer userId) {
        this.score = score;
        this.comment = comment;
        this.userId = userId;
    }

    protected RatingDto() {
    }

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
