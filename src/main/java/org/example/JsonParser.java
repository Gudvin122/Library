package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;

public class JsonParser {
    public void parse() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Student> students;

        try (InputStream is = JsonParser.class.getClassLoader().getResourceAsStream("students.json")) {
            if (is == null) {
                throw new IllegalStateException("students.json not found");
            }
            students = objectMapper.readValue(is, new TypeReference<List<Student>>() {});
        } catch (IOException e) {
            throw new UncheckedIOException("Error reading students.json", e);
        }

        students.stream()
                .peek(s -> System.out.println(s.getName()))
                .map(Student::getBooks)
                .forEach(System.out::println);

        students.stream()
                .peek(System.out::println)
                .flatMap(student -> student.getBooks().stream())
                .sorted(java.util.Comparator.comparingInt(Book::getPages))
                .distinct()
                .filter(book -> book.getYear() > 2000)
                .limit(3)
                .map(Book::getYear)
                .findFirst()
                .ifPresentOrElse(
                        year -> System.out.println("Год выпуска найденной книги: " + year),
                        () -> System.out.println("Такая книга отсутствует")
                );
    }
}

