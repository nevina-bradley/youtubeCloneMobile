package com.nevinabradley.youtube_clone_mobile;

import com.nevinabradley.youtube_clone_mobile.services.VideoPostingService;
import com.nevinabradley.youtube_clone_mobile.repos.VideoPostingRepo;
import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.nevinabradley.youtube_clone_mobile.services.VideoPostingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VideoPostingServiceImplTest {

    @InjectMocks
    private VideoPostingServiceImpl videoPostingService;

    @Mock
    private VideoPostingRepo videoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllVideos() {
        List<VideoPosting> videos = new ArrayList<>();
        videos.add(new VideoPosting());
        videos.add(new VideoPosting());

        when(videoRepository.findAll()).thenReturn(videos);

        List<VideoPosting> result = videoPostingService.getAllVideos();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetVideoById() {
        VideoPosting video = new VideoPosting();
        video.setId(1L);

        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));

        VideoPosting result = videoPostingService.getVideoById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testCreateVideo() {
        VideoPosting video = new VideoPosting();
        video.setTitle("Test Video");

        when(videoRepository.save(any(VideoPosting.class))).thenReturn(video);

        VideoPosting result = videoPostingService.createVideo(video);

        assertNotNull(result);
        assertEquals("Test Video", result.getTitle());
    }

    @Test
    public void testUpdateVideo() {
        VideoPosting existingVideo = new VideoPosting();
        existingVideo.setId(1L);

        VideoPosting updatedVideo = new VideoPosting();
        updatedVideo.setId(1L);
        updatedVideo.setTitle("Updated Video");

        when(videoRepository.findById(1L)).thenReturn(Optional.of(existingVideo));
        when(videoRepository.save(any(VideoPosting.class))).thenReturn(updatedVideo);

        VideoPosting result = videoPostingService.updateVideo(1L, updatedVideo);

        assertNotNull(result);
        assertEquals("Updated Video", result.getTitle());
    }

    @Test
    public void testDeleteVideo() {
        VideoPosting video = new VideoPosting();
        video.setId(1L);

        when(videoRepository.findById(1L)).thenReturn(Optional.of(video));

        boolean result = videoPostingService.deleteVideo(1L);

        assertTrue(result);
        verify(videoRepository, times(1)).delete(video);
    }
}
