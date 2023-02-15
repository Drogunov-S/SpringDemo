package ru.drogunov.springcource.model;

import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;

public class Book {
    private int id;
    private int person;
    @Size(min = 3, max = 100)
    private String title;
    @Size(min = 3, max = 100)
    private String author;
    @DateTimeFormat(pattern = "YYYY")
    private Year yearManufactured;
    
    public Book() {
    }
    
    public Book(int id, int person, String title, String author, Year yearManufactured) {
        this.id = id;
        this.person = person;
        this.title = title;
        this.author = author;
        this.yearManufactured = yearManufactured;
    }
    
    public boolean isFree() {
        return person == 0;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPersonId() {
        return person;
    }
    
    public void setPerson(int person) {
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
