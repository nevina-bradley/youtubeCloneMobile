package com.nevinabradley.youtube_clone_mobile.controllers;

import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;
import com.nevinabradley.youtube_clone_mobile.services.VideoPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoPostingController {

    @Autowired
    private VideoPostingService videoPostingService;

    @GetMapping
    public ResponseEntity<List<VideoPosting>> getAllVideos() {
        List<VideoPosting> videos = videoPostingService.getAllVideos();
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideoPosting> getVideoById(@PathVariable Long id) {
        VideoPosting video = videoPostingService.getVideoById(id);
        if (video != null) {
            return new ResponseEntity<>(video, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VideoPosting> createVideo(@RequestBody VideoPosting video) {
        VideoPosting createdVideo = videoPostingService.createVideo(video);
        return new ResponseEntity<>(createdVideo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideoPosting> updateVideo(@PathVariable Long id, @RequestBody VideoPosting video) {
        VideoPosting updatedVideo = videoPostingService.updateVideo(id, video);
        if (updatedVideo != null) {
            return new ResponseEntity<>(updatedVideo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        boolean deleted = videoPostingService.deleteVideo(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
