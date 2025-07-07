package cz.dan.identity.integration.fixture.person;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PersonFixture {

    private final JdbcTemplate jdbcTemplate;

    public void savePersons(List<Map<String, Object>> persons) {
        String statement = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?, ?);";

        jdbcTemplate.batchUpdate(statement, new PersonBatchPreparedStatementSetter(persons));
    }

    private record PersonBatchPreparedStatementSetter(List<Map<String, Object>> persons)
            implements BatchPreparedStatementSetter {

        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            Map<String, Object> person = persons.get(i);
            ps.setLong(1, (Long) person.get("id"));
            ps.setString(2, (String) person.get("nationality"));
            ps.setString(3, (String) person.get("first_name"));
            ps.setString(4, (String) person.get("last_name"));
            ps.setString(5, (String) person.get("name"));
            ps.setString(6, (String) person.get("display_name"));
            ps.setDate(7, Date.valueOf((LocalDate) person.get("date_of_birth")));
        }

        @Override
        public int getBatchSize() {
            return persons.size();
        }

    }

}
