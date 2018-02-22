package com.leverton.shortURL.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

import javax.persistence.Id;


@Entity
public class UrlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
	private Long id;
	 @Column(name = "full_url")
	private String fullUrl;
	 @Column(name = "short_url",unique=true)
		private String shortUrl;
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullUrl() {
		return fullUrl;
	}
	public void setFullUrl(String url) {
		this.fullUrl = url;
	}

}
