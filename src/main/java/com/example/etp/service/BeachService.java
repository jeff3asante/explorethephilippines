package com.example.etp.service;

import com.example.etp.domain.Beach;
import com.example.etp.domain.Island;
import com.example.etp.domain.Popularity;
import com.example.etp.domain.Region;
import com.example.etp.repository.BeachRepository;
import com.example.etp.repository.IslandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BeachService {
    private final BeachRepository beachRepository;
    private final IslandRepository islandRepository;

    @Autowired
    public BeachService(BeachRepository beachRepository, IslandRepository islandRepository) {
        this.beachRepository = beachRepository;
        this.islandRepository = islandRepository;
    }

    public Beach createBeach(String title, String description, String length,
                             String sand, String keywords,
                             String islandName, Popularity popularity, Region region) {
        Island island = islandRepository.findByName(islandName).orElseThrow(() ->
                new RuntimeException("Island does not exist, at least not in the Philippenes " + islandName));

        return beachRepository.save(new Beach(title, description, length, sand, keywords, island, popularity, region));
    }

    public long total() {
        return beachRepository.count();
    }
}
