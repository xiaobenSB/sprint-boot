package com.didispace.model.async;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service  
public class asyncGitHubLookupService {
	 private static final Logger logger = LoggerFactory.getLogger(asyncGitHubLookupService.class);

	    private final RestTemplate restTemplate;

	    public asyncGitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
	        this.restTemplate = restTemplateBuilder.build();
	    }

	    @Async    //使用这个会把下面的方法拉到并发池里
	    public CompletableFuture<asyncUser> findUser(String user) throws InterruptedException {
	        logger.info("Looking up " + user);
	        String url = String.format("https://api.github.com/users/%s", user);
	        asyncUser results = restTemplate.getForObject(url, asyncUser.class);
	        logger.info("Looking upTwo " + user);
	        // Artificial delay of 1s for demonstration purposes
	        Thread.sleep(1000L);
	        logger.info("Looking upThree " + results);
	        return CompletableFuture.completedFuture(results);
	    }
}
