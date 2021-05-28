package de.andreas.glaser.siteCrawlerBatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SiteCrawlerBatchApplication {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(SiteCrawlerBatchApplication.class, args)));
	}

}
