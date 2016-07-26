Summary
=======

This dataset describes 5-star rating and free-text tagging activity from [MovieLens] (http://movielens.org), a movie recommendation service. It contains 47644977 ratings for 34308 movies. These data were created by 247853 users between January 09, 1995 and January 29, 2016. This dataset was generated on January 29, 2016.

Users were selected at random for inclusion. All selected users had rated at least 1 movies.

The data are contained in three files, `users.csv`, `movies.csv` and `ratings.csv`. More details about the contents and use of all these files follows.

NOTE: We have added only 100 records for every file in GIT for reference purpose. Actual data set can be referred from HDFS at /user/hdfs/movie_lens_data.

Usage License
=============

Neither the University of Minnesota nor any of the researchers involved can guarantee the correctness of the data, its suitability for any particular purpose, or the validity of results based on the use of the data set. The data set may be used for any research purposes under the following conditions:

* The user may not state or imply any endorsement from the University of Minnesota or the GroupLens Research Group.
* The user must acknowledge the use of the data set in publications resulting from the use of the data set (see below for citation information).
* The user may not redistribute the data without separate permission.
* The user may not use this information for any commercial or revenue-bearing purposes without first obtaining permission from a faculty member of the GroupLens Research Project at the University of Minnesota.
* The executable software scripts are provided "as is" without warranty of any kind, either expressed or implied, including, but not limited to, the implied warranties of merchantability and fitness for a particular purpose. The entire risk as to the quality and performance of them is with you. Should the program prove defective, you assume the cost of all necessary servicing, repair or correction.

In no event shall the University of Minnesota, its affiliates or employees be liable to you for any damages arising out of the use or inability to use these programs (including but not limited to loss of data or data being rendered inaccurate).

If you have any further questions or comments, please email <grouplens-info@cs.umn.edu>


Content and Use of Files
========================

Formatting and Encoding
-----------------------

The dataset files are written as [comma-separated values](http://en.wikipedia.org/wiki/Comma-separated_values) files with a single header row. These files are encoded as UTF-8. If accented characters in movie titles or tag values (e.g. Misérables, Les (1995)) display incorrectly, make sure that any program reading the data, such as a text editor, terminal, or script, is configured for UTF-8.

User Ids
--------
MovieLens users were selected at random for inclusion. Their ids have been anonymized. User ids are consistent between `ratings.csv` and `users.csv` (i.e., the same id refers to the same user across the two files).

Movie Ids
---------
Only movies with at least one rating or tag are included in the dataset. These movie ids are consistent with those used on the MovieLens web site (e.g., id `1` corresponds to the URL <https://movielens.org/movies/1>). Movie ids are consistent between `ratings.csv` and `movies.csv`.


-----------------------------------------
Ratings Data File Structure (ratings.csv)
-----------------------------------------
All ratings are contained in the file `ratings.csv`. Each line of this file after the header row represents one rating of one movie by one user, and has the following format:

    userId,movieId,rating,timestamp

The lines within this file are ordered first by userId, then, within user, by movieId. Ratings are made on a 5-star scale, with half-star increments (0.5 stars - 5.0 stars).
Timestamps represent seconds since midnight Coordinated Universal Time (UTC) of January 1, 1970.

Below is the sample records from ratings.csv
    247752,4993,0.5,1287412650
    247752,5952,0.5,1287412663
    247752,7153,0.5,1287412661
    247752,8874,4.0,1287412729
    247752,27773,2.5,1287413266
    247752,30749,4.0,1287412625


---------------------------------------
Movies Data File Structure (movies.csv)
---------------------------------------

Movie information is contained in the file `movies.csv`. Each line of this file after the header row represents one movie, and has the following format:

    movieId,title,genres

Movie titles are entered manually or imported from <https://www.themoviedb.org/>, and include the year of release in parentheses. Errors and inconsistencies may exist in these titles.

Genres are a pipe-separated list, and are selected from the following:

* Action
* Adventure
* Animation
* Children's
* Comedy
* Crime
* Documentary
* Drama
* Fantasy
* Film-Noir
* Horror
* Musical
* Mystery
* Romance
* Sci-Fi
* Thriller
* War
* Western
* (no genres listed)


Below is the sample records from movies.csv
    151657,iMurders (2008),Drama|Horror|Mystery|Thriller
    151661,Autoerotic (2011),Drama|Romance
    151663,"Semen, a Love Sample (2005)",Comedy|Romance
    151667,Romance on the Run (1938),(no genres listed)
    151669,Genetic Me (2014),(no genres listed)
    151671,The Chosen (2015),Thriller
    151673,Hustle & Heat (2003),Action|Comedy|Crime|Romance|Thriller


-------------------------------------
Users Data File Structure (users.csv)
-------------------------------------

This file contains demographic information about the users; this is a comma separated list with following format:

    userId,age,gender,occupation,zip-code

Below is the sample records from movies.csv
    247732,Test User 247732,27,F,Engineer,900673
    247733,Test User 247733,35,F,None,425922
    247734,Test User 247734,24,M,HomeMaker,885037
    247735,Test User 247735,28,F,HomeMaker,849347
    247736,Test User 247736,23,M,Writer,732639
    247737,Test User 247737,16,M,Librarian,847508
    247738,Test User 247738,16,M,Executive,585023

