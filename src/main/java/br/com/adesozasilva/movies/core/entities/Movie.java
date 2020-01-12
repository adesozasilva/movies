package br.com.adesozasilva.movies.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OrderColumn;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@DynamicUpdate
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("details")
	@Lob
	@Column(length=100000)
	private String details;
	
	@JsonProperty("created")
	private String created;
	
	@JsonProperty("director")
	private String director;
	
	@JsonProperty("edited")
	private String edited;

	@JsonProperty("producer")
	private String producer;
	
	@JsonProperty("release_date")
	private String releaseDate;
	
	@JsonProperty("saga")
	private String saga;
	
	@JsonIgnore
	@ElementCollection
    @CollectionTable(name = "movie_description_history", joinColumns = @JoinColumn(name = "movie_id"))
    @OrderColumn
	private List<MovieDescriptionHistory> movieDescriptionHistory = new ArrayList<>();
	
	@Version
	private Integer version;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSaga() {
		return saga;
	}

	public void setSaga(String saga) {
		this.saga = saga;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public void setMovieDescriptionHistory(MovieDescriptionHistory movieDescriptionHistory) {
		this.movieDescriptionHistory.add(movieDescriptionHistory);
	}
	
	public List<MovieDescriptionHistory> getMovieDescriptionHistory() {
		return movieDescriptionHistory;
	}
	
	@Override
	public String toString() {
		return description;
	}
	
}