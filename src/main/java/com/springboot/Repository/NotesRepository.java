package com.springboot.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Notes;
import com.springboot.Entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	public Page<Notes> findByUser(User user, Pageable pageable);

}
