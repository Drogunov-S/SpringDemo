package ru.drogunov.springcource.dao;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.drogunov.springcource.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    
    public PersonDAO() {
        this.people = new ArrayList<>() {{
            add(new Person(++PEOPLE_COUNT, "Tom", 12, "Bosh", "wewe@yandex.ru"));
            add(new Person(++PEOPLE_COUNT, "Gery", 43, "Aisbern", "bestboy@i.ru"));
            add(new Person(++PEOPLE_COUNT, "Garry", 23, "Brow", "brow2341@mail.ru"));
            add(new Person(++PEOPLE_COUNT, "Sergey", 65, "Dr", "Dr-r@i.com"));
            add(new Person(++PEOPLE_COUNT, "Oleg", 23, "Smith", "ol.123@rezolver.yu"));
            add(new Person(++PEOPLE_COUNT, "Jimmy", 9, "Boy", "boyboyboy@boy.vok"));
        }};
    }
    
    public List<Person> index() {
        return people;
    }
    
    public Person getById(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findAny()
                .orElse(null);
    }
    
    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
    
    public void update(int id, Person person) {
        Person personToBeUpdated = getById(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setSurname(person.getSurname());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }
    
    public void delete(int id) {
        people.remove(getById(id));
    }
}
