package com.java.ms.template.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.ms.template.book.domain.Book;
import com.java.ms.template.book.domain.BookRequest;
import com.java.ms.template.book.domain.factory.BookFactory;
import com.java.ms.template.book.exception.NotFoundException;
import com.java.ms.template.book.repository.BookRepository;

@Service
public class BookService implements CrudService<BookRequest, Long> {

	private BookRepository repository;
	private BookFactory factory;
	
	@Autowired
	public BookService(BookRepository repository, BookFactory factory) {
		this.repository = repository;
		this.factory = factory;
	}
	
	@Override
	public BookRequest findById(Long id) {
		Book resource = Optional.of(repository.findOne(id)).orElseThrow(() -> new NotFoundException(""));
		return factory.createRequest(resource);
	}

	@Override
	public List<BookRequest> findAll() {
		List<Book> resources = new ArrayList<>();
		repository.findAll().forEach(resources::add);
		return factory.createBookRequestList(resources);
	}

	@Override
	public BookRequest save(BookRequest resourceRequest) {
		Book resource = factory.create(resourceRequest);
		repository.save(resource);
		return resourceRequest;
	}

	@Override
	public void delete(BookRequest resourceRequest) {
		repository.delete(factory.create(resourceRequest));
	}

	
}
