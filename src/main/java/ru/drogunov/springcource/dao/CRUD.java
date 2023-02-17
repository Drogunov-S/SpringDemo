package ru.drogunov.springcource.dao;

import java.util.List;

public interface CRUD<T> {
    
    void save(T entity);
    
    List<T> index();
    
    T show(Integer id);
    
    void update(T entity);
    
    void delete(Integer id);
    
    
}
