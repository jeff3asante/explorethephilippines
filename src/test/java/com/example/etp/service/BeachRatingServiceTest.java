package com.example.etp.service;//package com.example.etp.service;
//
//import com.example.etp.domain.Beach;
//import com.example.etp.domain.BeachRating;
//import com.example.etp.repository.BeachRatingRepository;
//import com.example.etp.repository.BeachRepository;
//import com.example.etp.web.RatingDto;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class BeachRatingServiceTest {
//
//    private static final int USER_ID = 11;
//    private static final int BEACH_ID = 1;
//    private static final int BEACH_RATING_ID = 100;
//    private static final String COMMENT = "It was ok";
//
//    @Mock
//    private Beach beachMock;
//
//    @Mock
//    private BeachRating beachRatingMock;
//
//    @Mock
//    private BeachRepository beachRepositoryMock;
//
//    @Mock
//    private BeachRatingRepository beachRatingRepositoryMock;
//
//    @InjectMocks
//    private BeachRatingService underTest;
//
//    private RatingDto ratingDto = new RatingDto(5,"it was ite", USER_ID);
//
//    @Before
//    public void setupReturnValuesOfMockMethods(){
//        when(beachRepositoryMock.findById(BEACH_ID)).thenReturn(java.util.Optional.of(beachMock));
//        when(beachMock.getId()).thenReturn(BEACH_ID);
//        when(beachRatingRepositoryMock.findByBeachIdAndUserId(BEACH_ID, USER_ID)).thenReturn(java.util.Optional.of(beachRatingMock));
//        when(beachRatingRepositoryMock.findByBeachId(BEACH_ID)).thenReturn(Arrays.asList(beachRatingMock));
//
//    }
//
//    @Test
//    public void testCreateNewBeachRating(){
//        Beach beach = new Beach();
//        BeachRating beachRating = new BeachRating(beach, USER_ID, 5, COMMENT);
//        RatingDto ratingDto = new RatingDto(5, COMMENT, USER_ID);
//        when(beachRatingRepositoryMock.save(any())).thenReturn(beachRating);
//
//        underTest.createNewBeachRating(BEACH_ID, ratingDto);
//
//        Assertions.assertNotNull(beachRating);
//        verify(beachRatingRepositoryMock).save(any());
//
//        // ArgumentCaptor<BeachRating> beachRatingArgumentCaptor = ArgumentCaptor.forClass(BeachRating.class);
//
//        //underTest.createNewBeachRating(BEACH_ID, ratingDto);
//
//        //verify(beachRatingRepositoryMock).save(beachRatingArgumentCaptor.capture());
//
//        //Assertions.assertEquals(java.util.Optional.of(5), java.util.Optional.of(beachRatingArgumentCaptor.getValue().getScore()));
//       // assertThat(beachRatingArgumentCaptor.getValue().getScore(), is(5));
////
////        Beach beach = new Beach();
////        BeachRating beachRating = new BeachRating(beach, USER_ID,5,"it was ite" );
////
////
////        underTest.createNewBeachRating(BEACH_ID, ratingDto);
////
////        assertNotNull(beachRating);
////        verify(beachRatingRepositoryMock).save(any());
//        //assertEquals(java.util.Optional.of(USER_ID), beachRating.getUserId());
//    }
//
//    @Test
//    public void testGetAllRatingsForBeach(){
//        when(beachRatingRepositoryMock.findAll()).thenReturn(Arrays.asList(beachRatingMock));
//        assertEquals(beachRatingMock, underTest.getAllRatingsForBeach(BEACH_ID).get(0));
//    }
//}