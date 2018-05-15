package br.com.projectmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.UserTeam;
import br.com.projectmanager.repository.UserTeamRepository;

@Service
public class UserTeamService {

	@Autowired
	private UserTeamRepository userteamrepository;
	
	
	
	public Iterable<UserTeam> getAll(){
		
		Iterable<UserTeam> userteams = userteamrepository.findAll();
		return userteams;
	}
	
	
	public void save(UserTeam userteam){
		
		userteamrepository.save(userteam);

	}
	
}
