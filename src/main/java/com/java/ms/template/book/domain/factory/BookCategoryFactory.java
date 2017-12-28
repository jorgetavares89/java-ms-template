package com.java.ms.template.book.domain.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.ms.template.book.domain.BookCategory;
import com.java.ms.template.book.domain.BookCategoryRequest;

@Component
public class BookCategoryFactory {
	
	private BookFactory bookFactory;
	
	@Autowired
	public BookCategoryFactory (BookFactory resourceFactory) {
		this.bookFactory = resourceFactory;
	}

    public BookCategory create(BookCategoryRequest baseRequest) {
        return new BookCategory.Builder()
        		.withId(baseRequest.getId())
                .withBooks(bookFactory.createBookList(baseRequest.getBookRequests()))
                .build();
    }
    
    public BookCategoryRequest createRequest(BookCategory base) {
        return new BookCategoryRequest.Builder()
                .withId(base.getId())
                .withBookRequests(bookFactory.createBookRequestList(base.getBooks()))
                .build();
    }
    
    public List<BookCategoryRequest> createRequestList(List<BookCategory> bookCategories) {
    	List<BookCategoryRequest> bookCategoryRequests = new ArrayList<>();
    	bookCategories.forEach(book -> bookCategoryRequests.add(createRequest(book)));
    	return bookCategoryRequests;
    }
    
}