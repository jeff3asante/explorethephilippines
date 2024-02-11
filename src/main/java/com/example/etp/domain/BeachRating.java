package com.example.etp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rating of a Beach by a User
 * <p>
 * Created by Jeffrey Asante
 */

@Entity
@Data
@NoArgsConstructor
public class BeachRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(insertable = false, updatable = false, nullable = false)
    private Integer userId;

    @ManyToOne
    private Beach beach;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    /**
     * Create a fully initialized BeachRating.
     *
     * @param beach     The beach being rated
     * @param userId The user Identifier
     * @param score     Integer score (1-5)
     * @param comment   Optional comment from the user
     */
    public BeachRating(Beach beach, Integer userId, Integer score, String comment) {
        this.beach = beach;
        this.userId = userId;
        this.score = score;
        this.comment = comment;
    }
}
