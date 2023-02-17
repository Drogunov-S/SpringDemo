package ru.drogunov.springcource.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.drogunov.springcource.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
