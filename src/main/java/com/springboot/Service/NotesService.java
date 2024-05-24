package com.springboot.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;


public interface NotesService {

	public Notes saveNotes(Notes notes);
	public Notes getNotesById(int id);
	public Page<Notes> getNotesByUser(User user,Pageable pageable);
	public Notes updateNotes(Notes notes);
	public boolean deleteNotes(int id);
}
