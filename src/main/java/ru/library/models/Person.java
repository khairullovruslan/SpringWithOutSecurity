package ru.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;


@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    @NotEmpty(message = "Name shoud not be empty")
    @Column(name = "fio")
    private String fio;
    @Min(value = 1900, message = "Age should be greater than 0")
    @Column(name = "year")
    private int year;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @OneToMany(mappedBy = "owner")
    private List<Book> books;
    public Person(int user_id, String fio, int year) {
        this.user_id = user_id;
        this.fio = fio;
        this.year = year;
    }
    public Person(){

    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int id) {
        user_id = id;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
