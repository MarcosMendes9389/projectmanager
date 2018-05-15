package br.com.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Entity(name="user_team")
public class UserTeam {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id;
	 
	private Long team_id;
	

	public UserTeam() {
		
	}

	
	public UserTeam(Long user_id, Long team_id) {
		super();
		this.user_id = user_id;
		this.team_id = team_id;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Long getTeam_id() {
		return team_id;
	}


	public void setTeam_id(Long team_id) {
		this.team_id = team_id;
	}


	
	
}
