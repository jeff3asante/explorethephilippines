package com.example.etp.repository;

import com.example.etp.domain.BeachRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

/**
 * Beach Rating Repository Interface
 * <p>
 * Created by Jeffrey Asante
 */
@RepositoryRestResource(exported = false)
public interface BeachRatingRepository extends CrudRepository<BeachRating, Integer> {

    /**
     * Lookup all the BeachRatings for a beach.
     *
     * @param beachId is the beach Identifier
     * @return a List of any found BeachRatings
     */
    List<BeachRating> findByBeachId(Integer beachId);

    /**
     * Lookup a BeachRating by the BeachId and User Id
     *
     * @param beachId   beach identifier
     * @param userId user identifier
     * @return Optional of found BeachRatings.
     */
    Optional<BeachRating> findByBeachIdAndUserId(Integer beachId, Integer userId);
}
