package com.atomscode.movie;

import org.springframework.jdbc.core.RowMapper;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(java.sql.ResultSet resultSet, int i) throws java.sql.SQLException {
        return new Movie(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                null,
                resultSet.getDate("release_date").toLocalDate()
        );
    }
}
