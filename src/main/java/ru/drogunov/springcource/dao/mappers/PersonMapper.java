package ru.drogunov.springcource.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Пример собственного rowMapper
 * */
@Component
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int age = resultSet.getInt("age");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String role = resultSet.getString("role");
        return new Person(id, name, surname, age, email, address, Role.valueOf(role));
    }
}
