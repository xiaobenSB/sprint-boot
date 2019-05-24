package com.didispace.model.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class cacheSimpleBookRepository {

	private void simulateSlowService() {
       try {
    	   long time = 3000L;
           Thread.sleep(time); 
       }catch(InterruptedException e) {
    	   throw new IllegalStateException(e);
       }		
	}
	
	@Cacheable("books")
	public cacheBook getByIsbn(String isbn) {
		simulateSlowService();
		return new cacheBook(isbn,"Some book");
	}

}
