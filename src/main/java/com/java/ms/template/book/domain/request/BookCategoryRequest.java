package com.java.ms.template.book.domain.request;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookCategoryRequest {
	
    private Long groupId;
    private List<BookRequest> resourcesRequest;

    public BookCategoryRequest() {}

    public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public List<BookRequest> getResources() {
		return resourcesRequest;
	}

	public void setResources(List<BookRequest> resources) {
		this.resourcesRequest = resources;
	}

	public BookCategoryRequest fromJson(String jsonString) throws IOException {
        return new ObjectMapper().readValue(jsonString, BookCategoryRequest.class);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public static class Builder {

        private BookCategoryRequest accessPolicyRequest = new BookCategoryRequest();

        public Builder withGroupId(Long groupId) {
        	this.accessPolicyRequest.setGroupId(groupId);
        	return this;
        }
        
        public Builder withResourcesRequest(List<BookRequest> resourceRequests) {
        	this.accessPolicyRequest.setResources(resourceRequests);
        	return this;
        }
        
        public BookCategoryRequest build() {
            return this.accessPolicyRequest;
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
