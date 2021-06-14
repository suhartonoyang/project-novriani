package com.project.novriani.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.novriani.model.Lesson;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long>{

	public Lesson findLessonByLessonName(String lessonName);
}
