package ru.drogunov.springcource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.dao.mappers.PersonMapper;
import ru.drogunov.springcource.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final PersonMapper personMapper;
    private final BeanPropertyRowMapper<Person> rowMapper = new BeanPropertyRowMapper<>(Person.class);
    
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, PersonMapper personMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.personMapper = personMapper;
    }
    
    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM person;";
        return jdbcTemplate.query(query, personMapper);
    }
    
    /**
     * Пример с RowMapper от Spring
     */
    public Person show(int id) {
        String query = "SELECT * FROM person WHERE id=?";
        return jdbcTemplate.query(query, new Object[]{id}, rowMapper)
                .stream()
                .findAny()
                .orElse(null);
    }
    
    public Optional<Person> show(String email) {
        String query = "SELECT id, email FROM person WHERE email=?";
        return jdbcTemplate.query(query, new Object[]{email}, rowMapper)
                .stream()
                .findAny();
        
    }
    
    public void save(Person person) {
        String query = "INSERT INTO person(name, surname, age, email, address) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(query,
                person.getName(),
                person.getSurname(),
                person.getAge(),
                person.getEmail(),
                person.getAddress()
        );
    }
    
    public void update(int id, Person person) {
        String query = "UPDATE person SET name=?, surname=?, age=?, email=?, address=? WHERE id=?";
        jdbcTemplate.update(query,
                person.getName(),
                person.getSurname(),
                person.getAge(),
                person.getEmail(),
                person.getAddress(),
                id);
    }
    
    public void delete(int id) {
        String query = "DELETE FROM person WHERE id=?";
        jdbcTemplate.update(query, id);
    }
    
    /*TODO
     * 1) Для того чтобы работало Вариант 1 82, 83 строчки закомментировать.
     * Вариант 2 не работает, но должен использоваться такой подход 86,87 раскомментировать
     * 2) В БД это ENUM файл инициализации БД в ресурсах.
     * 3) На странице http://localhost:8080/admin меняем роль Person
     * Проблема; При текущем варианте Spring делает не правильный запрос, почему так получается
     * не ясно.
     * */
    public void updateRole(Person person, String role) {
        String query = "UPDATE person SET role= ? WHERE id= ?";
        jdbcTemplate.update(query, role, person.getId());
    
    /*
     String query = "UPDATE person SET role=\'"+role+"\' WHERE id=?";
        jdbcTemplate.update(query, person.getId());
    * */
    }
}









