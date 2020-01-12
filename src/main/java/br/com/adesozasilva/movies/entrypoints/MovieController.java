package br.com.adesozasilva.movies.entrypoints;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adesozasilva.movies.core.usecases.MovieService;

@RestController	
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@PatchMapping("/movie/{id}")
	public ResponseEntity<?> updateDescription(
			@RequestBody DescriptionRequest descriptionRequest,
			@PathVariable("id") Long id, HttpServletRequest request) {
		
	    movieService.updateDescription(id, descriptionRequest.getDescription(), request.getRemoteAddr());
	    
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/movies")
	public ResponseEntity<?> findMoviesBySaga(@RequestParam("saga") String saga) {
	    return ResponseEntity.ok().body(movieService.findBySaga(saga));
	}
	
	@GetMapping("/movie/{id}/details")
	public ResponseEntity<?> showMovieDetails(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(movieService.showMovieDetails(id));
	}
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<?> showMovieInfo(@PathVariable("id") Long id){
		return ResponseEntity.ok().body(movieService.findById(id));
	}

}
