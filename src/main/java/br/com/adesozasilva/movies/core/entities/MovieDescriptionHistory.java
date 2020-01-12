package br.com.adesozasilva.movies.core.entities;

import javax.persistence.Embeddable;

@Embeddable
public class MovieDescriptionHistory {
	
	private String newDescription;
	private String oldDescription;
	private String requestIp;
	
	public MovieDescriptionHistory() {}
	
	public MovieDescriptionHistory(String newDescription, String oldDescription, String requestIp) {
		this.newDescription = newDescription;
		this.oldDescription = oldDescription;
		this.requestIp = requestIp;
	}
	
	public String getNewDescription() {
		return newDescription;
	}
	
	public String getOldDescription() {
		return oldDescription;
	}
	
	public String getRequestIp() {
		return requestIp;
	}
}
