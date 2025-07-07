package cz.dan.identity.integration.glue.person;

import cz.dan.avro.fetcher.outbox.PersonOutboxPayload;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;

import static cz.dan.integrationtests.util.AwaitHelper.assertRow;

@RequiredArgsConstructor
public class PersonPersistenceSteps {

    private static final String PERSON_TOPIC_NAME = "fetcher.person";

    private final JdbcTemplate jdbcTemplate;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @When("Person is delivered with the following properties:")
    public void personIsDeliveredWithTheFollowingProperties(Map<String, String> player) {
        PersonOutboxPayload payload = PersonOutboxPayload.newBuilder()
                .setId(Long.parseLong(player.get("id")))
                .setNationality(player.get("nationality"))
                .setFirstName(player.get("firstName"))
                .setLastName(player.get("lastName"))
                .setName(player.get("name"))
                .setDisplayName(player.get("displayName"))
                .setDateOfBirth(LocalDate.parse(player.get("dateOfBirth")))
                .build();

        kafkaTemplate.send(PERSON_TOPIC_NAME, payload);
    }

    @Then("{int}s Person with the following properties is saved:")
    public void personWithTheFollowingPropertiesIsSaved(int timeoutInSeconds, Map<String, Object> expectedPerson) {
        assertRow(timeoutInSeconds, this::getActualPerson, expectedPerson);
    }

    private Map<String, Object> getActualPerson() {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM person",
                    (rs, rowNum) -> Map.of(
                            "id", rs.getString("id"),
                            "nationality", rs.getString("nationality"),
                            "first_name", rs.getString("first_name"),
                            "last_name", rs.getString("last_name"),
                            "name", rs.getString("name"),
                            "display_name", rs.getString("display_name"),
                            "date_of_birth", rs.getString("date_of_birth")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyMap();
        }
    }

}
