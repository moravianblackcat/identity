package cz.dan.identity.domain.football.team.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballTeam {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "founded")
    private Integer founded;

    @Column(name = "country", nullable = false, length = 3)
    private String country;

    @Column(name = "stadium", nullable = false)
    private String stadium;

    @Column(name = "city", nullable = false)
    private String city;

}
