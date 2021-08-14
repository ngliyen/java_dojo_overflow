package com.liyen.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.liyen.dojooverflow.models.Question;
import com.liyen.dojooverflow.models.Tag;
import com.liyen.dojooverflow.repositories.TagRepository;

@Service
public class TagService {
	private final TagRepository tagRepository;
	
	public TagService(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	// find all tags
	public List<Tag> allTags(){
		return tagRepository.findAll();
	}
	
	// create tag
	public Tag createTag(Tag tag) {
		return tagRepository.save(tag);
	}
	
	// show tag by subject
	public Tag findSubject(String subject) {
		Tag tag = tagRepository.findBySubject(subject);
		System.out.println("tag result");
		System.out.println(tag);
		return tag;
	}
}
