package net.visionvalley.iotservices.smac.entities;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="oauth_access_token")
public class AccessToken {
	@Id
	@Column(name="token_id")
	private String tokenId;
	@Column(name="token")
	@Lob
	private Blob token;
	@Column(name="authentication_id")
	private String authenticationId;
	@Column(name="user_name")
	private String username;
	@Column(name="client_id")
	private String clientId;
	@Column(name="authentication")
	@Lob
	private Blob  authentication;
	@Column(name="refresh_token")
	private String refreshToken;
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
	public String getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
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
