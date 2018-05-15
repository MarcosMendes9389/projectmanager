package br.com.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectmanager.model.UserProject;

public interface UserProjectRepository extends CrudRepository<UserProject, Long>{

}
