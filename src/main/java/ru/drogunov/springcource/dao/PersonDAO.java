package ru.drogunov.springcource.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.drogunov.springcource.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    
    public PersonDAO() {
        this.people = new ArrayList<>() {{
            add(new Person(++PEOPLE_COUNT, "Tom", "Bosh"));
            add(new Person(++PEOPLE_COUNT, "Gery", "Aisbern"));
            add(new Person(++PEOPLE_COUNT, "Garry", "Brow"));
            add(new Person(++PEOPLE_COUNT, "Sergey", "Dr"));
            add(new Person(++PEOPLE_COUNT, "Oleg", "Smith"));
            add(new Person(++PEOPLE_COUNT, "Jimmy", "Boy"));
        }};
    }
    
    public List<Person> index() {
        return people;
    }
    
    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
    
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
