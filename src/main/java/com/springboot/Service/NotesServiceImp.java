package com.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;
import com.springboot.Repository.NotesRepository;

@Service
public class NotesServiceImp implements NotesService{

	@Autowired
	private NotesRepository notesRepository;
	
	@Override
	public Notes saveNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepository.save(notes);
	}

	@Override
	public Notes getNotesById(int id) {
		// TODO Auto-generated method stub
		return notesRepository.findById(id).get();
	}

	@Override
	public Page<Notes> getNotesByUser(User user , Pageable pageable) {
		// TODO Auto-generated method stub	
		
		return notesRepository.findByUser(user, pageable);
	}

	@Override
	public Notes updateNotes(Notes notes) {
		// TODO Auto-generated method stub
		return notesRepository.save(notes);
	}

	@Override
	public boolean deleteNotes(int id) {
		// TODO Auto-generated method stub
		Notes notes = notesRepository.findById(id).get();
		if(notes != null) {
			notesRepository.delete(notes);
			return true;
		}
		return false;
	}

}
