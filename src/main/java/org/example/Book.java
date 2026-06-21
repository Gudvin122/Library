package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private int pages;
    private int year;

    public Book() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return pages == book.pages &&
                year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pages, year);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Название: ").append(getTitle()).append(System.lineSeparator())
                .append("Автор: ").append(getAuthor()).append(System.lineSeparator())
                .append("Год выпуска: ").append(getYear()).append(System.lineSeparator())
                .append("Стр: ").append(getPages());
        return str.toString();
    }
}
