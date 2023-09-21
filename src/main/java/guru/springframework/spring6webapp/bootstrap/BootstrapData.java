package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author jaakko = new Author();
        jaakko.setFirstName("Jaakko");
        jaakko.setLastName("Kotitalo");
        Book sinuhe = new Book();
        sinuhe.setTitle("Sinuhe egyptiläinen");
        sinuhe.setIsbn("123456789");

        Author teppo = new Author();
        teppo.setFirstName("Teppo");
        teppo.setLastName("Tepilä");

        Book tepposBook = new Book();
        tepposBook.setTitle("Teppos Book");
        tepposBook.setIsbn("987654321");


        Author teppoSaved = authorRepository.save(teppo);


        Author jaakkoSaved = authorRepository.save(jaakko);
        Book sinuheSaved = bookRepository.save(sinuhe);

        jaakko.getBooks().add(sinuheSaved);
        sinuhe.getAuthors().add(jaakkoSaved);

        teppo.getBooks().add(tepposBook);
        tepposBook.getAuthors().add(teppoSaved);

        authorRepository.save(jaakko);
        authorRepository.save(teppo);

        System.out.println("In Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());



    }

}
