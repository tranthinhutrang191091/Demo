package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.Clazz;
import com.daicent.repository.ClazzRepository;
import com.daicent.service.ClazzService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class ClazzServiceImpl implements ClazzService<Clazz>{
	@Autowired
	ClazzRepository clazzRepository;

	@Override
	public Iterable<Clazz> findAll() {
		return clazzRepository.findAll();
	}

	@Override
	public Optional<Clazz> findById(Long id) {
		return clazzRepository.findById(id);
	}

	@Override
	public Clazz save(Clazz clazz) {
		return clazzRepository.save(clazz);
	}

	@Override
	public void remove(Long id) {
		clazzRepository.deleteById(id);
		
	}

	
}
