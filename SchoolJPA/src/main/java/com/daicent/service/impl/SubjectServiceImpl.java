package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.Subject;
import com.daicent.repository.SubjectRepository;
import com.daicent.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
	@Autowired 
	SubjectRepository subjectRepository;

	@Override
	public Iterable findAll() {
		return subjectRepository.findAll();
	}

	@Override
	public Optional findById(Long id) {
		return subjectRepository.findById(id);
	}

	@Override
	public Object save(Subject subject) {
		return subjectRepository.save(subject);
	}

	@Override
	public void remove(Long id) {
		subjectRepository.deleteById(id);
		
	}
	
}
