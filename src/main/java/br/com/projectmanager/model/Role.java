package br.com.projectmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Entity(name="roles")
public class Role {

	
	public static final Long manager = (long) 1;
	public static final Long employee = (long) 2;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	

	public Role() {
		
	}

	
	public Role(String name) {
		super();
		this.name = name;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	
	
}
