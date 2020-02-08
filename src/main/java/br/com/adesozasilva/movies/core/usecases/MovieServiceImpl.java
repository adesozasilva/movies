package br.com.adesozasilva.movies.core.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.adesozasilva.movies.core.entities.MovieDescriptionHistory;
import br.com.adesozasilva.movies.core.entities.Movie;
import br.com.adesozasilva.movies.core.entities.MovieDetails;
import br.com.adesozasilva.movies.dataproviders.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public void updateDescription(Long id, String newDescription, String idRequester) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("There are no movie with this " + id));

		MovieDescriptionHistory movieDescriptionHistory = 
				new MovieDescriptionHistory(newDescription, movie.getDescription(), idRequester);
		movie.setDescription(newDescription);
		movie.setMovieDescriptionHistory(movieDescriptionHistory);
		movieRepository.save(movie);

	}

	@Override
	public List<Movie> findBySaga(String saga) {
		return movieRepository.findBySaga(saga);
	}

	@Override
	public Movie findById(Long id) {
		return movieRepository.findById(id).
				orElseThrow(() -> new IllegalArgumentException("There are no movie with this " + id));
	}

	@Override
	public MovieDetails showMovieDetails(Long id) {
		return new MovieDetails(findById(id).getDetails());
	}

}
