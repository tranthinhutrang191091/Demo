package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.School;
import com.daicent.repository.SchoolRepository;
import com.daicent.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	SchoolRepository schoolRepository;

	@Override
	public Iterable findAll() {
		return schoolRepository.findAll();
	}

	@Override
	public Optional findById(Long id) {
		
		return schoolRepository.findById(id);
	}

	@Override
	public Object save(School school) {
		return schoolRepository.save(school);
	}

	@Override
	public void remove(Long id) {
		schoolRepository.deleteById(id);
		
	}
	
}
