package ru.drogunov.springcource.model;

import jakarta.validation.constraints.*;

import java.util.List;

public class Person {
    private int id;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String surname;
    @Min(value = 5, message = "Min age 5")
    private int yearBrith;
    @Email
    private String email;
    
    @Pattern(
            regexp = "([A-Z]+|[А-Я]+)(\\w+|[а-я]+), ([A-Z]+|[А-Я]+)(\\w+|[а-я]+), \\d{6}, .+",
            message = "Формат: \"Страна, Город, 123456, \""
    )
    private String address;
    Role role;
    
    private List<Book> books;
    
    public Person() {
    }
    
    public Person(int id, String name, String surname, int yearBrith, String email, String address, Role role, List<Book> books) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearBrith = yearBrith;
        this.email = email;
        this.address = address;
        this.role = role;
        this.books = books;
    }
    
    
    
    public String getFullname() {
        return String.format("%s %s", name, surname);
    }
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getYearBrith() {
        return yearBrith;
    }
    
    public void setYearBrith(int yearBrith) {
        this.yearBrith = yearBrith;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
