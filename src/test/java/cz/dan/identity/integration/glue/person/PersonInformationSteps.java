package cz.dan.identity.integration.glue.person;

import cz.dan.identity.integration.fixture.person.PersonFixture;
import cz.dan.integrationtests.http.HttpHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PersonInformationSteps {

    private final HttpHelper httpHelper;

    private final PersonFixture personFixture;

    @Given("Persons with the following properties exist:")
    public void personsWithTheFollowingPropertiesExist(List<Map<String, Object>> persons) {
        personFixture.savePersons(persons);
    }

    @When("Search for person with name {} is requested")
    public void searchForPersonWithNameIsRequested(String name) {
        httpHelper.getForArrayWithQueryParams("/api/v1/person", Map.of("name", name));
    }

}
