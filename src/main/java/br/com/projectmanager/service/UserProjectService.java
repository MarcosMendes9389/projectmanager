package br.com.projectmanager.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.UserProject;
import br.com.projectmanager.repository.UserProjectRepository;

@Service
public class UserProjectService {

	@Autowired
	private UserProjectRepository userprojectrepository;
	
	
	
	public Iterable<UserProject> getAll(){
		
		Iterable<UserProject> userprojects = userprojectrepository.findAll();
		return userprojects;
	}
	
	
	public ArrayList<UserProject> getByProjectId(Long project_id){
		
		ArrayList<UserProject> userprojects = new ArrayList<UserProject>(); 
		
		for(UserProject userproject: userprojectrepository.findAll() ){
			
			if(userproject.getProject_id() == project_id){
				userprojects.add(userproject);
			}
		}
		
		return userprojects;
	}
	
	
	
	public void save(UserProject userproject){
		
		userprojectrepository.save(userproject);

	}
	
}
