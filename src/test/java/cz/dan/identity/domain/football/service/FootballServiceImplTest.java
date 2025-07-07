package cz.dan.identity.domain.football.service;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;
import cz.dan.identity.domain.football.player.profile.entity.FootballProfile;
import cz.dan.identity.domain.football.player.profile.entity.FootballProfilePosition;
import cz.dan.identity.domain.football.player.profile.entity.mapper.FootballProfileMapper;
import cz.dan.identity.domain.football.player.profile.entity.mapper.FootballProfileMapperImpl;
import cz.dan.identity.domain.football.team.entity.FootballTeam;
import cz.dan.identity.domain.football.team.entity.mapper.FootballTeamMapper;
import cz.dan.identity.domain.football.team.entity.mapper.FootballTeamMapperImpl;
import cz.dan.identity.domain.person.entity.Person;
import cz.dan.identity.domain.person.entity.mapper.PersonMapper;
import cz.dan.identity.domain.person.entity.mapper.PersonMapperImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static cz.dan.avro.fetcher.FootballPosition.goalkeeper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballServiceImplTest {

    @Spy
    private final FootballTeamMapper footballTeamMapper = new FootballTeamMapperImpl();

    @Spy
    private final FootballProfileMapper footballProfileMapper = new FootballProfileMapperImpl();

    @Spy
    private final PersonMapper personMapper = new PersonMapperImpl();

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private FootballServiceImpl sut;

    @Captor
    private ArgumentCaptor<FootballProfile> footballProfileCaptor;

    @Captor
    private ArgumentCaptor<FootballTeam> footballTeamCaptor;

    @Captor
    private ArgumentCaptor<Person> personCaptor;

    @Test
    void savesFootballPlayerPayload() {
        FootballPlayerOutboxPayload payload = FootballPlayerOutboxPayload.newBuilder()
                .setId(14L)
                .setFirstName("Oliver")
                .setLastName("Kahn")
                .setName("Oliver Kahn")
                .setDisplayName("Oliver Kahn")
                .setNationality("GER")
                .setDateOfBirth(LocalDate.of(1969, 6, 15))
                .setPosition(goalkeeper)
                .build();

        sut.save(payload);

        verify(entityManager, times(1)).persist(personCaptor.capture());
        verify(entityManager, times(1)).persist(footballProfileCaptor.capture());
        assertThat(personCaptor.getValue())
                .isNotNull()
                .extracting(
                        Person::getId,
                        Person::getFirstName,
                        Person::getLastName,
                        Person::getName,
                        Person::getDisplayName,
                        Person::getNationality,
                        Person::getDateOfBirth
                )
                .containsExactly(
                        14L,
                        "Oliver",
                        "Kahn",
                        "Oliver Kahn",
                        "Oliver Kahn",
                        "GER",
                        LocalDate.of(1969, 6, 15)
                );
        assertThat(footballProfileCaptor.getValue())
                .isNotNull()
                .extracting(
                        FootballProfile::getPersonId,
                        FootballProfile::getPosition
                ).containsExactly(
                        14L,
                        FootballProfilePosition.goalkeeper
                );
    }

    @Test
    void savesTeamPayload() {
        FootballTeamOutboxPayload payload = FootballTeamOutboxPayload.newBuilder()
                .setId(15L)
                .setCountry("CZE")
                .setCity("Brno")
                .setStadium("MěFS Srbská")
                .setFounded(1913)
                .setName("FC Zbrojovka Brno")
                .build();

        sut.save(payload);

        verify(entityManager, times(1)).persist(footballTeamCaptor.capture());
        assertThat(footballTeamCaptor.getValue())
                .isNotNull()
                .extracting(
                        FootballTeam::getId,
                        FootballTeam::getName,
                        FootballTeam::getCity,
                        FootballTeam::getStadium,
                        FootballTeam::getFounded,
                        FootballTeam::getCountry
                ).containsExactly(
                        15L,
                        "FC Zbrojovka Brno",
                        "Brno",
                        "MěFS Srbská",
                        1913,
                        "CZE"
                );
    }

}