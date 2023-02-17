package ru.drogunov.springcource.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springcource.model.Person;

import java.util.Optional;

@Repository
public class PersonDAO extends BaseDAO<Person>{
    
    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        super(Person.class, sessionFactory);
    }
    
    public PersonDAO() {
        super();
    }
    
    @Transactional
    public Optional<Person> show(String email) {
        String query = "FROM Person p WHERE p.email=:EMAIL";
        return getCurrentSession()
                .createQuery(query, Person.class)
                .setParameter("EMAIL", email)
                .uniqueResultOptional();
        
    }
    /*TODO
     * 1) Для того чтобы работало Вариант 1 82, 83 строчки закомментировать.
     * Вариант 2 не работает, но должен использоваться такой подход 86,87 раскомментировать
     * 2) В БД это ENUM файл инициализации БД в ресурсах.
     * 3) На странице http://localhost:8080/admin меняем роль Person
     * Проблема; При текущем варианте Spring делает не правильный запрос, почему так получается
     * не ясно.
     * */
    @Transactional
    public void updateRole(Person person, String role) {
        getCurrentSession().update(person);
    }
}









