package com.example.etp.service;

import com.example.etp.domain.Beach;
import com.example.etp.domain.Island;
import com.example.etp.domain.Popularity;
import com.example.etp.domain.Region;
import com.example.etp.repository.BeachRepository;
import com.example.etp.repository.IslandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.example.etp.domain.Popularity.Very_Popular;
import static com.example.etp.domain.Region.South_Boracay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BeachServiceTest {


    private static String TITLE = "Nacpan Beach";
    private static String DESCRIPTION = "Nacpan Beach is one of the most beautiful stretches of sand in the Philippines. With four kilometres of soft cream-coloured sand, swaying coconut palms.";
    private static String LENGTH = "4km";
    private static String SAND = "Soft cream-coulerd";
    private static String KEYWORDS = "Soft Sand, Beach Hostel, Palawan, Nacpan";
    private static String ISLAND_NAME = "Palawan";
    private static String ISLAND_CODE = "PLWN";
    private static Popularity POPULARITY = Very_Popular;
    private static Region REGION = South_Boracay;


    @Mock
    private BeachRepository beachRepository;

    @Mock
    private IslandRepository islandRepository;

    @InjectMocks
    private BeachService underTest;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBeach() {
        //given
        Island island = new Island(ISLAND_CODE, ISLAND_NAME);
        Beach expected = beachBuilder();

        when(islandRepository.findByName(anyString())).thenReturn(java.util.Optional.of(island));
        when(beachRepository.save(any())).thenReturn(expected);
        when(underTest.total()).thenReturn(1L);

        //when
        Beach actual = underTest.createBeach(TITLE,
                DESCRIPTION,
                LENGTH,
                SAND,
                KEYWORDS,
                ISLAND_NAME,
                POPULARITY,
                REGION);

        //then
        assertNotNull(actual);
        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(1L, beachRepository.count());
    }

    @Test()
    public void testRuntimeException() {
        assertThrows(RuntimeException.class, () -> {
            underTest.createBeach(TITLE,
                    DESCRIPTION,
                    LENGTH,
                    SAND,
                    KEYWORDS,
                    ISLAND_NAME,
                    POPULARITY,
                    REGION);
        });
    }

    private Beach beachBuilder() {
        return new Beach(TITLE,
                DESCRIPTION,
                LENGTH,
                SAND,
                KEYWORDS,
                new Island(ISLAND_CODE, ISLAND_NAME),
                POPULARITY,
                REGION);
    }
}
