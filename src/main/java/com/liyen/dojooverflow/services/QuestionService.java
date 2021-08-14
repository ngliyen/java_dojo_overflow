package com.liyen.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liyen.dojooverflow.models.Question;

import com.liyen.dojooverflow.repositories.QuestionRepository;

@Service
public class QuestionService {
	private final QuestionRepository questionRepository;
	
	public QuestionService(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	// find all questions
	public List<Question> allQuestions(){
		return questionRepository.findAll();
	}
	
	
	// show question by id
	public Question findQuestion(Long id) {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		} else {
			return null;
		}
	}
	
	// create a question
	public Question createQuestion(Question question) {
		return questionRepository.save(question);
	}
}
