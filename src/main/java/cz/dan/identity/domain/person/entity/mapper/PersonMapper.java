package cz.dan.identity.domain.person.entity.mapper;

import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import cz.dan.identity.domain.person.entity.Person;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PersonMapper {

    Person from(FootballPlayerOutboxPayload payload);

    Person from(PersonOutboxPayload payload);

}
