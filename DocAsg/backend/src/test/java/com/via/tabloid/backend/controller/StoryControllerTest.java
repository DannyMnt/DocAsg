package com.via.tabloid.backend.controller;

import com.via.tabloid.backend.model.Story;
import com.via.tabloid.backend.service.StoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoryControllerTest {

    @Mock
    private StoryService storyService;

    @InjectMocks
    private StoryController storyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStories() {
        // Arrange
        List<Story> mockStories = Arrays.asList(
                new Story("Title1", "Content1", "IT"),
                new Story("Title2", "Content2", "Business")
        );
        when(storyService.getAllStories()).thenReturn(mockStories);

        // Act
        ResponseEntity<List<Story>> response = storyController.getAllStories();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(storyService, times(1)).getAllStories();
    }

    @Test
    void testGetStoryById_Found() {
        // Arrange
        Story mockStory = new Story("Title1", "Content1", "IT");
        when(storyService.getStoryById(1L)).thenReturn(mockStory);

        // Act
        ResponseEntity<Story> response = storyController.getStoryById(1L);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Title1", response.getBody().getTitle());
        verify(storyService, times(1)).getStoryById(1L);
    }

    @Test
    void testGetStoryById_NotFound() {
        // Arrange
        when(storyService.getStoryById(1L)).thenReturn(null);

        // Act
        ResponseEntity<Story> response = storyController.getStoryById(1L);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(storyService, times(1)).getStoryById(1L);
    }

    @Test
    void testCreateStory() {
        // Arrange
        Story newStory = new Story("Title1", "Content1", "IT");
        when(storyService.createStory(newStory)).thenReturn(newStory);

        // Act
        ResponseEntity<Story> response = storyController.createStory(newStory);

        // Assert
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Title1", response.getBody().getTitle());
        verify(storyService, times(1)).createStory(newStory);
    }

    @Test
    void testUpdateStory_Found() {
        // Arrange
        Story updatedStory = new Story("Updated Title", "Updated Content", "IT");
        when(storyService.updateStory(1L, updatedStory)).thenReturn(updatedStory);

        // Act
        ResponseEntity<Story> response = storyController.updateStory(1L, updatedStory);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Updated Title", response.getBody().getTitle());
        verify(storyService, times(1)).updateStory(1L, updatedStory);
    }

    @Test
    void testUpdateStory_NotFound() {
        // Arrange
        Story updatedStory = new Story("Updated Title", "Updated Content", "IT");
        when(storyService.updateStory(1L, updatedStory)).thenReturn(null);

        // Act
        ResponseEntity<Story> response = storyController.updateStory(1L, updatedStory);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(storyService, times(1)).updateStory(1L, updatedStory);
    }

    @Test
    void testDeleteStory_Found() {
        // Arrange
        when(storyService.deleteStory(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = storyController.deleteStory(1L);

        // Assert
        assertEquals(204, response.getStatusCodeValue());
        verify(storyService, times(1)).deleteStory(1L);
    }

    @Test
    void testDeleteStory_NotFound() {
        // Arrange
        when(storyService.deleteStory(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = storyController.deleteStory(1L);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(storyService, times(1)).deleteStory(1L);
    }
}