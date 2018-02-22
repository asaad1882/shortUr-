package com.leverton.shortURL.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leverton.shortURL.dto.UrlDTO;
import com.leverton.shortURL.entity.UrlEntity;
import com.leverton.shortURL.repostriy.UrlRepository;
import com.leverton.shortURL.utilities.RandomNumberGenerator;
import com.leverton.shortURL.utilities.UrlGenerator;

@Service
public class UrlService {
	@Autowired
    private UrlRepository urlRepository;
	private static final String domain="http://leverton.com";
	
    public UrlDTO getURL(String shortUrl) {
    	UrlDTO dto = new UrlDTO();  
    	Optional<UrlEntity> urlShortDb = urlRepository.findByShortUrl(shortUrl);          
            if(urlShortDb.isPresent()) {               
                UrlEntity url = urlShortDb.get();
                dto.setId(url.getId());
                dto.setShortUrl(shortUrl);
                dto.setFullUrl(url.getFullUrl());                
            }        
        return dto;
    }

	public UrlDTO addUrl(String fullURL) {
		UrlEntity urlEntity = new UrlEntity();
		fullURL = sanitizeURL(fullURL);
		Optional<UrlEntity> exitURL = urlRepository.findByFullUrl(fullURL);
		if (exitURL.isPresent()) {
			urlEntity = exitURL.get();
		} else {
			urlEntity.setFullUrl(fullURL);
			urlEntity.setShortUrl(this.domain + "/" + UrlGenerator
					.generateShortUrl(RandomNumberGenerator.getRandonNumber(fullURL.length(), fullURL.length() * 3)));
			urlEntity = urlRepository.save(urlEntity);
		}
		return mapToDTO(urlEntity);
	}
	public UrlDTO updateUrl(String fullURL) {
		UrlEntity urlEntity = new UrlEntity();
		fullURL = sanitizeURL(fullURL);
		Optional<UrlEntity> exitURL = urlRepository.findByFullUrl(fullURL);
		if (exitURL.isPresent()) {
			urlEntity = exitURL.get();
			urlEntity.setShortUrl(this.domain + "/" + UrlGenerator
					.generateShortUrl(RandomNumberGenerator.getRandonNumber(fullURL.length(), fullURL.length() * 3)));
		} 
		return mapToDTO(urlEntity);
	}
    public void deleteUrl(String shortUrl){
    	Optional<UrlEntity> urlShortDb = urlRepository.findByShortUrl(shortUrl);
    	 if(urlShortDb.isPresent()) {
    		 UrlEntity url = urlShortDb.get();
    	   urlRepository.delete(url.getId());
    	 }
    }


private String sanitizeURL(String url) {
    if (url.substring(0, 7).equals("http://"))
        url = url.substring(7);

    if (url.substring(0, 8).equals("https://"))
        url = url.substring(8);

    if (url.charAt(url.length() - 1) == '/')
        url = url.substring(0, url.length() - 1);
    return url;
}
    private UrlDTO mapToDTO(UrlEntity urlEntity) {
       
    	UrlDTO urlDTO = new UrlDTO();
    	urlDTO.setId(urlEntity.getId());
    	urlDTO.setFullUrl(urlEntity.getFullUrl());      
        
        
        urlDTO.setShortUrl(urlEntity.getShortUrl());
        return urlDTO;
    }
}
