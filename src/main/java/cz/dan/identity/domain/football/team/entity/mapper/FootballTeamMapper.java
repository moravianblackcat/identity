package cz.dan.identity.domain.football.team.entity.mapper;

import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;
import cz.dan.identity.domain.football.team.entity.FootballTeam;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface FootballTeamMapper {

    FootballTeam from(FootballTeamOutboxPayload payload);

}
