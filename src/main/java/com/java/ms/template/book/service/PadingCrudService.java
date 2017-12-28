package com.java.ms.template.book.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.java.ms.template.book.domain.TopicEvent;

public interface PadingCrudService<T, K> {
	public T findById(K id);
	public Page<T> findAll(Pageable pageable);
	public T save(T object, TopicEvent eventType);
	public T update(T object, TopicEvent eventType);
	public void delete(T object, TopicEvent eventType);
}
