package com.nevinabradley.youtube_clone_mobile.repos;

import com.nevinabradley.youtube_clone_mobile.models.VideoPosting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoPostingRepo extends JpaRepository<VideoPosting, Long> {

}