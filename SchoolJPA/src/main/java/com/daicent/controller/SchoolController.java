package com.daicent.controller;

import java.util.Optional;

import com.daicent.model.School;
import com.daicent.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/schools")
public class SchoolController {
	@Autowired
	SchoolService schoolSevicer;

	// Hiển thị danh sách
	@GetMapping
	public ResponseEntity<Iterable> findAll() {
		return new ResponseEntity<>(schoolSevicer.findAll(), HttpStatus.OK);
	}
	// Thêm mới
	@PostMapping
	 public ResponseEntity add(@RequestBody School school) {
		schoolSevicer.save(school);
        return new ResponseEntity(schoolSevicer,HttpStatus.OK);
    }
	// Tìm theo id
	@GetMapping("/{id}")
	public ResponseEntity<School> findSById(@PathVariable Long id) {
        Optional<School> schoolOptional = schoolSevicer.findById(id);
        if (!schoolOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(schoolOptional.get(), HttpStatus.OK);
        
    }
	//Chỉnh sửa theo id
	@PutMapping("/{id}")           
    public ResponseEntity update(@RequestBody School school, @PathVariable Long id) {
        Optional<School> schoolOptional = schoolSevicer.findById(id);
        if (!schoolOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        school.setId(schoolOptional.get().getId());
        schoolSevicer.save(school);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	//Xóa theo id
	@DeleteMapping("/{id}")              
    public ResponseEntity<School> delete(@PathVariable Long id) {
        Optional<School> schoolOptional = schoolSevicer.findById(id);
        if (!schoolOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        schoolSevicer.remove(id);
        return new ResponseEntity<>(schoolOptional.get(), HttpStatus.OK);
    }
}
