package ru.drogunov.springcource.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class BaseDAO<T> implements CRUD<T> {
    private Class<T> aClass;
    private SessionFactory sessionFactory;
    
    @Autowired
    public BaseDAO(Class<T> aClass, SessionFactory sessionFactory) {
        this.aClass = aClass;
        this.sessionFactory = sessionFactory;
    }
    
    public BaseDAO() {
    }
    
    @Override
    @Transactional
    public void save(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(entity);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<T> index() {
        Session session = getCurrentSession();
        List<T> aCLASS = session
                .createQuery("FROM " + aClass.getSimpleName(), aClass)
                .getResultList();
        return aCLASS;
    }
    @Transactional(readOnly = true)
    @Override
    public T show(Integer id) {
        return getCurrentSession().get(aClass, id);
    }
    
    @Transactional
    @Override
    public void update(T entity) {
        getCurrentSession().merge(entity);
    }
    
    @Transactional
    @Override
    public void delete(Integer id) {
        getCurrentSession().remove(show(id));
    }
    
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
