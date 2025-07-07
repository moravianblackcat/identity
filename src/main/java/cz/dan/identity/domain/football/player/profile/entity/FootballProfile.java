package cz.dan.identity.domain.football.player.profile.entity;

import cz.dan.identity.domain.person.entity.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballProfile {

    @Id
    private Long personId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    @Enumerated(STRING)
    private FootballProfilePosition position;
}
