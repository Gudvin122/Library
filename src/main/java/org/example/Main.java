package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Student> students;
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("students.json")) {
            if (is == null) {
                throw new RuntimeException("students.json not found");
            }
            students = objectMapper.readValue(
                    is,
                    new TypeReference<List<Student>>() {
                    }
            );
        }

        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);

        students.stream()
                .map(Student::getBooks)
                .forEach(System.out::println);

        students.stream()
                .flatMap(student -> student.getBooks().stream())
                .map(Book::getTitle)
                .forEach(System.out::println);

        students.stream()
                .flatMap(student -> student.getBooks().stream())
                .sorted(java.util.Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .forEach(System.out::println);

        Optional<Integer> year = students.stream()
                .flatMap(student -> student.getBooks().stream())
                .filter(book -> "Игра престолов".equals(book.getTitle()))
                .map(Book::getYear)
                .findFirst();

        year.ifPresentOrElse(
                y -> System.out.println("Год выпуска найденной книги: " + y),
                () -> System.out.println("Такая книга отсутствует")
        );
    }
}

