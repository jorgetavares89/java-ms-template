package com.java.ms.template.book.service;

import java.util.List;

public interface CrudService<T, K> {
	public T findById(K id);
	public List<T> findAll();
	public T save(T object);
	public void delete(T object);
}
