package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.Enroll;

@Repository
public interface EnrollRepository extends CrudRepository<Enroll, Long>{

}
