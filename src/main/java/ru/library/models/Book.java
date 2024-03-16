package ru.library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int book_id;
    @Column(name = "name")
    @NotEmpty(message = "Name shoud not be empty")
    private String name;

    @Column(name = "author")
    @NotEmpty(message = "Name shoud not be empty")
    private String author;

    @Column(name = "year")
    private int year;
    @Column(name = "time_taken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_taken;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Person owner;

    @Transient
    private boolean isExpired;

    public boolean isExpired() {
        return TimeUnit.MILLISECONDS.toDays((new Date()).getTime() - getTime_taken().getTime()) >= 10;
    }

    public Date getTime_taken() {
        return time_taken;
    }

    public void setTime_taken(Date time_taken) {
        this.time_taken = time_taken;
    }


    public void setExpired(boolean expired) {
        isExpired = expired;
    }



    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Book(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Book(int id, String name, String author, int year) {
        this.book_id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
