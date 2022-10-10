package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.Unit;
import com.daicent.repository.UnitRepository;
import com.daicent.service.UnitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UnitServiceImpl implements UnitService<Unit> {
	@Autowired
	UnitRepository unitRepository;

	@Override
	public Iterable<Unit> findAll() {
		return unitRepository.findAll();
	}

	@Override
	public Optional<Unit> findById(Long id) {
		return unitRepository.findById(id);
	}

	@Override
	public Unit save(Unit unit) {
		return unitRepository.save(unit);
	}

	@Override
	public void remove(Long id) {
		unitRepository.deleteById(id);
		
	}

}
