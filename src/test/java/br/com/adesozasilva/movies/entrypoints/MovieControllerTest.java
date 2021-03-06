package br.com.adesozasilva.movies.entrypoints;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adesozasilva.movies.MovieApplication;
import br.com.adesozasilva.movies.core.entities.Movie;
import br.com.adesozasilva.movies.core.entities.MovieDetails;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MovieApplication.class)
@WebAppConfiguration
public class MovieControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testUpdateDescription() throws  Exception {
		//Given
		Long id = 1L;
		Movie movie = getMovieById(id);
		Integer oldVersion = movie.getVersion();
		String oldDescription = movie.getDescription();
		
		//When
		whenUpdateDescription(id);
		
		//Then
		movie = getMovieById(id);
		assertTrue(oldVersion < movie.getVersion());
		assertNotEquals(oldDescription, movie.getDescription());
		
	}


	private void whenUpdateDescription(Long id) {
		try {
			mockMvc.perform(patch("/movie/"+id)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(
							new DescriptionRequest("Description updated")))
					).andExpect(status().isNoContent());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	@Test
	public void testFindMoviesBySaga() throws  Exception {
		//Given
		String saga = "Saga 2";
		
		//When
		List<Movie> movies = whenRequestMoviesBySaga(saga);

		//Then
		movies.forEach(movie-> {
			if(!saga.equals(movie.getSaga())) fail("incorrectly filtered movies");  
		});
	}

	private List<Movie> whenRequestMoviesBySaga(String saga)
			throws Exception {
		MvcResult result = mockMvc.perform(get("/movies?saga="+saga))
				.andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		return objectMapper.readValue(json, new TypeReference<List<Movie>>() {});
	}

	@Test
	public void testShowMovieDetails() throws  Exception {
		//Given
		Long id = 1L;
		String details = getMovieById(id).getDetails();

		//When
		MovieDetails movieDetails = whenRequestMovieDetails(id);

		//Then
		assertEquals(details, movieDetails.getDetails());
	}

	private MovieDetails whenRequestMovieDetails(Long id)
			throws Exception {
		MvcResult result = mockMvc.perform(get("/movie/"+id+"/details")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(status().isOk()).andReturn();

		String json = result.getResponse().getContentAsString();
		return objectMapper.readValue(json, MovieDetails.class);
	}

	@Test
	public void testShowMovieInfo() throws  Exception {
        //Given
		Long id = 1L;
		
		//When
		Movie movie = getMovieById(id);
		
		//Then
		assertEquals(movie.getId(), id);
	}


	private Movie getMovieById(Long id) throws Exception {
		MvcResult result = mockMvc.perform(get("/movie/"+id)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				).andExpect(status().isOk()).andReturn();

		String json = result.getResponse().getContentAsString();
		return objectMapper.readValue(json, Movie.class);
	}
}
