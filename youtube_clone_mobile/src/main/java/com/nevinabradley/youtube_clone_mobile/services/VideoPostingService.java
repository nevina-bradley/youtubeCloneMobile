package com.nevinabradley.youtube_clone_mobile.services;

import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;
import java.util.List;

public interface VideoPostingService {

    List<VideoPosting> getAllVideos();

    VideoPosting getVideoById(Long id);

    VideoPosting createVideo(VideoPosting video);

    VideoPosting updateVideo(Long id, VideoPosting video);

    boolean deleteVideo(Long id);
}