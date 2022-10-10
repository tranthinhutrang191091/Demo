package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.School;
import com.daicent.model.Unit;
import com.daicent.service.UnitService;

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
@RequestMapping("/units")
public class UnitController {
	@Autowired
	UnitService<Unit> unitService;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
	}

	// Thêm mới
	@PostMapping
	public ResponseEntity add(@RequestBody Unit unit) {
		unitService.save(unit);
		return new ResponseEntity(unitService, HttpStatus.OK);
	}

	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<Unit> findById(@PathVariable Long id) {
		Optional<Unit> unitOptional = unitService.findById(id);
		if (!unitOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(unitOptional.get(), HttpStatus.OK);

	}

	// Chỉnh sửa theo id
	@PutMapping("/{id}")
	public ResponseEntity update(@RequestBody Unit unit, @PathVariable Long id) {
		Optional<Unit> unitOptional = unitService.findById(id);
		if (!unitOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		unit.setId(unitOptional.get().getId());
		unitService.save(unit);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// Xóa theo id
	@DeleteMapping("/{id}")
	public ResponseEntity<Unit> delete(@PathVariable Long id) {
		Optional<Unit> unitOptional = unitService.findById(id);
		if (!unitOptional.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		unitService.remove(id);
		return new ResponseEntity<>(unitOptional.get(), HttpStatus.OK);
	}
}
