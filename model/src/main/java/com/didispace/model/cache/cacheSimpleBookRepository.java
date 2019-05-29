package com.didispace.model.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class cacheSimpleBookRepository {

	private void simulateSlowService(Long time) {
       try {
           Thread.sleep(time); 
       }catch(InterruptedException e) {
    	   throw new IllegalStateException(e);
       }		
	}
	
	@Cacheable(value = "book", key =  "'userCache'")     //加个 '' 代表是字符串，否则为变量
	public cacheBook getByIsbn(String isbn) {
		simulateSlowService(3000L);
		return new cacheBook(isbn,"Some book","xiaoer");
	}
	
	@CachePut(value = "book", key =  "'userCache'")      //加个 '' 代表是字符串，否则为变量
	public cacheBook getByIsbnTwo(String isbn) {
		simulateSlowService(4000L);
		return new cacheBook(isbn,"Some book","xiaosan");
	}
	
	 //清除缓存
    @CacheEvict(cacheNames = "book", allEntries = true)
    public void delect() {
    }

}
