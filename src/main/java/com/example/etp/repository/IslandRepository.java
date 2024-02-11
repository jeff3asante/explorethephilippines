package com.example.etp.repository;

import com.example.etp.domain.Island;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "islands", path = "islands")
public interface IslandRepository extends CrudRepository<Island, String> {

    /*
typing http://localhost:8080/islands/search in postman maps to this method;
    "_links": {
        "findByName": {
            "href": "http://localhost:8080/islands/search/findByName{?name}",
            "templated": true
        },
        "self": {
            "href": "http://localhost:8080/islands/search"
 */
    Optional<Island> findByName(@Param("name") String name);

    @Override
    @RestResource(exported = false)
    <S extends Island> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Island> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(Island entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Island> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
