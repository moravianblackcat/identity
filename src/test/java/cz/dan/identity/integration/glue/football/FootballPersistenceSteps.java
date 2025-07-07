package cz.dan.identity.integration.glue.football;

import cz.dan.avro.fetcher.FootballPosition;
import cz.dan.avro.fetcher.outbox.FootballPlayerOutboxPayload;
import cz.dan.avro.fetcher.outbox.FootballTeamOutboxPayload;
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
public class FootballPersistenceSteps {

    private static final String FOOTBALL_PLAYER_RESPONSE_TOPIC_NAME = "fetcher.response.football.player";

    private static final String FOOTBALL_TEAM_RESPONSE_TOPIC_NAME = "fetcher.response.football.team";

    private final JdbcTemplate jdbcTemplate;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @When("Football player is delivered with the following properties:")
    public void footballPlayerIsDeliveredWithTheFollowingProperties(Map<String, String> player) {
        FootballPlayerOutboxPayload payload = FootballPlayerOutboxPayload.newBuilder()
                .setId(Long.parseLong(player.get("id")))
                .setNationality(player.get("nationality"))
                .setPosition(FootballPosition.valueOf(player.get("position")))
                .setFirstName(player.get("firstName"))
                .setLastName(player.get("lastName"))
                .setName(player.get("name"))
                .setDisplayName(player.get("displayName"))
                .setDateOfBirth(LocalDate.parse(player.get("dateOfBirth")))
                .build();

        kafkaTemplate.send(FOOTBALL_PLAYER_RESPONSE_TOPIC_NAME, payload);
    }

    @When("Football team is delivered with the following properties:")
    public void footballTeamIsDeliveredWithTheFollowingProperties(Map<String, String> team) {
        FootballTeamOutboxPayload payload = FootballTeamOutboxPayload.newBuilder()
                .setId(Long.parseLong(team.get("id")))
                .setName(team.get("name"))
                .setFounded(Integer.parseInt(team.get("founded")))
                .setCountry(team.get("country"))
                .setStadium(team.get("stadium"))
                .setCity(team.get("city"))
                .build();

        kafkaTemplate.send(FOOTBALL_TEAM_RESPONSE_TOPIC_NAME, payload);
    }

    @Then("{int}s Football player profile with the following properties is saved:")
    public void footballPlayerProfileWithTheFollowingPropertiesIsSaved(int timeoutInSeconds,
                                                                       Map<String, Object> expectedProfile) {
        assertRow(timeoutInSeconds, this::getActualFootballProfile, expectedProfile);
    }

    @Then("{int}s Football team with the following properties is saved:")
    public void footballTeamWithTheFollowingPropertiesIsSaved(int timeoutInSeconds,
                                                              Map<String, Object> expectedTeam) {
        assertRow(timeoutInSeconds, this::getActualFootballTeam, expectedTeam);
    }

    private Map<String, Object> getActualFootballProfile() {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM football_profile",
                    (rs, rowNum) -> Map.of(
                            "person_id", rs.getString("person_id"),
                            "position", rs.getString("position")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyMap();
        }
    }

    private Map<String, Object> getActualFootballTeam() {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM football_team",
                    (rs, rowNum) -> Map.of(
                            "id", rs.getString("id"),
                            "name", rs.getString("name"),
                            "founded", rs.getString("founded"),
                            "country", rs.getString("country"),
                            "stadium", rs.getString("stadium"),
                            "city", rs.getString("city")
                    ));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyMap();
        }
    }

}

