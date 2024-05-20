package com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	public List<Notes> findByUser(User user);

}
