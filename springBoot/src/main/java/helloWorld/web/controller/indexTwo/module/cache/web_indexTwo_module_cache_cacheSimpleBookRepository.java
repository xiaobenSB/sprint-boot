package helloWorld.web.controller.indexTwo.module.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class web_indexTwo_module_cache_cacheSimpleBookRepository {

	private void simulateSlowService(Long time) {
       try {
           Thread.sleep(time); 
       }catch(InterruptedException e) {
    	   throw new IllegalStateException(e);
       }		
	}
	
	@Cacheable(value = "book", key =  "'userCache'")     
	public web_indexTwo_module_cache_cacheBook getByIsbn(String isbn) {
		simulateSlowService(3000L);
		return new web_indexTwo_module_cache_cacheBook(isbn,"Some book","xiaoer");
	}
	
	@CachePut(value = "book", key =  "'userCache'")     
	public web_indexTwo_module_cache_cacheBook getByIsbnTwo(String isbn) {
		simulateSlowService(4000L);
		return new web_indexTwo_module_cache_cacheBook(isbn,"Some book","xiaosan");
	}
	
	@Cacheable(value = "book", key =  "'userCache2'")     
	public web_indexTwo_module_cache_cacheBook getByIsbnThree(String isbn) {
		simulateSlowService(3000L);
		return new web_indexTwo_module_cache_cacheBook(isbn,"Some book","xiaosi");
	}
	
	@CachePut(value = "book", key =  "'userCache2'")     
	public web_indexTwo_module_cache_cacheBook getByIsbnFour(String isbn) {
		simulateSlowService(4000L);
		return new web_indexTwo_module_cache_cacheBook(isbn,"Some book","xiaowu");
	}
	

    @CacheEvict(cacheNames = "book", allEntries = true)
    public void delect() {
    }

}
