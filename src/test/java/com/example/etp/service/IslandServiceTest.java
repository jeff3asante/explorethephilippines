package com.example.etp.service;

import com.example.etp.domain.Island;
import com.example.etp.repository.IslandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class IslandServiceTest {

    @Mock
    private IslandRepository islandRepository;

    @InjectMocks
    public IslandService underTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateIsland() {
        //given
        Island island = new Island("PLWN", "Palawan");
        when(islandRepository.save(any())).thenReturn(island);
        when(islandRepository.findById(anyString())).thenReturn(java.util.Optional.of(island));
        when(underTest.total()).thenReturn(1L);

        //when
        underTest.createIsland("PLWN", "Palawan");

        //then
        assertNotNull(island);
        assertEquals("PLWN", island.getCode());
        assertEquals(1L, islandRepository.count());
    }

    @Test
    public void testLookUp() {
        //given
        Island palawan = new Island("PLWN", "Palawan");
        Island siquijor = new Island("SQJR", "Siquijor");
        Island bohol = new Island("BHL", "Bohol");
        Island boracay = new Island("BRCY", "Boracay");

        //when
        when(islandRepository.save(any())).thenReturn(palawan);
        when(islandRepository.save(any())).thenReturn(siquijor);
        when(islandRepository.save(any())).thenReturn(bohol);
        when(islandRepository.save(any())).thenReturn(boracay);

        //then
        assertNotNull(underTest.lookup());
    }
}
