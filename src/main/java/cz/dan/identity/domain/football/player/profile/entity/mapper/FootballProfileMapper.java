package cz.dan.identity.domain.football.player.profile.entity.mapper;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.identity.domain.football.player.profile.entity.FootballProfile;
import cz.dan.identity.domain.person.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface FootballProfileMapper {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person", target = "person")
    @Mapping(source = "payload.position", target = "position")
    FootballProfile from(Person person, FootballPlayerOutboxPayload payload);

}
