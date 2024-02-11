package com.example.etp.controller;

import com.example.etp.service.BeachRatingService;
import com.example.etp.web.RatingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * Beach Rating Controller
 * <p>
 * Created by Jeffrey Asante
 */
@RestController
@RequestMapping(path = "/beaches/{beachId}/ratings")
public class BeachRatingController {

    private final BeachRatingService beachRatingService;

    @Autowired
    public BeachRatingController(BeachRatingService beachRatingService){
        this.beachRatingService = beachRatingService;
    }

    @PostMapping(value = "/createBeachRating",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> createBeachRating(@PathVariable(value = "beachId") Integer beachId,
                                            @RequestBody @Validated RatingDto ratingDto) {
        beachRatingService.createNewBeachRating(beachId, ratingDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/listAllRatingsForBeach",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RatingDto>> lookUpAllRatingsForBeach(@PathVariable(value = "beachId") int beachId) {
        return new ResponseEntity<>(beachRatingService.getAllRatingsForBeach(beachId), HttpStatus.OK);
    }

    @GetMapping(value = "/getAverageBeachRating",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Double>> getAverageBeachRating(@PathVariable(value = "beachId") int beachId) {
        return new ResponseEntity<>(beachRatingService.getAverageRating(beachId), HttpStatus.OK);
    }

    @PutMapping(value = "/updateScoreAndComent",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RatingDto> updateScoreAndComent(@PathVariable(value = "beachId") int beachId,
                                                          @RequestBody @Validated RatingDto ratingDto) {
        return new ResponseEntity<>(beachRatingService.updateWithPut(beachId, ratingDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deleteBeachRatingOfUserWithUserId/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBeachRatingOfUserWithUserId(@PathVariable(value = "beachId") int beachId,
                                                                  @PathVariable(value = "userId") int userId) {
        beachRatingService.delete(beachId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
