package cz.dan.identity.domain.football.service;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;

public interface FootballService {

    void save(FootballPlayerOutboxPayload payload);

    void save(FootballTeamOutboxPayload payload);

}
