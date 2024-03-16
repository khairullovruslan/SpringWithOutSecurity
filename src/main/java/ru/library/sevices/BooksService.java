package ru.library.sevices;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.repositories.BooksRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleService peopleService;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleService peopleService) {
        this.booksRepository = booksRepository;
        this.peopleService = peopleService;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }
    public List<Book> findAll(Integer pageValue, Integer books_per_page) {
        return booksRepository.findAll(PageRequest.of(pageValue, books_per_page)).getContent();
    }
    public List<Book> findAll(Boolean isSort) {
        if (isSort){
            return booksRepository.findAll(Sort.by("year"));
        }
        return booksRepository.findAll();

    }
    public List<Book> findAll(Integer pageValue, Integer books_per_page, Boolean isSort) {
        if (isSort){
            return booksRepository.findAll(PageRequest.of(pageValue, books_per_page, Sort.by("year"))).getContent();
        }
        return booksRepository.findAll(PageRequest.of(pageValue, books_per_page)).getContent();
    }

    public Book findOne(int id){
        Book book = booksRepository.findById(id).orElse(null);
        if(book != null) Hibernate.initialize(book.getOwner());
        return book;
    }
    @Transactional
    public void save(Book book){

        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book book){
        book.setBook_id(id);
        booksRepository.save(book);

    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);}

    public void addOwner(int id, int userId) {
        Book book = findOne(id);
        Person person = peopleService.findOne(userId);
        book.setTime_taken(new Date());
        book.setOwner(person);
        person.getBooks().add(book);


    }

    public void updateBookOwner(int id) {
        Book book = findOne(id);
        book.setTime_taken(null);
        Person person = book.getOwner();
        person.getBooks().remove(book);
        book.setOwner(null);
    }


    public List<Book> findByName(String s) {
        System.out.println(s);
        return booksRepository.findByNameStartingWith(s);
    }
}
