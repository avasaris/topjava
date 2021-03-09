package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

import java.time.LocalDateTime;

@Repository
@Profile(Profiles.POSTGRES_DB)
public class JdbcPostgesMealRepository extends JdbcAbstractMealRepository {
    public JdbcPostgesMealRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    protected void addSqlParameter(MapSqlParameterSource map, String name, LocalDateTime dateTime) {
        map.addValue(name, dateTime);
    }

    @Override
    protected LocalDateTime dateAdapter(LocalDateTime ldt) {
        return ldt;
    }
}
