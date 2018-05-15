package br.com.projectmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projectmanager.model.Team;
import br.com.projectmanager.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamrepository;
	
	
	
	public Iterable<Team> getAll(){
		
		Iterable<Team> teams = teamrepository.findAll();
		return teams;
	}
	
	
	public void save(Team team){
		
		teamrepository.save(team);

	}
	
}
