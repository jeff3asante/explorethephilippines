package com.example.etp.repository;

import com.example.etp.domain.Beach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Beach Repository Interface
 * <p>
 * Created by Jeffrey Asante
 */
public interface BeachRepository extends CrudRepository<Beach, Integer> {
    /**
     * Find Beaches associated with the Island.
     *
     * @param code island code code
     * @return List of found beaches.
     */
    //In previous versions of spring data rest, you needed the at param
    //PagingAndSortingRepository allows you to get a list of
    //beaches by typing http://localhost:8080/beaches in postman
    Page<Beach> findByIslandCode(@Param("code") String code, Pageable pageable);

    @Override
    @RestResource(exported = false)
    Iterable<Beach> findAll();

    @Override
    @RestResource(exported = false)
    <S extends Beach> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Beach> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Beach entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Beach> entities);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
