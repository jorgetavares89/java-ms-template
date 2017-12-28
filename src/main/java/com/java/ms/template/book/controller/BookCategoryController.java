package com.java.ms.template.book.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.ms.template.book.domain.BookCategoryRequest;
import com.java.ms.template.book.domain.TopicEvent;
import com.java.ms.template.book.service.BookCategoryService;

@RestController
@RequestMapping("/bookCategory")
public class BookCategoryController {

    private final BookCategoryService service;
    
    @Autowired
    public BookCategoryController(BookCategoryService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<BookCategoryRequest> getAll (Pageable pageable) {
        return service.findAll(pageable);
    }
        
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public BookCategoryRequest getOneByAccountIdAndGroupId (@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<BookCategoryRequest> update (@PathVariable Long id, 
    												   @Valid @RequestBody BookCategoryRequest baseRequest) {
        BookCategoryRequest updated = service.update(baseRequest, TopicEvent.UPDATED);
        return ResponseEntity
                .ok()
                .body(updated);
    }

    @RequestMapping(method = RequestMethod.POST, path ="/{id}")
    public ResponseEntity<BookCategoryRequest> save (@PathVariable Long id,
    									     		 @RequestBody @Valid BookCategoryRequest baseRequest) {
        BookCategoryRequest saved = service.save(baseRequest, TopicEvent.CREATED);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }
}
