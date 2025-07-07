package cz.dan.identity.integration.fixture.football.team;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FootballTeamFixture {

    private final JdbcTemplate jdbcTemplate;

    public void saveTeams(List<Map<String, Object>> teams) {
        String statement = "INSERT INTO football_team VALUES (?, ?, ?, ?, ?, ?);";

        jdbcTemplate.batchUpdate(statement, new FootballTeamBatchPreparedStatementSetter(teams));
    }

    private record FootballTeamBatchPreparedStatementSetter(List<Map<String, Object>> teams)
            implements BatchPreparedStatementSetter {

        @Override
        public void setValues(PreparedStatement ps, int i) throws SQLException {
            Map<String, Object> team = teams.get(i);
            ps.setLong(1, (Long) team.get("id"));
            ps.setString(2, (String) team.get("name"));
            ps.setLong(3, (Long) team.get("founded"));
            ps.setString(4, (String) team.get("country"));
            ps.setString(5, (String) team.get("stadium"));
            ps.setString(6, (String) team.get("city"));
        }

        @Override
        public int getBatchSize() {
            return teams.size();
        }

    }

}
