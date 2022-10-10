package com.daicent.service.impl;

import java.util.Optional;

import com.daicent.model.Student;
import com.daicent.repository.StudentRepository;
import com.daicent.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentRepository studentRepository;
	@Override
	public Iterable findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Optional findById(Long id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void remove(Long id) {
		studentRepository.deleteById(id);
	}
	
}
