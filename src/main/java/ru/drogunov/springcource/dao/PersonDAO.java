package ru.drogunov.springcource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.config.ConnectionDB;
import ru.drogunov.springcource.model.Person;

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
        try (ResultSet resultSet = connectionDB.executeQuery(query)) {
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
//        return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);
        return null;
    }
    
    public void save(Person person) {
        String query = String.format("INSERT INTO person(%s,%s,%s,%s) VALUES('%s','%s',%d,'%s')",
                "name", "surname", "age", "email",
                person.getName(), person.getSurname(), person.getAge(), person.getEmail()
                );
        System.out.println(query);
        connectionDB.executeUpdate(query);
    }
    
    public void update(int id, Person person) {
//        Person personToBeUpdated = getById(id);
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setSurname(person.getSurname());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }
    
    public void delete(int id) {
//        people.remove(getById(id));
    }
}
