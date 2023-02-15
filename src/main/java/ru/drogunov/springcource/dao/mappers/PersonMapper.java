package ru.drogunov.springcource.dao.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.dao.BookDAO;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Пример собственного rowMapper
 */
@Component
public class PersonMapper implements RowMapper<Person> {
    private final BookDAO bookDAO;
    
    @Autowired
    public PersonMapper(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int yearBrith = resultSet.getInt("year_birth");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String role = resultSet.getString("role");
        List<Book> books = bookDAO.indexByPerson(id);
        return new Person(id, name, surname, yearBrith, email, address, Role.valueOf(role), books);
    }
}
