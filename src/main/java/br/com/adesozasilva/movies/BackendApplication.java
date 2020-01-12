package br.com.adesozasilva.movies;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adesozasilva.movies.core.entities.Movie;
import br.com.adesozasilva.movies.dataproviders.MovieRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = MovieRepository.class)
@EntityScan(basePackageClasses = Movie.class)
@ComponentScan(basePackageClasses = BackendApplication.class)
@Configuration
public class BackendApplication {

	private Logger log = LoggerFactory.getLogger(BackendApplication.class);

	@Bean
	public CommandLineRunner demo(MovieRepository repository) {
		return (args) -> {

			log.info("Persistindo filmes...");
			Resource resource = new ClassPathResource("movies.json");
			List<Movie> movies = new ObjectMapper().readValue(resource.getInputStream(), new TypeReference<List<Movie>>(){});

			movies.forEach(movie -> {
				repository.save(movie);
			});
			log.info("Persistido filmes");
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
