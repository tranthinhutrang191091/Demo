package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.School;
import com.daicent.model.Student;
import com.daicent.service.StudentService;

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
@RequestMapping("/students")
public class StudentController {
	@Autowired
	StudentService studentService;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
	}

	// Thêm mới
	@PostMapping
	public ResponseEntity add(@RequestBody Student student) {
		studentService.save(student);
		return new ResponseEntity(studentService, HttpStatus.OK);
	}

	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<Student> findById(@PathVariable Long id) {
		Optional<Student> studentOptional = studentService.findById(id);
		if (!studentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);

	}

	// Chỉnh sửa theo id
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody Student student, @PathVariable Long id) {
		Optional<Student> studentOptional = studentService.findById(id);
		if (!studentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		student.setId(studentOptional.get().getId());
		studentService.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Xóa theo id
	@DeleteMapping("/{id}")
	public ResponseEntity<Student> delete(@PathVariable Long id) {
		Optional<Student> studentOptional = studentService.findById(id);
		if (!studentOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		studentService.remove(id);
		return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
	}
}
