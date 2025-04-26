package com.via.tabloid.backend.controller;
import com.via.tabloid.backend.model.Story;
import com.via.tabloid.backend.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

@RequestMapping("/api/stories")

@CrossOrigin(origins = "*")

public class StoryController {

    

    private final StoryService storyService;

    

    @Autowired

    public StoryController(StoryService storyService) {

        this.storyService = storyService;

    }

    

    @GetMapping

    public ResponseEntity<List<Story>> getAllStories() {

        return new ResponseEntity<>(storyService.getAllStories(), HttpStatus.OK);

    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    

    @GetMapping("/department/{department}")

    public ResponseEntity<List<Story>> getStoriesByDepartment(@PathVariable String department) {

        return new ResponseEntity<>(storyService.getStoriesByDepartment(department), HttpStatus.OK);

    }

    

    @GetMapping("/{id}")

    public ResponseEntity<Story> getStoryById(@PathVariable Long id) {

        Story story = storyService.getStoryById(id);

        if (story != null) {

            return new ResponseEntity<>(story, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    

    @PostMapping

    public ResponseEntity<Story> createStory(@RequestBody Story story) {

        return new ResponseEntity<>(storyService.createStory(story), HttpStatus.CREATED);

    }

    

    @PutMapping("/{id}")

    public ResponseEntity<Story> updateStory(@PathVariable Long id, @RequestBody Story storyDetails) {

        Story updatedStory = storyService.updateStory(id, storyDetails);

        if (updatedStory != null) {

            return new ResponseEntity<>(updatedStory, HttpStatus.OK);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {

        boolean deleted = storyService.deleteStory(id);

        if (deleted) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
