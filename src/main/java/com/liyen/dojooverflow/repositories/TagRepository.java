package com.liyen.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.liyen.dojooverflow.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag,Long> {
	// list all tags
	List<Tag> findAll();
	
	// find a tag with a subject
	Tag findBySubject(String subject);

}
