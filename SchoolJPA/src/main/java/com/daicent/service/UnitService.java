package com.daicent.service;

import java.util.Optional;

import com.daicent.model.Unit;

public interface UnitService <T>{
	Iterable<T> findAll();

    Optional<T> findById(Long id);

    T save(Unit unit);

    void remove(Long id);
}
