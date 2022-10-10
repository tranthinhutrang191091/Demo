package com.daicent.service;

import java.util.Optional;

import com.daicent.model.School;

public interface SchoolService <T> {
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(School school);

    void remove(Long id);
}
