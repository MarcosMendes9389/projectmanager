package br.com.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectmanager.model.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

}
