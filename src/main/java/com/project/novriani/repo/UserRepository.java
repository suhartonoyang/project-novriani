package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	public User findByUsername(String username);
	
}
