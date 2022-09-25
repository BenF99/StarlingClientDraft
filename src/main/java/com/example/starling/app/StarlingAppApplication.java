package com.example.starling.app;

import com.example.starling.app.service.Starling;
import com.example.starling.app.service.StarlingBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class StarlingAppApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(StarlingAppApplication.class, args);
	}

	@Bean
	public Starling getStarlingClient(
			@Value("${STARLING_ACCESS_TOKEN:#{null}}") String starlingAccessToken
	) {
		return new StarlingBuilder().withAccessToken(starlingAccessToken).build();
	}

}



