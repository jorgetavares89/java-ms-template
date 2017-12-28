package com.java.ms.template.book.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.ms.template.book.domain.BookCategory;
import com.java.ms.template.book.domain.BookCategoryRequest;
import com.java.ms.template.book.domain.TopicEvent;
import com.java.ms.template.book.domain.factory.BookCategoryFactory;
import com.java.ms.template.book.exception.NotFoundException;
import com.java.ms.template.book.repository.BookCategoryRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BookCategoryService implements PadingCrudService<BookCategoryRequest, Long> {

	private BookCategoryRepository repository;
	private BookCategoryFactory factory;
	private SnsPublisherService snsPublisher;

	@Autowired
	public BookCategoryService(BookCategoryRepository repository, BookCategoryFactory factory, SnsPublisherService snsPublisher) {
		this.repository = repository;
		this.factory = factory;
		this.snsPublisher = snsPublisher;
	}

	@Override
	public BookCategoryRequest findById(Long id) {
		BookCategory bookCategory = Optional.of(repository.findOne(id))
				.orElseThrow(() -> new NotFoundException("Not Found for this id"));
		return factory.createRequest(bookCategory);
	}

	@HystrixCommand(fallbackMethod = "findAllFallback", 
					ignoreExceptions = { NotFoundException.class })
	@Override
	public Page<BookCategoryRequest> findAll(Pageable pageable) {
		Page<BookCategory> bookCategories = repository.findAll(pageable);
		List<BookCategoryRequest> requests = new ArrayList<>();
		bookCategories.forEach(base -> requests.add(factory.createRequest(base)));
		return new PageImpl<>(requests);
	}

	public List<BookCategoryRequest> findAllFallback(String accountId) {
		return Collections.emptyList();
	}

	@Override
	public BookCategoryRequest save(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
		return saveOrUpdate(bookCategoryRequest, eventType);
	}

	@Override
	public BookCategoryRequest update(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
		return saveOrUpdate(bookCategoryRequest, eventType);
	}

	private BookCategoryRequest saveOrUpdate(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
		repository.save(factory.create(bookCategoryRequest));
		publishOnSns(bookCategoryRequest, eventType);
		return bookCategoryRequest;
	}

	@Override
	public void delete(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
		repository.delete(factory.create(bookCategoryRequest));
	}
	
	private void publishOnSns(BookCategoryRequest bookCategoryRequest, TopicEvent eventType) {
		snsPublisher.publish(bookCategoryRequest, eventType);
	}
}