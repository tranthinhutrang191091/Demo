package com.daicent.service;

import java.util.Optional;

import com.daicent.model.Subject;

public interface SubjectService <T>{
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(Subject subject);

    void remove(Long id);
}
