package com.leverton.shortURL.repostriy;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leverton.shortURL.entity.UrlEntity;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long>{
	
	    Optional<UrlEntity> findById(
	                 Long id);
	    Optional<UrlEntity> findByShortUrl(
	    		String shortUrl);
	    Optional<UrlEntity> findByFullUrl(
                String fullUrl);

}
