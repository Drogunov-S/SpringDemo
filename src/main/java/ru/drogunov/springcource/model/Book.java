package ru.drogunov.springcource.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;

import static java.util.Objects.isNull;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "person_id")
    private Integer person;
    @Size(min = 3, max = 100)
    private String title;
    @Size(min = 3, max = 100)
    private String author;
    @Column(name = "year", columnDefinition = "integer")
    @DateTimeFormat(pattern = "YYYY")
    private Year yearManufactured;
    
    public Book() {
    }
    
    public Book(Integer id, Integer person, String title, String author, Year yearManufactured) {
        this.id = id;
        this.person = person;
        this.title = title;
        this.author = author;
        this.yearManufactured = yearManufactured;
    }
    
    public boolean isFree() {
        return isNull(person);
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPersonId() {
        return person;
    }
    
    public void setPerson(Integer person) {
        this.person = person;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public Year getYearManufactured() {
        return yearManufactured;
    }
    
    public void setYearManufactured(Year yearManufactured) {
        this.yearManufactured = yearManufactured;
    }
}
