package com.daicent.service;

import java.util.Optional;

import com.daicent.model.StudentSubject;

public interface StudentSubjectService <T>{
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(StudentSubject studentSubject);

    void remove(Long id);
}
