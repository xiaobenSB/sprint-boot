package helloWorld.web.controller.indexTwo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import helloWorld.web.controller.indexTwo.module.cache.web_indexTwo_module_cache_cacheBook;
import helloWorld.web.controller.indexTwo.module.cache.web_indexTwo_module_cache_cacheSimpleBookRepository;


@RequestMapping(value = "/indexTwo")
@RestController
public class web_indexTwo_api_index {
	
	private static final Logger logger = LoggerFactory.getLogger(web_indexTwo_api_index.class);
	@Autowired
	web_indexTwo_module_cache_cacheSimpleBookRepository bookRepository;
  
	/*
	   *                      缓存测试
	 * */
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi5")
	public web_indexTwo_module_cache_cacheBook ceshiFive() {
		logger.info(".... Fetching books");
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbn("isbn-1234"); 
	}
	
	/*
	   *                      缓存更新
	 * */
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi5Two")
	public web_indexTwo_module_cache_cacheBook ceshiFiveTwo() {
		logger.info(".... Fetching books");
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
      logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbnTwo("isbn-1234"); 
	}
	
	/*
	   *                      缓存测试
	 * */
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi6")
	public web_indexTwo_module_cache_cacheBook ceshisix() {
		logger.info(".... Fetching books");
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbnThree("isbn-1234"); 
	}
	
	/*
	   *                      缓存更新
	 * */
	@CrossOrigin(origins = "*")
	@RequestMapping("/ceshi6Two")
	public web_indexTwo_module_cache_cacheBook ceshisixTwo() {
		logger.info(".... Fetching books");
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
    logger.info("isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		return bookRepository.getByIsbnFour("isbn-1234"); 
	}
}
