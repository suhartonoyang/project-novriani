package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

}
