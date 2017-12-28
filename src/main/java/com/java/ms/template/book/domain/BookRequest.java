package com.java.ms.template.book.domain;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookRequest {
	
	private Long id;
	private String name;
	
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
	
	public BookRequest fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, BookRequest.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static class Builder {

        private BookRequest accessPolicy = new BookRequest();

        public Builder withId(Long groupId) {
        	this.accessPolicy.setId(groupId);
        	return this;
        }
        
        public Builder withName(String accountId) {
            this.accessPolicy.setName(accountId);
            return this;
        }
                
        public BookRequest build() {
            return this.accessPolicy;
        }
    }
	
}
