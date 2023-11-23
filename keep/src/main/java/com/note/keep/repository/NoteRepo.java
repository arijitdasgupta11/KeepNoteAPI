package com.note.keep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.keep.model.Note;

public interface NoteRepo extends JpaRepository<Note,Integer>{

}
