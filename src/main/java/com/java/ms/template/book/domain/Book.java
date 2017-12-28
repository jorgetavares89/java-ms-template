package com.java.ms.template.book.domain;

import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Table
@Entity
public class Book {
	
	private Long id;
	private String name;
	private String author;
	private BookCategory bookCategory;
	
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
	
    @ManyToOne  
    @JoinColumn(name = "book_category_id")
    public BookCategory getBookCategory() {
		return bookCategory;
	}
    
	public void setBookCategory(BookCategory base) {
		this.bookCategory = base;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Book fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, Book.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

	public static class Builder {

        private Book accessPolicy = new Book();

        public Builder withId(Long id) {
        	this.accessPolicy.setId(id);
        	return this;
        }
        
        public Builder withName(String name) {
            this.accessPolicy.setName(name);
            return this;
        }
                
        public Book build() {
            return this.accessPolicy;
        }
    }
	
	
}
