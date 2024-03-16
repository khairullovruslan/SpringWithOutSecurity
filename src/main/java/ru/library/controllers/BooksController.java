package ru.library.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Book;
import ru.library.models.Person;
import ru.library.sevices.BooksService;
import ru.library.sevices.PeopleService;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }



    @GetMapping()
    public String index(@RequestParam(value = "page", required = false) Optional<Integer> pageValue,
                        @RequestParam(value = "books_per_page", required = false)
                        Optional<Integer> books_per_page,
                        @RequestParam(value = "sort_by_year", required = false) Optional<Boolean> isSort,
                        Model model){
        // http://localhost:8080/books?sort_by_year=true&page=0&books_per_page=2
        if (isSort.isPresent() && books_per_page.isPresent() && pageValue.isPresent()){
            model.addAttribute("books", booksService.findAll(pageValue.get(), books_per_page.get(), isSort.get()));
        }
        else if (isSort.isPresent()){
            model.addAttribute("books", booksService.findAll(isSort.get()));
        }
        else if (books_per_page.isPresent() && pageValue.isPresent()){
            model.addAttribute("books", booksService.findAll(pageValue.get(), books_per_page.get()));
        }
        else {
            model.addAttribute("books", booksService.findAll());
        }
        return "books/index";
    }

    @GetMapping("/new")
    public String createBook(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(Book book){
        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("personNew") Person person,  Model model){
        model.addAttribute("book", booksService.findOne(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }
    @PatchMapping("/updateBookOwnerAdd{id}")
    public String updateBookOwnerAdd(@PathVariable("id") int id,  Person person){
        booksService.addOwner(id, person.getUser_id());
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @Valid  Book book){
        booksService.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/newBookPost")
    public String newBookPost(){
        return "redirect:/books/new";
    }

    @PostMapping("/{id}/edit")
    public String bookEdit(){
        return "redirect:/books/{id}/edit";
    }

    @PatchMapping("/updateBookOwner{id}")
    public String updateBookOwner(@PathVariable("id") int id){
        booksService.updateBookOwner(id);
        return "redirect:/books/{id}";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam(value = "text", required = false) Optional<String> text){
        System.out.println();
        if (text.isPresent()){
            System.out.println(text.get());
            model.addAttribute("findBooks", booksService.findByName(text.get()));
        }
        model.addAttribute("books");
        return "books/search";
    }
    @PostMapping("/search")
    public String searchBook(@RequestParam("text") String text){
        String encodedRussianWord = URLEncoder.encode(text, StandardCharsets.UTF_8);

        return "redirect:/books/search?text=" + encodedRussianWord;
    }




}
