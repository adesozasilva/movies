package br.com.adesozasilva.movies.core.usecases;

import java.util.List;

import br.com.adesozasilva.movies.core.entities.Movie;
import br.com.adesozasilva.movies.core.entities.MovieDetails;

public interface MovieService {

	void updateDescription(Long id, String newDescription, String idRequester);

	List<Movie> findBySaga(String saga);

	Movie findById(Long id);

	MovieDetails showMovieDetails(Long id);

}
