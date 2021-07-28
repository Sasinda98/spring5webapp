package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    //Framework will inject this.
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping(path = "/books")
    public String getBooks(Model model){
        //Assign the books to model.
        model.addAttribute("books", bookRepository.findAll());

        //Tells the framework to apply the view "books".
        return "books/list";
    }
}
