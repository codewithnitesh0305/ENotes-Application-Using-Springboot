package com.springboot.Service;

import java.util.List;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;


public interface NotesService {

	public Notes saveNotes(Notes notes);
	public Notes getNotesById(int id);
	public List<Notes> getNotesByUser(User user);
	public Notes updateNotes(Notes notes);
	public boolean deleteNotes(int id);
}
