package cz.dan.identity.domain.football.service;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;
import cz.dan.identity.domain.football.player.profile.entity.mapper.FootballProfileMapper;
import cz.dan.identity.domain.football.team.entity.FootballTeam;
import cz.dan.identity.domain.football.team.entity.mapper.FootballTeamMapper;
import cz.dan.identity.domain.person.entity.Person;
import cz.dan.identity.domain.person.entity.mapper.PersonMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FootballServiceImpl implements FootballService {

    private final FootballTeamMapper footballTeamMapper;

    private final FootballProfileMapper footballProfileMapper;

    private final PersonMapper personMapper;

    private final EntityManager entityManager;

    @Override
    public void save(FootballPlayerOutboxPayload payload) {
        Person person = personMapper.from(payload);
        entityManager.persist(person);
        entityManager.flush();
        entityManager.persist(footballProfileMapper.from(person, payload));
    }

    @Override
    public void save(FootballTeamOutboxPayload payload) {
        FootballTeam footballTeam = footballTeamMapper.from(payload);
        entityManager.persist(footballTeam);
    }

}
