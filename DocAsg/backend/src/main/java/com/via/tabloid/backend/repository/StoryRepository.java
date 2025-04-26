package com.via.tabloid.backend.repository;



import com.via.tabloid.backend.model.Story;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;



@Repository

public interface StoryRepository extends JpaRepository<Story, Long> {

    List<Story> findByDepartment(String department);

}
