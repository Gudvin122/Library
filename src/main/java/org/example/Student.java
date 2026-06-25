package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Student {
    private String name;
    private List<Book> books;

    @Override
    public String toString() {
        return "Студент: " + getName() + System.lineSeparator() +
                "Список книг: " + System.lineSeparator() +
                getBooks();
    }
}
