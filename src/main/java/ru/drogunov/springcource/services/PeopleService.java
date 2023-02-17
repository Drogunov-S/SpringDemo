package ru.drogunov.springcource.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springcource.model.Person;
import ru.drogunov.springcource.model.Role;
import ru.drogunov.springcource.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    
    public List<Person> findAll() {
        return peopleRepository.findAll();
    }
    
    public Person findOne(Integer id) {
        return peopleRepository.findById(id)
                .orElse(null);
    }
    
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
    
    @Transactional
    public void save(Person person, String role) {
        person.setRole(Role.valueOf(role));
        peopleRepository.save(person);
    }
    
    @Transactional
    public void update(Person updatedPerson) {
//        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    
    @Transactional
    public void delete(Integer id) {
        peopleRepository.deleteById(id);
    }
    
    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }
}
