package cz.dan.identity.domain.person.entity;

import cz.dan.identity.domain.person.controller.dto.SearchPersonDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query(
        value = "SELECT id, first_name AS firstName, last_name AS lastName, " +
                "nationality, CAST(date_of_birth AS DATE) AS dateOfBirth FROM person " +
                "WHERE first_name LIKE %:queryName% " +
                "OR last_name LIKE %:queryName% " +
                "OR name LIKE %:queryName%",
        nativeQuery = true
    )
    List<SearchPersonDto> search(@Param("queryName") String name);

}
