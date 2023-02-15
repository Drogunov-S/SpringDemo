package ru.drogunov.springcource.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    @Column(name = "year_birth")
    private int yearBrith;
    private String email;
    private String address;
//    @Enumerated(EnumType.STRING)
    
    public Person() {
    }
    
    public Person(String name, String surname, int yearBrith, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.yearBrith = yearBrith;
        this.email = email;
        this.address = address;
    }
    
    public Person(int id, String name, String surname, int yearBrith, String email, String address, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearBrith = yearBrith;
        this.email = email;
        this.address = address;
    }
    
    public String getFullname() {
        return String.format("%s %s", name, surname);
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
    
}
