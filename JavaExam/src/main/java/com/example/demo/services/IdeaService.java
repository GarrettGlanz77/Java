package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Idea;
import com.example.demo.repositories.IdeaRepository;



@Service
public class IdeaService {
	private final IdeaRepository ideaRepo;
	
	public IdeaService(IdeaRepository ideaRepo) {
		this.ideaRepo = ideaRepo;
	}
	
	public List<Idea> findAllIdea(){
		return ideaRepo.findAll();
	}
	
	public Idea createIdea(Idea idea) {
		return ideaRepo.save(idea);
	}
	
	public void updateIdea(Idea idea) {
		ideaRepo.save(idea);
	}
	
	public Idea findIdea(Long id) {
		Optional<Idea> myIdea = ideaRepo.findById(id);
		if (myIdea.isPresent()) {
			return myIdea.get();
		}else{
			return null;
		}
	}
	
	public void deleteIdea(Long id) {
		ideaRepo.deleteById(id);
	}
}