package com.example.etp.service;

import com.example.etp.domain.Beach;
import com.example.etp.domain.BeachRating;
import com.example.etp.repository.BeachRatingRepository;
import com.example.etp.repository.BeachRepository;
import com.example.etp.web.RatingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BeachRatingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeachRatingService.class);
    private final BeachRatingRepository beachRatingRepository;
    private final BeachRepository beachRepository;

    /**
     * Construct BeachRatingService
     *
     * @param beachRatingRepository Beach Rating Repository
     * @param beachRepository Beach Repository
     */
    @Autowired
    public BeachRatingService(BeachRatingRepository beachRatingRepository, BeachRepository beachRepository) {
        this.beachRatingRepository = beachRatingRepository;
        this.beachRepository = beachRepository;
    }

    /**
     * Create a new Beach Rating in the database
     *
     * @param beachId beach identifier
     * @param ratingDto Rating Data Transfer Object
     * @throws NoSuchElementException if no Beach found.
     */
    public void createNewBeachRating(Integer beachId, RatingDto ratingDto) throws NoSuchElementException {
        LOGGER.info("Create Rating for beach {} from user with vistorId {}", beachId, ratingDto.getUserId());
        Beach beach = verifyBeach(beachId);
        Integer userId = ratingDto.getUserId();
        Integer score = ratingDto.getScore();
        String comment = ratingDto.getComment();
        beachRatingRepository.save(new BeachRating(beach, userId, score, comment));
    }

    /**
     * Get All Ratings.
     *
     * @return List of BeachRatings
     */
    public List<RatingDto> getAllRatingsForBeach(Integer beachId) {
        LOGGER.info("Get all Ratings for the beach with beachId of {}", beachId);
        verifyBeach(beachId);
        return beachRatingRepository.findByBeachId(beachId).stream()
                .map(RatingDto::new).collect(Collectors.toList());
    }

    /**
     * Get The Average Rating of a Beach.
     *
     * @param beachId beach identifier
     * @return Average Rating
     */
    //Map.of doesn't seem to work with java 8. Only 9 and above
    public Map<String, Double> getAverageRating(Integer beachId) {
        LOGGER.info("Get average rating of beach with teh beachId of {}", beachId);
        verifyBeach(beachId);
        Map<String, Double> averageMap = new HashMap<>();
        averageMap.put("averageBeachRating", beachRatingRepository.findByBeachId(beachId).stream()
                .mapToInt(BeachRating::getScore).average()
                .orElseThrow(() -> new NoSuchElementException("Beach has no Ratings")));
        return averageMap;
    }

    /**
     * Update score and comment of a Beach Rating
     *
     * @param beachId   beach identifier
     * @param ratingDto rating Data Transfer Object
     * @return The modified Rating DTO.
     */
    public RatingDto updateWithPut(Integer beachId, RatingDto ratingDto) {
        Integer userId = ratingDto.getUserId();
        Integer score = ratingDto.getScore();
        String comment = ratingDto.getComment();

        BeachRating rating = verifyBeachRating(beachId, userId);
        rating.setScore(score);
        rating.setComment(comment);
        LOGGER.info("Update score and comment of the beach with the beachId of {} " +
                "left by the user with the userId of {}", beachId, userId);
        return new RatingDto(beachRatingRepository.save(rating));
    }

    /**
     * Delete a Rating of a beach made by a user
     *
     * @param beachId   beach identifier
     * @param userId user identifier
     */
    public void delete(Integer beachId, Integer userId) {
        LOGGER.info("Delete Rating for beach with the beachId of {} and the userId of {}", beachId, userId);
        BeachRating rating = verifyBeachRating(beachId, userId);
        beachRatingRepository.delete(rating);
    }

    /**
     * Verify and return the Beach given a beachId.
     *
     * @param beachId beach identifier
     * @return the found Beach
     * @throws NoSuchElementException if no Beach found.
     */
    private Beach verifyBeach(Integer beachId) throws NoSuchElementException {
        return beachRepository.findById(beachId).orElseThrow(() ->
                new NoSuchElementException("Beach does not exsist, at least not in the Philippenes" + beachId));
    }

    /**
     * Verify and return the BeachRating for a particular beachId and User
     *
     * @param beachId   beach identifier
     * @param userId user identifier
     * @return the found BeachRating
     * @throws NoSuchElementException if no BeachRating found
     */
    private BeachRating verifyBeachRating(Integer beachId, Integer userId) throws NoSuchElementException {
        return beachRatingRepository.findByBeachIdAndUserId(beachId, userId).orElseThrow(() ->
                new NoSuchElementException("Rating of beach with beachId: " + beachId +
                        " left by user with userId: " + userId + " not found"));
    }

    /**
     * Exception handler if NoSuchElementException is thrown in this Controller
     *
     * @param ex exception
     * @return Error message String.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }

}


