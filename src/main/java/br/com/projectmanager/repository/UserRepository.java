package br.com.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.projectmanager.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
