package ru.drogunov.springcource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.drogunov.springcource.model.Book;
import ru.drogunov.springcource.model.Person;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Book.class)
                .buildSessionFactory();
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.persist(getPerson());
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
    
    private static Person getPerson() {
        return new Person("Петров Петр Петрович",
                            "Aisbern",
                            1960,
                            "bestboy@i.ru",
                            "Россия, Москва, 125476, ул. Видова дом 2"
        );
    }
    
    private static void startOut() {
        System.out.println("//".repeat(18));
        System.out.println("//".repeat(5) + " @ Start output " + "//".repeat(5));
        System.out.println("//".repeat(18) + "\n");
    }
    
    private static void endOut() {
        System.out.println("\n" + "//".repeat(18));
        System.out.println("//".repeat(5) + " @ End output " + "//".repeat(6));
        System.out.println("//".repeat(18) + "\n");
    }
}
