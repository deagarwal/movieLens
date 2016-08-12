SELECT movie_id, title, count(*)
 FROM movies
 RIGHT OUTER JOIN ratings
 ON movies.movie_id=ratings.movie_id
 GROUP BY movies.movie_id, title;
