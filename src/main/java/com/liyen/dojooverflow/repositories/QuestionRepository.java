package com.liyen.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.liyen.dojooverflow.models.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question,Long>{
	// list all questions
	List<Question> findAll();
}
