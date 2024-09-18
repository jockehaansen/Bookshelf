package com.example.bookshelf;

import com.example.bookshelf.model.Book;
import com.example.bookshelf.model.VolumeInfo;
import com.example.bookshelf.repositories.BookRepository;
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
    public CommandLineRunner init(BookRepository bookRepository) {
        return  args -> {
            VolumeInfo volumeInfo = new VolumeInfo("Title", "Subtitle", List.of("Lasse L"), "2021-04-04", "Bra bok", 333);
            Book book = new Book(volumeInfo, true);
            Book book1 = new Book(volumeInfo, false);
            Book book2 = new Book(volumeInfo, false);
            bookRepository.save(book);
            bookRepository.save(book1);
            bookRepository.save(book2);
        };

    }

}
