package com.advDB.neoCrud.repository;

import com.advDB.neoCrud.entity.Movie;
import java.util.Collection;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;


/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */
public interface MovieRepository  extends Neo4jRepository<Movie, Long> {

  @Query("MATCH(movie:Movie) <-[ACTED_IN]-(person:Person),(genre:Genre)<-[IN]-(movie:Movie)  "
      +"RETURN person.Director as director , person.Actors as actors ,movie.Description as description ,"
      + "movie.Ids as ids,movie.Title as title , genre.Genre as genre,movie.Rating as rating ,"
      + "movie.Votes as votes , movie.Year as year ,movie.`Revenue (Millions)` as revenue ,movie.`Runtime (Minutes)` as runTime ")
  Collection<Movie> getAllMovies();

  @Query("MATCH(movie:Movie) <-[ACTED_IN]-(person:Person),(genre:Genre)<-[IN]-(movie:Movie)  WHERE movie.Title = $title and person.Actors IS NOT NULL "
      +"MATCH(movie_d:Movie) <-[DIRECTED]-(person_d:Person)  WHERE movie_d.Title = $title and person_d.Director IS NOT NULL "
      +"RETURN person_d.Director as director , person.Actors as actors ,movie.Description as description ,"
      + "movie.Ids as ids,movie.Title as title , genre.Genre as genre,movie.Rating as rating ,"
      + "movie.Votes as votes , movie.Year as year ,movie.`Revenue (Millions)` as revenue ,movie.`Runtime (Minutes)` as runTime ")
  Movie findByTitle(String title);

  @Query("MATCH (movie:Movie) WHERE movie.Title=$title   DETACH DELETE movie")
   void  deleteByTitle(String title);

  @Query("MATCH (movie:Movie) WHERE movie.Title=$title   set movie.Description = $desc,"
         +"movie.Rating=$rating,movie.`Revenue (Millions)` = $revenue,"
         +"movie.`Runtime (Minutes)` = $runTime,movie.Votes=$votes,movie.Year=$year")
  public void updateMovieDetails(String title,String desc,Long year,Long runTime,
      Long votes,Long rating,Long revenue);

  @Query("MERGE (movie:Movie {Ids:$Id, Title: $title,Description:$desc,Year:$year,"
      +"`Runtime (Minutes)`:$runTime,Votes:$votes,Rating:$rating,`Revenue (Millions)`:$revenue})"
      +" MERGE(person:Person {Actors : $actors})"
      +" MERGE(person_d:Person {Director : $director})"
      + "MERGE(genre:Genre{Genre:$genre}) "
      + "MERGE (person_d)-[:DIRECTED]->(movie)"
      + "MERGE (person)-[:ACTED_IN]->(movie)"
      + "MERGE(movie)-[:IN] ->(genre)"
      + "RETURN movie.Title")
     public void insertMovie(Long Id,String title,String desc,Long year,Long runTime,
      Long votes,Long rating,Long revenue,
      String actors,String director,String genre);

  @Query("Match (movie:Movie) return max(movie.Ids)")
  public Long getMaxId();

  @Query("Match (movie:Movie) where movie.Title=$title return movie.Title as title")
  public Movie isMovieAvailable(String title);

}
