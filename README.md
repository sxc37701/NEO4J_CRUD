# NEO4JDBCRUD

An application illustrating crud operations of Neo4J DB

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
