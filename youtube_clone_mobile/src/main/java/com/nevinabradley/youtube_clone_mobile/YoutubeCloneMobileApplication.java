package com.nevinabradley.youtube_clone_mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nevinabradley.youtube_clone_mobile.models")
public class YoutubeCloneMobileApplication {

	public static void main(String[] args) {
		SpringApplication.run(YoutubeCloneMobileApplication.class, args);
	}

}
