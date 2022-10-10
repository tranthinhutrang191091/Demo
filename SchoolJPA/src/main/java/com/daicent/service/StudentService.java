package com.daicent.service;

import java.util.Optional;

import com.daicent.model.Student;


public interface StudentService <T>{
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(Student student);

    void remove(Long id);
}
