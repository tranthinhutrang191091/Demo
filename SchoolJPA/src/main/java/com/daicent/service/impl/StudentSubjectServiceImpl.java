package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.StudentSubject;
import com.daicent.repository.StudentSubjectRepository;
import com.daicent.service.StudentSubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentSubjectServiceImpl implements StudentSubjectService{
	@Autowired
	StudentSubjectRepository studentSubjectRepository;

	@Override
	public Iterable findAll() {
		return studentSubjectRepository.findAll();
	}

	@Override
	public Optional findById(Long id) {
		return studentSubjectRepository.findById(id);
	}

	@Override
	public Object save(StudentSubject studentSubject) {
		return studentSubjectRepository.save(studentSubject);
	}

	@Override
	public void remove(Long id) {
		studentSubjectRepository.deleteById(id);
		
	}
	
}
