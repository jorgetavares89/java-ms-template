package com.java.ms.template.book.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table
@Entity
public class BookCategory {

	private Long id;
    private String name;
    private List<Book> books;

    public BookCategory() {
    	books = new ArrayList<>();
    }
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}

	public BookCategory fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, BookCategory.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }
	public static class Builder {

        private BookCategory bookCategory = new BookCategory();
        
        public Builder withId(Long id) {
        	bookCategory.setId(id);
        	return this;
        }

        public Builder withName(String name) {
        	bookCategory.setName(name);
        	return this;
        }

        public Builder withBooks(List<Book> resources) {
        	bookCategory.setBooks(resources);
        	return this;
        }
        
        public BookCategory build() {
            return bookCategory;
        }
    }
}