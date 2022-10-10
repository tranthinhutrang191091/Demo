package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.Student;
import com.daicent.model.StudentSubject;
import com.daicent.service.StudentSubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student-subject")
public class StudentSubjectController {
	@Autowired
	StudentSubjectService studentSubjectService;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(studentSubjectService.findAll(), HttpStatus.OK);
	}

	// Thêm mới
	@PostMapping
	public ResponseEntity add(@RequestBody StudentSubject studentSubject) {
		studentSubjectService.save(studentSubject);
		return new ResponseEntity(studentSubjectService, HttpStatus.OK);
	}

	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<StudentSubject> findById(@PathVariable Long id) {
		Optional<StudentSubject> studentSubjectOptional = studentSubjectService.findById(id);
		if (!studentSubjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(studentSubjectOptional.get(), HttpStatus.OK);

	}

	// Chỉnh sửa theo id
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody StudentSubject studentSubject, @PathVariable Long id) {
		Optional<StudentSubject> studentSubjectOptional = studentSubjectService.findById(id);
		if (!studentSubjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		studentSubject.setId(studentSubjectOptional.get().getId());
		studentSubjectService.save(studentSubject);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Xóa theo id
	@DeleteMapping("/{id}")
	public ResponseEntity<StudentSubject> delete(@PathVariable Long id) {
		Optional<StudentSubject> studentSubjectOptional = studentSubjectService.findById(id);
		if (!studentSubjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		studentSubjectService.remove(id);
		return new ResponseEntity<>(studentSubjectOptional.get(), HttpStatus.OK);
	}
}
