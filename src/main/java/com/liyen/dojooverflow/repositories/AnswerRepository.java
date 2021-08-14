package com.liyen.dojooverflow.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.liyen.dojooverflow.models.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer,Long> {
	
}
