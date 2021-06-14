package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.Classroom;

@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long>{

	public Classroom findClassroomByClassroomName(String classroomName);
}
