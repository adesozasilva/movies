package br.com.adesozasilva.movies.dataproviders;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.adesozasilva.movies.core.entities.Movie;


@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

	List<Movie> findBySaga(String saga);
	
}
