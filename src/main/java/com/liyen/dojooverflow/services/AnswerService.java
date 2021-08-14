package com.liyen.dojooverflow.services;

import org.springframework.stereotype.Service;

import com.liyen.dojooverflow.models.Answer;
import com.liyen.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {
	private final AnswerRepository answerRepository;
	
	public AnswerService(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}
	
	// create an answer
	public Answer createAnswer(Answer answer) {
		return answerRepository.save(answer);
	}
}
