package cz.dan.identity.integration.glue.football;

import cz.dan.identity.integration.fixture.football.team.FootballTeamFixture;
import cz.dan.integrationtests.http.HttpHelper;
import cz.dan.integrationtests.http.HttpResponsesCache;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class FootballInformationSteps {

    private final FootballTeamFixture footballTeamFixture;

    private final HttpHelper httpHelper;

    private final HttpResponsesCache httpResponsesCache;

    @Given("Teams with the following properties exist:")
    public void teamsWithTheFollowingPropertiesExist(List<Map<String, Object>> teams) {
        footballTeamFixture.saveTeams(teams);
    }

    @When("Search for team with name {} is requested")
    public void searchForPersonWithNameIsRequested(String name) {
        httpHelper.getForArrayWithQueryParams("/api/v1/team", Map.of("name", name));
    }

    @Then("Football team search returns those teams:")
    public void footballTeamSearchReturnsThoseTeams(List<Map<String, Object>> expectedTeams) {
        List<Map<String, Object>> actualTeams = (List<Map<String, Object>>) httpResponsesCache.get("/api/v1/team");

        assertThat(actualTeams)
                .usingRecursiveComparison()
                .withComparatorForType(Comparator.comparingLong(Number::longValue), Number.class)
                .isEqualTo(expectedTeams);
    }

}
