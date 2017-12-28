package com.java.ms.template.book.repository;

import org.springframework.data.repository.CrudRepository;

import com.java.ms.template.book.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
}
