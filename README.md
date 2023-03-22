# NEO4JDBCRUD

An application illustrating crud operations of Neo4J DB

Queries Used
================
For Insertion
 @Query("MERGE (movie:Movie {Ids:$Id, Title: $title,Description:$desc,Year:$year,"
      +"`Runtime (Minutes)`:$runTime,Votes:$votes,Rating:$rating,`Revenue (Millions)`:$revenue})"
      +" MERGE(person:Person {Actors : $actors})"
      +" MERGE(person_d:Person {Director : $director})"
      + "MERGE(genre:Genre{Genre:$genre}) "
      + "MERGE (person_d)-[:DIRECTED]->(movie)"
      + "MERGE (person)-[:ACTED_IN]->(movie)"
      + "MERGE(movie)-[:IN] ->(genre)"
      + "RETURN movie.Title")
      
 For Finding movie by title
  @Query("MATCH(movie:Movie) <-[ACTED_IN]-(person:Person),(genre:Genre)<-[IN]-(movie:Movie)  WHERE movie.Title = $title and person.Actors IS NOT NULL "
      +"MATCH(movie_d:Movie) <-[DIRECTED]-(person_d:Person)  WHERE movie_d.Title = $title and person_d.Director IS NOT NULL "
      +"RETURN person_d.Director as director , person.Actors as actors ,movie.Description as description ,"
      + "movie.Ids as ids,movie.Title as title , genre.Genre as genre,movie.Rating as rating ,"
      + "movie.Votes as votes , movie.Year as year ,movie.`Revenue (Millions)` as revenue ,movie.`Runtime (Minutes)` as runTime ")
      
  For Deleting movie node
  @Query("MATCH (movie:Movie) WHERE movie.Title=$title   DETACH DELETE movie")
  
  For updating movie node
    @Query("MATCH (movie:Movie) WHERE movie.Title=$title   set movie.Description = $desc,"
         +"movie.Rating=$rating,movie.`Revenue (Millions)` = $revenue,"
         +"movie.`Runtime (Minutes)` = $runTime,movie.Votes=$votes,movie.Year=$year")

## How to run the application

1)Clone this code in to your local folder
git clone https://github.com/sxc37701/NEO4J_CRUD.git

2)Open Git bash at NEO4J_CRUD which points to main branch
Execute mvn clean install Now Navigate to NEO4J_CRUD\target

3)Run the below command
java -jar neoCrud-0.0.1-SNAPSHOT.jar

4)Application starts running at port :9095

## Rest End points

##

3.Display the movie and show’s detail using title.
GET : http://localhost:9095/imdb/{title}

4. Retrieve all the movies and shows in database.
   GET :http://localhost:9095/imdb

5. Insert the new movie and show.--multiple data documents allowed
   POST:http://localhost:9095/imdb

   Sample data:

   ***

[{
"director": "Stephen Gaghan112",
"actors": "Matthew McConaughey112,Edgar RamÃ­rez, Bryce Dallas Howard, Corey Stoll",
"description": "Test Desc",
"title": 6908,
"genre": "Adventure,Drama,Thriller112",
"rating": 100.0,
"votes": 19058,
"year": 2026,
"revenue": 7.28,
"runTime": 208
}
]

1. Update the movie information using title. (Update the movie information using title. (By update only title, description, and rating))
   PATCH:http://localhost:9095/imdb/{title}
   Sample Data:

   ***

   {
   "description": "Test Desc009",
   "rating": 150.0,
   "votes": 19059,
   "year": 2030,
   "revenue": 7.009,
   "runTime": 210
   }

2. Delete the movie and show information using title.
   DELETE:http://localhost:9095/imdb/{title}
