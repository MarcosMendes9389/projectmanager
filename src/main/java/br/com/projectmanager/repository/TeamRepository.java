package br.com.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectmanager.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{

}
