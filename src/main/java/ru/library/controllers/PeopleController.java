package ru.library.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.library.models.Person;
import ru.library.sevices.PeopleService;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.findOneWithBooks(id));;
        return "people/show";
    }
    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id,  Model model){
        model.addAttribute(peopleService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, @Valid  Person person){
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping()
    public String newPerson(@Valid  Person person){
        peopleService.save(person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/edit")
    public String editP(){
        return "redirect:/people/{id}/edit";
    }
    @PostMapping("/newPersonPost")
    public String newPersonPost(){
        return "redirect:/people/new";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }


}
