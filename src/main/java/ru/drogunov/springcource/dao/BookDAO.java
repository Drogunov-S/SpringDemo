package ru.drogunov.springcource.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;

import java.util.List;

@Repository
public class BookDAO extends BaseDAO<Book> {
    @Autowired
    public BookDAO(SessionFactory sessionFactory) {
        super(Book.class, sessionFactory);
    }
    
    @Transactional
    public void freedom(Integer id) {
//        String query = "UPDATE book SET person_id = NULL WHERE id = ?";
        Book show = show(id);
        show.setPerson(null);
//        getCurrentSession().createQuery("UPDATE Book b SET b.person = NULL WHERE b.id = :ID", Book.class)
//            .setParameter("ID", id);
    }
    
    @Transactional
    public void reserved(Integer bookId, Person person) {
        show(bookId).setPerson(person.getId());
    }
    
    @Transactional
    public List<Book> indexByPerson(Integer personId) {
        return getCurrentSession().createQuery("from Book b WHERE b.person = :ID", Book.class)
                .setParameter("ID", personId)
                .list();
    }
}
