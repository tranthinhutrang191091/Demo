package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.Clazz;
import com.daicent.model.School;
import com.daicent.service.ClazzService;

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
@RequestMapping("/clazzes")
public class ClazzController {
	@Autowired
	ClazzService clazzService;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(clazzService.findAll(), HttpStatus.OK);
	}

	// Thêm mới
	@PostMapping
	public ResponseEntity add(@RequestBody Clazz clazz) {
		clazzService.save(clazz);
		return new ResponseEntity(clazzService, HttpStatus.OK);
	}

	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<Clazz> findById(@PathVariable Long id) {
		Optional<Clazz> clazzOptional = clazzService.findById(id);
		if (!clazzOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clazzOptional.get(), HttpStatus.OK);

	}

	// Chỉnh sửa theo id
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody Clazz clazz, @PathVariable Long id) {
		Optional<Clazz> clazzOptional = clazzService.findById(id);
		if (!clazzOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clazz.setId(clazzOptional.get().getId());
		clazzService.save(clazz);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Xóa theo id
	@DeleteMapping("/{id}")
	public ResponseEntity<Clazz> delete(@PathVariable Long id) {
		Optional<Clazz> clazzOptional = clazzService.findById(id);
		if (!clazzOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		clazzService.remove(id);
		return new ResponseEntity<>(clazzOptional.get(), HttpStatus.OK);
	}
}
