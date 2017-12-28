package com.java.ms.template.book.domain.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.java.ms.template.book.domain.Book;
import com.java.ms.template.book.domain.BookRequest;

@Component
public class BookFactory {
	
	public BookRequest createRequest(Book resource) {
		return new BookRequest.Builder()
				.withId(resource.getId())
				.withName(resource.getName())
				.build();
	}
	
	public Book create(BookRequest resourceRequest) {
		return new Book.Builder()
				.withId(resourceRequest.getId())
				.withName(resourceRequest.getName())
				.build();
	}

	public List<Book> createBookList(List<BookRequest> bookRequests) {
		List<Book> books = new ArrayList<>();
		bookRequests.forEach(bookRequest -> books.add(create(bookRequest)));
		return books;
	}

	public List<BookRequest> createBookRequestList(List<Book> books) {
		List<BookRequest> bookRequests = new ArrayList<>();
		books.forEach(book -> bookRequests.add(createRequest(book)));
		return bookRequests;
	}
}
