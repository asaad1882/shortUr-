package com.leverton.shortURL.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leverton.shortURL.dto.UrlDTO;
import com.leverton.shortURL.service.UrlService;

@RestController
@RequestMapping("/leverton")
public class Api {
	@Autowired
	 private UrlService urlService;
	 	/**
	 	 * Method to generate short URL and add full and short to database
	 	 * @param fullUrl
	 	 * @return
	 	 */
	    @RequestMapping(value = "/url",
	            method = RequestMethod.POST
	            )
	    @ResponseStatus
	    @ResponseBody
	    public UrlDTO addUrl(@RequestParam(value = "fullUrl") String fullUrl) {
	        return urlService.addUrl(fullUrl);
	    }
	    /**
	     * Method to generate short URL and update short url for given url 
	     * @param fullUrl
	     * @return
	     */
	    @RequestMapping(value = "/url",
	            method = RequestMethod.PUT
	            )
	    @ResponseStatus
	    @ResponseBody
	    public UrlDTO updateUrl(@RequestParam(value = "fullUrl") String fullUrl) {
	        return urlService.updateUrl(fullUrl);
	    }
	    /**
	     * delete short url
	     * @param shortUrl
	     */
	    @RequestMapping(value = "/url",
	            method = RequestMethod.DELETE
	            )
	    @ResponseStatus
	    @ResponseBody
	    public void deleteUrl(@RequestParam(value = "shortUrl") String shortUrl) {
	        urlService.deleteUrl(shortUrl);
	    }
	    /**
	     * get short url
	     * @param shortUrl
	     * @return
	     */
	     
	    @RequestMapping(value = "/url", method = RequestMethod.GET)
	    @ResponseStatus
	    @ResponseBody
	    public UrlDTO getURL(@RequestParam(value = "shortUrl") String shortUrl) {
	        return urlService.getURL(shortUrl);
	    }
	    @RequestMapping(value = "/urlForward", method = RequestMethod.GET)
	    @ResponseStatus
	    @ResponseBody
	    public void getURL(@RequestParam(value = "shortUrl") String shortUrl,HttpServletResponse response) throws IOException {
	    	response.sendRedirect( urlService.getURL(shortUrl).getFullUrl());
	    	
	    }

}
