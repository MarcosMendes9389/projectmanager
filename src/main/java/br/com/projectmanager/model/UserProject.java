package br.com.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Entity(name="user_project")
public class UserProject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id;
	
	private Long project_id;

	private Long role_id;


	public UserProject() {
		
	}

	
	public UserProject(Long user_id, Long project_id, Long role_id) {
		super();
		this.user_id = user_id;
		this.project_id = project_id;
		this.role_id = role_id;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public Long getProject_id() {
		return project_id;
	}


	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}


	public Long getRole_id() {
		return role_id;
	}


	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	
	
}
