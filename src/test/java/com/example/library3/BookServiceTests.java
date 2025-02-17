package com.example.library3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    BookRepository mockBookRepository;

    @InjectMocks
    BookService subject;

    @Test
    void create() {
       BookDto bookDto = new BookDto("1984", "George Orwell");
       subject.create(bookDto);
       verify(mockBookRepository).save(
               new BookEntity("1984", "George Orwell")
       );
    }

    @Test
    void fetchAll() {
        // S Seat
        BookEntity bookEntity = new BookEntity("1984", "George Orwell");
        when(mockBookRepository.findAll()).thenReturn(
                List.of(
                   bookEntity,
                   new BookEntity("Zero to One", "Blake Master")
                )
        );

        // E Exercise
        List<BookDto> actual = subject.fetchAll();

        // A Assert
        assertThat(actual).isEqualTo(
                List.of(
                        new BookDto("1984", "George Orwell"),
                        new BookDto("Zero to One", "Blake Master")
                )
        );
    }
}
