#!/bin/bash
hdfs dfs -mv /user/hdfs/movie_lens_data/users/* /user/hdfs/movie_lens_data_bkp/users/
hdfs dfs -mv /user/hdfs/movie_lens_data/movies/* /user/hdfs/movie_lens_data_bkp/movies/
hdfs dfs -mv /user/hdfs/movie_lens_data/ratings/* /user/hdfs/movie_lens_data_bkp/ratings/
echo "Hdfs main data is moved from /user/hdfs/movie_lens_data to /user/hdfs/movie_lens_data_bkp location"
