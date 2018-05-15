package br.com.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectmanager.model.UserTeam;

public interface UserTeamRepository extends CrudRepository<UserTeam, Long>{

}
