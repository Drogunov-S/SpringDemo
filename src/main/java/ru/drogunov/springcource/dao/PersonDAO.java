package ru.drogunov.springcource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.config.ConnectionDB;
import ru.drogunov.springcource.model.Person;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private ConnectionDB connectionDB;
    
    @Autowired
    public PersonDAO(ConnectionDB connectionDB) {
        this.connectionDB = connectionDB;
    }
    
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM first_db.public.person;";
        try (ResultSet resultSet = connectionDB.get()
                .createStatement()
                .executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                people.add(new Person(id, name, surname, age, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }
    
    public Person getById(int id) {
        String query = "SELECT * FROM person WHERE id=?";
        try {
            PreparedStatement preparedStatement = connectionDB.get().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultPerson(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    private Person resultPerson(ResultSet resultSet) throws SQLException {
        resultSet.next();
//        if (resultSet.getFetchSize() > 0) {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        int age = resultSet.getInt("age");
        String email = resultSet.getString("email");
        return new Person(id, name, surname, age, email);
//        }
//        throw new ResolutionException("Result set is empty");
    }
    
    public void save(Person person) {
        String query = "INSERT INTO person(name, surname, age, email) VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connectionDB.get().prepareStatement(query);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(int id, Person person) {
        String query = "UPDATE person SET name=?, surname=?, age=?, email=? WHERE id=?";
        try {
            PreparedStatement preparedStatement = connectionDB.get().prepareStatement(query);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.setString(4, person.getEmail());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(int id) {
        String query = "DELETE FROM person WHERE id=?";
        try {
            PreparedStatement preparedStatement = connectionDB.get().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}









