package com.via.tabloid.backend.controller;

import com.via.tabloid.backend.model.Story;
import com.via.tabloid.backend.repository.StoryRepository;
import com.via.tabloid.backend.service.StoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoryServiceTest {

    @Mock
    private StoryRepository storyRepository;

    @InjectMocks
    private StoryService storyService;

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
        when(storyRepository.findAll()).thenReturn(mockStories);

        // Act
        List<Story> result = storyService.getAllStories();

        // Assert
        assertEquals(2, result.size());
        verify(storyRepository, times(1)).findAll();
    }

    @Test
    void testGetStoryById_Found() {
        // Arrange
        Story mockStory = new Story("Title1", "Content1", "IT");
        when(storyRepository.findById(1L)).thenReturn(Optional.of(mockStory));

        // Act
        Story result = storyService.getStoryById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Title1", result.getTitle());
        verify(storyRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStoryById_NotFound() {
        // Arrange
        when(storyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Story result = storyService.getStoryById(1L);

        // Assert
        assertNull(result);
        verify(storyRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateStory() {
        // Arrange
        Story newStory = new Story("Title1", "Content1", "IT");
        when(storyRepository.save(newStory)).thenReturn(newStory);

        // Act
        Story result = storyService.createStory(newStory);

        // Assert
        assertNotNull(result);
        assertEquals("Title1", result.getTitle());
        verify(storyRepository, times(1)).save(newStory);
    }

    @Test
    void testUpdateStory_Found() {
        // Arrange
        Story existingStory = new Story("Title1", "Content1", "IT");
        Story updatedStoryDetails = new Story("Updated Title", "Updated Content", "Business");
        when(storyRepository.findById(1L)).thenReturn(Optional.of(existingStory));
        when(storyRepository.save(existingStory)).thenReturn(existingStory);

        // Act
        Story result = storyService.updateStory(1L, updatedStoryDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
        verify(storyRepository, times(1)).findById(1L);
        verify(storyRepository, times(1)).save(existingStory);
    }

    @Test
    void testUpdateStory_NotFound() {
        // Arrange
        Story updatedStoryDetails = new Story("Updated Title", "Updated Content", "Business");
        when(storyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Story result = storyService.updateStory(1L, updatedStoryDetails);

        // Assert
        assertNull(result);
        verify(storyRepository, times(1)).findById(1L);
        verify(storyRepository, never()).save(any());
    }

    @Test
    void testDeleteStory_Found() {
        // Arrange
        Story existingStory = new Story("Title1", "Content1", "IT");
        when(storyRepository.findById(1L)).thenReturn(Optional.of(existingStory));

        // Act
        boolean result = storyService.deleteStory(1L);

        // Assert
        assertTrue(result);
        verify(storyRepository, times(1)).findById(1L);
        verify(storyRepository, times(1)).delete(existingStory);
    }

    @Test
    void testDeleteStory_NotFound() {
        // Arrange
        when(storyRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        boolean result = storyService.deleteStory(1L);

        // Assert
        assertFalse(result);
        verify(storyRepository, times(1)).findById(1L);
        verify(storyRepository, never()).delete(any());
    }
}