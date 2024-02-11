package com.example.etp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Beach contains all attributes of a beach in the Philippines.
 * <p>
 * Created by Jeffrey Asante
 */
@Entity
@Data
@NoArgsConstructor
public class Beach {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String length;

    @Column
    private String sand;

    @Column
    private String keywords;

    @ManyToOne
    private Island island;

    @Column
    @Enumerated
    private Popularity popularity;

    @Column
    @Enumerated
    private Region region;

    public Beach(String title, String description, String length, String sand, String keywords, Island island, Popularity popularity, Region region) {
        this.title = title;
        this.description = description;
        this.length = length;
        this.sand = sand;
        this.keywords = keywords;
        this.island = island;
        this.popularity = popularity;
        this.region = region;
    }
}
