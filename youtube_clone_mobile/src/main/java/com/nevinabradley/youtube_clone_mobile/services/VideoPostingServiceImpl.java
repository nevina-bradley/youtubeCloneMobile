package com.nevinabradley.youtube_clone_mobile.services;

import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;
import com.nevinabradley.youtube_clone_mobile.repos.VideoPostingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoPostingServiceImpl implements VideoPostingService {

    @Autowired
    private VideoPostingRepo videoRepository;

    @Override
    public List<VideoPosting> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public VideoPosting getVideoById(Long id) {
        Optional<VideoPosting> optionalVideo = videoRepository.findById(id);
        return optionalVideo.orElse(null);
    }

    @Override
    public VideoPosting createVideo(VideoPosting video) {
        return videoRepository.save(video);
    }

    @Override
    public VideoPosting updateVideo(Long id, VideoPosting video) {
        Optional<VideoPosting> optionalExistingVideo = videoRepository.findById(id);
        if (optionalExistingVideo.isPresent()) {
            VideoPosting existingVideo = optionalExistingVideo.get();
            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            // Update other properties as needed
            return videoRepository.save(existingVideo);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteVideo(Long id) {
        Optional<VideoPosting> optionalVideo = videoRepository.findById(id);
        if (optionalVideo.isPresent()) {
            videoRepository.delete(optionalVideo.get());
            return true;
        } else {
            return false;
        }
    }
}