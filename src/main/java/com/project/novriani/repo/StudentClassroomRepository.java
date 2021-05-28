package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.StudentClassroom;

@Repository
public interface StudentClassroomRepository extends CrudRepository<StudentClassroom, Long>{

}
