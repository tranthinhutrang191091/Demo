package com.daicent.service;

import java.util.Optional;

import com.daicent.model.Clazz;


public interface ClazzService<T>{
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(Clazz clazz);

    void remove(Long id);
}
