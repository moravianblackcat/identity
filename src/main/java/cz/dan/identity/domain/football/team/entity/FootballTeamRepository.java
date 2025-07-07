package cz.dan.identity.domain.football.team.entity;

import cz.dan.identity.domain.football.team.controller.dto.SearchTeamDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootballTeamRepository extends CrudRepository<FootballTeam, Long> {

    @Query(
            value = "SELECT id, name, country FROM football_team " +
                    "WHERE name LIKE %:queryName% ",
            nativeQuery = true
    )
    List<SearchTeamDto> search(@Param("queryName") String name);

}
