package com.advDB.neoCrud.service;

import com.advDB.neoCrud.dto.MovieDto;
import com.advDB.neoCrud.dto.MovieUpdateDto;
import com.advDB.neoCrud.entity.Movie;
import com.advDB.neoCrud.repository.MovieRepository;
import java.util.Collection;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */
@Service
@Log4j2
public class NeoCrudService {

  @Autowired
  private MovieRepository movieRepository;

  public Collection<Movie> fetchAllMovies(){
    return movieRepository.getAllMovies();
  }

  public Movie getMovieByTitle(String title){
    return movieRepository.findByTitle(title);
  }
  public  List<Movie> insertMovie(List<MovieDto> moviesList){
    moviesList.forEach(imdbMovieDto -> {
      Long nextId = movieRepository.getMaxId()+1;
      movieRepository.insertMovie(nextId,imdbMovieDto.getTitle(),imdbMovieDto.getDescription(),
          imdbMovieDto.getYear(),imdbMovieDto.getRunTime(),imdbMovieDto.getVotes(),imdbMovieDto.getRating(),imdbMovieDto.getRevenue(),
          imdbMovieDto.getActors(),imdbMovieDto.getDirector(),imdbMovieDto.getGenre());
    });

    return null;
  }
  public void deleteByTitle(String title){
     movieRepository.deleteByTitle(title);
  }
  public void updateByTitle(MovieUpdateDto movieUpdateDto,String title){
    movieRepository.updateMovieDetails(title,movieUpdateDto.getDescription(),
        movieUpdateDto.getYear(),movieUpdateDto.getRunTime(),movieUpdateDto.getVotes(),movieUpdateDto.getRating(),movieUpdateDto.getRevenue());
  }

  public Movie isMovieAvailable(String title){
   return movieRepository.isMovieAvailable(title);
  }

}
