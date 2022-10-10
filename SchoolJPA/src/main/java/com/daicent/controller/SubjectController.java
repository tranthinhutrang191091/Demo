package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.Student;
import com.daicent.model.Subject;
import com.daicent.service.SubjectService;

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
@RequestMapping("/subjects")
public class SubjectController {
	@Autowired
	SubjectService subjectService;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(subjectService.findAll(), HttpStatus.OK);
	}

	// Thêm mới
	@PostMapping
	public ResponseEntity add(@RequestBody Subject subject) {
		subjectService.save(subject);
		return new ResponseEntity(subjectService, HttpStatus.OK);
	}

	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<Subject> findById(@PathVariable Long id) {
		Optional<Subject> subjectOptional = subjectService.findById(id);
		if (!subjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);

	}

	// Chỉnh sửa theo id
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody Subject subject, @PathVariable Long id) {
		Optional<Subject> subjectOptional = subjectService.findById(id);
		if (!subjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		subject.setId(subjectOptional.get().getId());
		subjectService.save(subject);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Xóa theo id
	@DeleteMapping("/{id}")
	public ResponseEntity<Subject> delete(@PathVariable Long id) {
		Optional<Subject> subjectOptional = subjectService.findById(id);
		if (!subjectOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		subjectService.remove(id);
		return new ResponseEntity<>(subjectOptional.get(), HttpStatus.OK);
	}
}
