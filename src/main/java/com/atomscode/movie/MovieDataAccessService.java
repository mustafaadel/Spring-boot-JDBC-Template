package com.atomscode.movie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MovieDataAccessService implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public MovieDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Movie> selectMovies() {
        String sql = """
                select id, name, release_date from movie 
                """;
       return jdbcTemplate.query(sql, new MovieRowMapper());
    }

    @Override
    public int insertMovie(Movie movie) {
        String sql = """
                INSERT INTO movie (name, release_date)
                VALUES (?, ?)
                """;
        return jdbcTemplate.update(
                sql , movie.name() , movie.releaseDate());
    }

    @Override
    public int deleteMovie(int id) {
        String sql = """
                delete from movie where id = ?
                """;
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public Optional<Movie> selectMovieById(int id) {
        var sql = """
                select id, name, release_date from movie where id = ?
                """;
        List<Movie> movies = jdbcTemplate.query(sql, new MovieRowMapper(), id);
        return movies.stream().findFirst();
    }

    @Override
    public int updateMovie(int id, Movie movie) {
        String sql = """
                UPDATE movie
                SET name = ?, release_date = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(
                sql , movie.name() , movie.releaseDate(), id);
    }
    
}
