package net.visionvalley.iotservices.smac.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oauth_client_details")
public class Client {
	 @Id	   
	 	@Column(name="client_id")
	    private String clientId;
	 	@Column(name="resource_ids")
	    private String resourceIds;
	 	@Column(name="client_secret")
	 	private String clientSecret;
	 	@Column(name="scope")
	 	private String scope;
	 	@Column(name="authorized_grant_types")
	 	private String authorizedGrantTypes;
	 	private String authorities;
	 	@Column(name="access_token_validity")
	 	private String accessTokenValidity;
	 	@Column(name="refresh_token_validity")
	 	private String refreshTokenValidity;
	 	private String autoapprove;
	 	@Column(name="WEB_SERVER_REDIRECT_URI")
	 	private String webServerRedirectURI;
	 	@Column(name="ADDITIONAL_INFORMATION")
	 	private String additionalInformation;
		
		public String getClientId() {
			return clientId;
		}
		public void setClientId(String clientId) {
			this.clientId = clientId;
		}
		public String getResourceIds() {
			return resourceIds;
		}
		public void setResourceIds(String resourceIds) {
			this.resourceIds = resourceIds;
		}
		
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public String getAuthorizedGrantTypes() {
			return authorizedGrantTypes;
		}
		public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
			this.authorizedGrantTypes = authorizedGrantTypes;
		}
		public String getAuthorities() {
			return authorities;
		}
		public void setAuthorities(String authorities) {
			this.authorities = authorities;
		}
		public String getAccessTokenValidity() {
			return accessTokenValidity;
		}
		public void setAccessTokenValidity(String accessTokenValidity) {
			this.accessTokenValidity = accessTokenValidity;
		}
		public String getRefreshTokenValidity() {
			return refreshTokenValidity;
		}
		public void setRefreshTokenValidity(String refreshTokenValidity) {
			this.refreshTokenValidity = refreshTokenValidity;
		}
		public String getAutoapprove() {
			return autoapprove;
		}
		public void setAutoapprove(String autoapprove) {
			this.autoapprove = autoapprove;
		}
		public String getClientSecret() {
			return clientSecret;
		}
		public void setClientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
		}
		public String getWebServerRedirectURI() {
			return webServerRedirectURI;
		}
		public void setWebServerRedirectURI(String webServerRedirectURI) {
			this.webServerRedirectURI = webServerRedirectURI;
		}
		public String getAdditionalInformation() {
			return additionalInformation;
		}
		public void setAdditionalInformation(String additionalInformation) {
			this.additionalInformation = additionalInformation;
		}

}
