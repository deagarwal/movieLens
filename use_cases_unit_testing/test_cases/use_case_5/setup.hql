DROP TABLE IF EXISTS ratings;

CREATE TABLE ratings (user_id bigint, movie_id bigint, rating float, time_stamp string)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
STORED AS TEXTFILE;

LOAD DATA LOCAL INPATH 'test_cases/use_case_5/ratings.csv' INTO TABLE ratings;
