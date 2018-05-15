package br.com.projectmanager.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */

@Entity(name="projects")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Date plannedstart;

	private Date plannedend;
	
	private boolean filed;

	public Project() {
		
	}

	
	public Project(String name, Date plannedstart, Date plannedend, boolean filed) {
		super();
		this.name = name;
		this.plannedstart = plannedstart;
		this.plannedend = plannedend;
		this.filed = filed;
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


	public Date getPlannedstart() {
		return plannedstart;
	}


	public void setPlannedstart(Date plannedstart) {
		this.plannedstart = plannedstart;
	}


	public Date getPlannedend() {
		return plannedend;
	}


	public void setPlannedend(Date plannedend) {
		this.plannedend = plannedend;
	}


	public boolean isFiled() {
		return filed;
	}


	public void setFiled(boolean filed) {
		this.filed = filed;
	}


	
	
	
	
}
