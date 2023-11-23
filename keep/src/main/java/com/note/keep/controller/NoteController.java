package com.note.keep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.note.keep.model.Note;
import com.note.keep.repository.NoteRepo;

@RestController
public class NoteController {

	@Autowired
	private NoteRepo noterepo;

	@PostMapping("/add")
	public ResponseEntity<String> addNote(@RequestBody Note note) {
		try {
			noterepo.save(note);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Successful", HttpStatus.OK);
	}

	@GetMapping("/notes")
	public List<Note> getNote() {
		try {
			return noterepo.findAll();
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/{id}")
	public Note getNoteById(@PathVariable int id) {
		try {
			return noterepo.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Note note) {

		try {
			if (noterepo.existsById(id)) {
				Note n = noterepo.findById(id).get();
				n.setContent(note.getContent());
				noterepo.save(n);
			}
			return new ResponseEntity<>("Successful", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteNoteById(@PathVariable int id) {
		try {
			noterepo.deleteById(id);
		} catch (Exception e) {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>("Successful", HttpStatus.OK);
	}
}
