package com.via.tabloid.backend.service;



import com.via.tabloid.backend.model.Story;

import com.via.tabloid.backend.repository.StoryRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;



@Service

public class StoryService {

    

    private final StoryRepository storyRepository;

    

    @Autowired

    public StoryService(StoryRepository storyRepository) {

        this.storyRepository = storyRepository;

    }

    

    public List<Story> getAllStories() {

        return storyRepository.findAll();

    }

    

    public List<Story> getStoriesByDepartment(String department) {

        return storyRepository.findByDepartment(department);

    }

    

    public Story getStoryById(Long id) {

        return storyRepository.findById(id).orElse(null);

    }

    

    public Story createStory(Story story) {

        return storyRepository.save(story);

    }

    

    public Story updateStory(Long id, Story storyDetails) {

        Story story = storyRepository.findById(id).orElse(null);

        if (story != null) {

            story.setTitle(storyDetails.getTitle());

            story.setContent(storyDetails.getContent());

            story.setDepartment(storyDetails.getDepartment());

            return storyRepository.save(story);

        }

        return null;

    }

    

    public boolean deleteStory(Long id) {

        Story story = storyRepository.findById(id).orElse(null);

        if (story != null) {

            storyRepository.delete(story);

            return true;

        }

        return false;

    }

}
