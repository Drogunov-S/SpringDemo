package ru.drogunov.springcource.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

@Component
public class BookMapper implements RowMapper<Book> {
    
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        int id = resultSet.getInt("id");
        int personalId = resultSet.getInt("person_id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        Year year = Year.of(resultSet.getInt("year"));
        return new Book(id, personalId, title, author, year);
    }
    
}
