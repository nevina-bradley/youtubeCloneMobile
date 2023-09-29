package com.nevinabradley.youtube_clone_mobile;
import static org.mockito.Mockito.*;
import com.nevinabradley.youtube_clone_mobile.controllers.VideoPostingController;
import com.nevinabradley.youtube_clone_mobile.services.VideoPostingService;
import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(VideoPostingController.class)
public class VideoPostingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoPostingService videoPostingService;

    @Test
    public void testGetAllVideos() throws Exception {
        List<VideoPosting> videos = new ArrayList<>();
        videos.add(new VideoPosting());
        videos.add(new VideoPosting());

        when(videoPostingService.getAllVideos()).thenReturn(videos);

        mockMvc.perform(get("/api/videos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(videos.size()));
    }

    @Test
    public void testGetVideoById() throws Exception {
        VideoPosting video = new VideoPosting();
        video.setId(1L);

        when(videoPostingService.getVideoById(1L)).thenReturn(video);

        mockMvc.perform(get("/api/videos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateVideo() throws Exception {
        VideoPosting video = new VideoPosting();
        video.setTitle("Test Video");

        when(videoPostingService.createVideo(any(VideoPosting.class))).thenReturn(video);

        mockMvc.perform(post("/api/videos")
                        .contentType("application/json")
                        .content("{}")) // Provide the JSON representation of the video
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Video"));
    }

    @Test
    public void testUpdateVideo() throws Exception {
        VideoPosting video = new VideoPosting();
        video.setId(1L);

        when(videoPostingService.updateVideo(eq(1L), any(VideoPosting.class))).thenReturn(video);

        mockMvc.perform(put("/api/videos/1")
                        .contentType("application/json")
                        .content("{}")) // Provide the JSON representation of the video
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteVideo() throws Exception {
        when(videoPostingService.deleteVideo(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/videos/1"))
                .andExpect(status().isNoContent());
    }
}
