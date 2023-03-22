package com.advDB.neoCrud.controller;

import com.advDB.neoCrud.dto.MovieDto;
import com.advDB.neoCrud.dto.MovieUpdateDto;
import com.advDB.neoCrud.entity.Movie;
import com.advDB.neoCrud.service.NeoCrudService;
import io.micrometer.common.util.StringUtils;
import java.util.Collection;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */
@Log4j2
@RestController
@RequestMapping("/imdb")
public class NeoCrudController {

  @Autowired
  private NeoCrudService neoCrudService;

  /*
  1.	Insert the new movie information.
  */
  @PostMapping()
  public ResponseEntity insertMovieAndShow(@RequestBody List<MovieDto> imdbMoviesList) {
    try{
      if(!CollectionUtils.isEmpty(imdbMoviesList)){
        log.info("Inserting IMDB movie  : "+imdbMoviesList.size());
        try{
          neoCrudService.insertMovie(imdbMoviesList);
          return new ResponseEntity("Imdb movie inserted successfully",HttpStatus.OK);
        }catch(Exception exc){
          return new ResponseEntity<>("Insertion of movie Failed ..... due to "+exc.getMessage(),HttpStatus.CONFLICT);
        }
      }else
        return new ResponseEntity<>("Empty movie data cannot be inserted...",HttpStatus.BAD_REQUEST);
    }catch(Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }

  /*
  2.	Update the movie information using title. (By update only title, description, and rating)
   */
  @PatchMapping("/{title}")
  public ResponseEntity updateByTitle(@PathVariable(required = true) String title,@RequestBody(required = true)
  MovieUpdateDto movieUpdateDto) {
    log.info("Updating id,title,description,score,rating of movies and shows by title : "+title);
    try{
      if(!ObjectUtils.isEmpty(movieUpdateDto) && StringUtils.isNotBlank(title)){
        Movie movie = neoCrudService.isMovieAvailable(title);
        if(!ObjectUtils.isEmpty(movie) && StringUtils.isNotBlank(movie.getTitle())){
        neoCrudService.updateByTitle(movieUpdateDto,title);
        return new ResponseEntity<>("IMDB details updated successfully...",HttpStatus.OK);
        }else{
          return new ResponseEntity<>("Movie doesnot exist in database to update...with title::     "+title,HttpStatus.OK);
        }
      }
      else{
        return new ResponseEntity<>("Error occurred while updating...",HttpStatus.CONFLICT);
      }
    }
    catch (Exception exc){
      return new ResponseEntity<>(exc.getMessage(),HttpStatus.CONFLICT);
    }
  }

  /*
  3.	Delete the movie information using title.
  */
  @DeleteMapping("/{title}")
  public ResponseEntity deleteMovieByTitle(@PathVariable(required = true) String title) {
    log.info("Deleting movies and shows by title : "+title);
    try{
      neoCrudService.deleteByTitle(title);
      return new ResponseEntity<>("IMDB Movie Deleted Successfully",HttpStatus.OK);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }

  /*
  4.	Retrieve all the movies in database.
  */
  @GetMapping
  public ResponseEntity getAllMovies() {
    log.info("Fetching all movies .....");
    try{
      Collection<Movie> movies = neoCrudService.fetchAllMovies();
      if(!CollectionUtils.isEmpty(movies))
        return new ResponseEntity(movies, HttpStatus.OK);
      else
        return new ResponseEntity("No Movies found in database", HttpStatus.CONFLICT);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }
  /*
   4.	5.	Display the movie’s details includes actors, directors and genres using title.
   */
  @GetMapping("/{title}")
  public ResponseEntity getMovieByTitle(@PathVariable(required = true) String title) {
    log.info("Fetching movie by title .....");
    try{
      Movie movieResponse = neoCrudService.getMovieByTitle(title);
      if(movieResponse!= null && StringUtils.isNotBlank(movieResponse.getTitle()))
      return new ResponseEntity(movieResponse, HttpStatus.OK);
      else
        return new ResponseEntity<>("No Movie Node found with this title..."+title,HttpStatus.CONFLICT);
    }catch (Exception exc){
      return new ResponseEntity(exc.getMessage(), HttpStatus.CONFLICT);
    }
  }
}
