package com.example.bookshelf;

import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.User;
import com.example.bookshelf.model.VolumeInfo;
import com.example.bookshelf.repositories.BookRepository;
import com.example.bookshelf.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BookshelfApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshelfApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(BookRepository bookRepository, UserRepository userRepository) {
        return  args -> {
            User user = new User("Joakim", "password");
            userRepository.save(user);
            VolumeInfo volumeInfo = new VolumeInfo("Title", "Subtitle", List.of("Lasse L"), "2021-04-04", "Bra bok", 333);
            Book book = new Book("asd123", volumeInfo);
            Book book1 = new Book("asd1233", volumeInfo);
            Book book2 = new Book("asd12333", volumeInfo);
            bookRepository.save(book);
            bookRepository.save(book1);
            bookRepository.save(book2);
            user.setBooks(List.of(book, book1, book2));
            userRepository.save(user);
        };

    }

}
