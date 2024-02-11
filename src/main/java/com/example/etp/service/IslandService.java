package com.example.etp.service;

import com.example.etp.domain.Island;
import com.example.etp.repository.IslandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IslandService {
    private final IslandRepository islandRepository;

    @Autowired
    public IslandService(IslandRepository islandRepository) {
        this.islandRepository = islandRepository;
    }

    public Island createIsland(String code, String name) {
        return islandRepository.findById(code).orElse(islandRepository.save(new Island(code, name)));
    }

    public Iterable<Island> lookup() {
        return islandRepository.findAll();
    }

    public long total() {
        return islandRepository.count();
    }
}
