package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//Tells Spring framework that its a Spring managed component.
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    //Dependency injection provides instances of authorRepository and bookRepository.
    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("Oxford", "Line1, Line2, Postal Code");
        Author author = new Author("John", "Doe");
        Book book = new Book("Spring Framework 5", "156843246");

        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        publisherRepository.save(publisher);
        bookRepository.save(book);

        /*
        *   Creates one publisher who will publish 2 books written by 2 different authors.
        * */
        Publisher publisher1= new Publisher("Pearson", "Line1, Line2, Postal Code");
        Author author1 = new Author("Damien", "Lewis");
        Book book1 = new Book("Bravo Two Zero", "564564564");

        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);
        book1.setPublisher(publisher1);
        publisher1.getBooks().add(book1);

        authorRepository.save(author1);
        publisherRepository.save(publisher1);
        bookRepository.save(book1);

        Author author2 = new Author("Spring", "Guru");
        Book book2 = new Book("Spring 5 Framework for Dummies", "564564564");

        author2.getBooks().add(book2);
        book2.getAuthors().add(author2);
        book2.setPublisher(publisher1);
        publisher1.getBooks().add(book2);

        authorRepository.save(author2);
        bookRepository.save(book2);

        /*
        *  Print out stuff
        * */
        System.out.println("Started in bootstrap.");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());

    }
}
