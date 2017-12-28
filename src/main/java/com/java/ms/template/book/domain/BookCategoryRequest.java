package com.java.ms.template.book.domain;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookCategoryRequest {
	
    private Long id;
    private List<BookRequest> bookRequests;

    public BookCategoryRequest() {}

    public Long getId() {
		return id;
	}

	public void setId(Long groupId) {
		this.id = groupId;
	}

	public List<BookRequest> getBookRequests() {
		return bookRequests;
	}

	public void setBookRequests(List<BookRequest> resources) {
		this.bookRequests = resources;
	}

	public BookCategoryRequest fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, BookCategoryRequest.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static class Builder {

        private BookCategoryRequest bookCategoryRequest = new BookCategoryRequest();

        public Builder withId(Long groupId) {
        	bookCategoryRequest.setId(groupId);
        	return this;
        }
        
        public Builder withBookRequests(List<BookRequest> bookRequests) {
        	bookCategoryRequest.setBookRequests(bookRequests);
        	return this;
        }
        
        public BookCategoryRequest build() {
            return this.bookCategoryRequest;
        }
    }
    
    @Override
    public String toString() {
    	try {
			return toJson();
		} catch (JsonProcessingException e) {
			return null;
		}
    }

}
