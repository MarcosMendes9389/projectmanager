package br.com.projectmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.User;
import br.com.projectmanager.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepository;
	
	
	
	public Iterable<User> getAll(){
		
		Iterable<User> users = userrepository.findAll();
		return users;
	}
	
	

	
	public User getById(Long Id){
		
		 Optional<User> user = userrepository.findById(Id);
		 return user.get();
	}
	
	
	public User getByNmae(String name){
		
		Iterable<User> users = userrepository.findAll();
		for(User user : users){
			if(user.getName().equals(name)) return user;
		}
		
		return null;
	}
	
	
	
	public void save(User user){
		
		userrepository.save(user);

	}
	
}
