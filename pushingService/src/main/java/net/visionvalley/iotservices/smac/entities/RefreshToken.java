package net.visionvalley.iotservices.smac.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="oauth_refresh_token")
public class RefreshToken {
	@Id
	@Column(name="token_id")
	private String tokenId;
	@Column(name="token")
	@Lob
	private Blob token;
	
	@Column(name="authentication")
	@Lob
	private Blob  authentication;
	
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	
	
	public Blob getToken() {
		return token;
	}
	public void setToken(Blob token) {
		this.token = token;
	}
	public Blob getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Blob authentication) {
		this.authentication = authentication;
	}

}
