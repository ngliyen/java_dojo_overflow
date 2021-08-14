package com.liyen.dojooverflow.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liyen.dojooverflow.models.Answer;
import com.liyen.dojooverflow.models.Question;
import com.liyen.dojooverflow.models.Tag;
import com.liyen.dojooverflow.services.AnswerService;
import com.liyen.dojooverflow.services.QuestionService;
import com.liyen.dojooverflow.services.TagService;

@Controller
public class DojoController {
	private final QuestionService questionService;
	private final TagService tagService;
	private final AnswerService answerService;

	public DojoController(QuestionService questionService, TagService tagService, AnswerService answerService) {
		this.questionService = questionService;
		this.tagService = tagService;
		this.answerService = answerService;
	}
	
	@GetMapping("/questions")
	public String dashboard(Model model) {
		List<Question> questions = questionService.allQuestions();
		model.addAttribute("questions", questions);
		return "/questions/dashboard.jsp";
	}
	
	// page to create new question
	@GetMapping("/questions/new")
	public String newQuestion() {
		return "/questions/new.jsp";
	}
	
	// create a new question and new tags (as necessary)
	@PostMapping("/questions")
	public String createQuestion(@RequestParam(value="question") String question, @RequestParam(value="tags") String tags, RedirectAttributes redirectAttributes) {
		String[] tagList = tags.trim().split("\\s*,\\s*");
		// validate input
		if (question.isEmpty() | tags.isEmpty() | tagList.length > 3) {
			// check if question is empty
			if (question.isEmpty()) {
				redirectAttributes.addFlashAttribute("question_error", "Question cannot be blank!");
			}
			// check if tags is empty
//			if (tags.trim().length() == 0) {
			if (tags.isEmpty()) {
				redirectAttributes.addFlashAttribute("tag_error", "Please provide at least one tag!");
			// check if there are more than 3 tags
			} else if (tagList.length > 3) {
				redirectAttributes.addFlashAttribute("tag_error", "Only a maximum of 3 tags allowed per question!");
			}
			return "redirect:/questions/new";
		} else {
			// create a question object
			Question newQuestion = new Question();
			
			// add question to the object
			newQuestion.setQuestion(question);
			
			// create a list of tags
			List<Tag> newTagList = new ArrayList<Tag>();
			for (String tag : tagList) {
				// check whether tag exist
				if (tagService.findSubject(tag) != null) {
					newTagList.add(tagService.findSubject(tag));
				} else {  // if not create tag
					Tag newTag = new Tag();
					newTag.setSubject(tag);
					tagService.createTag(newTag);
					newTagList.add(newTag);
				}
			}
			// add the list of tags to the question object
			newQuestion.setTags(newTagList);
			// create the question
			questionService.createQuestion(newQuestion);
			return "redirect:/questions";
		}		
	}
	
	// show question by id, allow user to add answers
	@GetMapping("/questions/{id}")
	public String showQuestion(@PathVariable("id") Long question_id, Model model, @ModelAttribute("ans") Answer ans) {
		Question question = questionService.findQuestion(question_id);
		model.addAttribute("question",question);
		return "/questions/show.jsp";
	}
	
	// process and save user answers
	@PostMapping("/answers/{id}")
	public String createAnswer(@Valid @ModelAttribute("ans") Answer ans, BindingResult result, @PathVariable("id") Long question_id, Model model) {
		Question question = questionService.findQuestion(question_id);
		if (result.hasErrors()) {
			model.addAttribute("question",question);
			return "/questions/show.jsp";
		} else {
			Answer newAnswer = new Answer();
			newAnswer.setAnswer(ans.getAnswer());
			newAnswer.setQuestion(question);
			answerService.createAnswer(newAnswer);
			return "redirect:/questions/" + question_id;
		}
	}
}
