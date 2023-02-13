package ru.drogunov.springcource.model;

import jakarta.validation.constraints.*;

public class Person {
    private int id;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Name not by null")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String surname;
    @Min(value = 5, message = "Min age 5")
    private int age;
    @Email
    private String email;
    @Pattern(
            regexp = "([A-Z]+|[А-Я]+)(\\w+|[а-я]+), ([A-Z]+|[А-Я]+)(\\w+|[а-я]+), \\d{6}, .+",
            message = "Формат: \"Страна, Город, 123456, \""
    )
    private String address;
    
    public Person(String name, String surname, int age, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.address = address;
    }
    
    public Person(int id, String name, String surname, int age, String email, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.surname = surname;
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Person() {
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
}
