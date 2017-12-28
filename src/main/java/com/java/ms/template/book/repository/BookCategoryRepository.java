package com.java.ms.template.book.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.java.ms.template.book.domain.BookCategory;

public interface BookCategoryRepository extends PagingAndSortingRepository<BookCategory, Long> {	

}