package br.com.projectmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.Project;
import br.com.projectmanager.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectrepository;
	
	
	
	public Iterable<Project> getAll(){
		
		Iterable<Project> projects = projectrepository.findAll();
		return projects;
	}
	
	public Project getByNmae(String name){
		
		Iterable<Project> projects = projectrepository.findAll();
		for(Project project : projects){
			if(project.getName().equals(name)) return project;
		}
		
		return null;
	}
	
	
	public void save(Project project){
		
		projectrepository.save(project);

	}
	
}
