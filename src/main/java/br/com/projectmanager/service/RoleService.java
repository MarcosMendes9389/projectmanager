package br.com.projectmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.Role;
import br.com.projectmanager.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository rolerepository;
	
	
	
	public Iterable<Role> getAll(){
		
		Iterable<Role> roles = rolerepository.findAll();
		return roles;
	}
	
	
	public Role getById(Long Id){
		
		 Optional<Role> role = rolerepository.findById(Id);
		
		 return role.get();
	}
	
	
	
	
	public void save(Role role){
		
		rolerepository.save(role);

	}
	
}
