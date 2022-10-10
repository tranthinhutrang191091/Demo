package com.daicent.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table (name ="studentSubject")
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubject {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne @JoinColumn(name="idStudent")
	private Student student;
	@ManyToOne @JoinColumn(name="idSubject")
	private Subject subject;
	private Float scores;
}
