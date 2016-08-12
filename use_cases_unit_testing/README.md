Beetest
=======

Beetest is a simple utility for testing of Apache Hive scripts locally for non-Java developers.
Beetest has been tested on Hadoop 2.2.0 (YARN) and Hive 1.2.1.

Motivation
----------
There are multiple reasons why Apache Hive was created by Facebook. One of the reasons is that many people, who want to run distributed computation on top of Hadoop cluster, are excelent at SQL, but they do not know (or do not want to use) Java. Apache Hive achieves this goal in 99% - although it provides a powerful SQL-like language called HiveQL, there is still number of useful features that must be implement in Java e.g. UDFs, SerDes and unit tests - to name a few.

In general, a Hive user is not necessarily a Java developer. While UDFs and SerDes can be implemented by colleagues who are fluent at Java, the unit test are expected to be written by Hive users (as they know the logic of their queries the best). If a process of testing Hive queries is tedious and requires Java skills, then Hive users might have lower motivation to write them (and, in a result, test queries by running them manually on the production cluster).


### Example

run-test.sh is a basic script that runs a test and verifies the output:

        $ ./run-test.sh <path-test-case-directory> <path-to-hive-config>

We run test with following parameters:

The set of arguments needed are:
        * Directory with set of query, property and expected result files
                $ ./run-test.sh test_cases/use_case_1


### How it works

A unit test is represented as a directory that consists of several files
* `select.hql` - a query to test

The query below is the use case #1: List all the movies and the number of ratings 

        SELECT movie_id, title, count(*)
         FROM movies
         RIGHT OUTER JOIN ratings
         ON movies.movie_id=ratings.movie_id
         GROUP BY movies.movie_id, title;

* `table.ddl` - schemas of input tables

The input tables has a following schema:

        movies (movie_id BIGINT, title STRING, genres STRING)
        ratings (user_id bigint, movie_id bigint, rating float, time_stamp string)

* text files with input data.

These files should be named in the same way as tables e.g. `movies.csv` contains input records for the `movies` table

movies.csv:
        169,Free Willy 2: The Adventure Home (1995),Adventure|Children|Drama
        112552,Whiplash (2014),Drama
        1031,Bedknobs and Broomsticks (1971),Adventure|Children|Musical
        1449,Waiting for Guffman (1996),Comedy
        1732,"Big Lebowski, The (1998)",Comedy|Crime

ratings.csv:
        2,112552,5.0,1436165496
        4,1449,4.0,1037740074
        4,1732,3.0,1037992675
        17,1031,2.0,944990828
        40,1031,5.0,869171596
        129,1031,1.0,1007273202
        169,1031,0.5,1307478603
        226,1031,4.0,1180650686
        241,1031,4.0,1103133820
        256,1031,5.0,958686829
        316,1031,3.5,1063677821
        402,1031,1.0,1090269805
        407,1031,4.5,1118275167
        419,1031,3.0,1284231304
        486,1031,2.0,945553578
        499,1031,3.5,1294888655
        537,1031,3.0,841301672
        675,1031,4.0,978244385

* `expected.txt` - expected output

The expected (and right) answer is as follows:

        1031	Bedknobs and Broomsticks (1971)	15
        1449	Waiting for Guffman (1996)	1
        1732	"Big Lebowski	1
        112552	Whiplash (2014)	1

* (optional) `setup.hql` - any initialization query Beetest e.g. setting values of configuration settings

Once you run the `run-test.sh` file, BeeTest will use these files to generate and execute Hive script that, in local mode, will create necessary input tables, load input data into them, execute your query and verify if it returns expected output.

        ./run-test.sh test_cases/use_case_1

        WARNING: Use "yarn jar" to launch YARN applications.
        Aug 12, 2016 5:35:42 PM com.spotify.beetest.TestQueryExecutor run
        INFO: Generated query filename: /tmp/beetest-test-2101186839-query.hql
        Aug 12, 2016 5:35:42 PM com.spotify.beetest.TestQueryExecutor run
        INFO: Generated query content: 
        CREATE DATABASE IF NOT EXISTS beetest;
        USE beetest;
        DROP TABLE IF EXISTS movies;
        
        CREATE TABLE movies (movie_id BIGINT, title STRING, genres STRING)
        ROW FORMAT DELIMITED  FIELDS TERMINATED BY ',' 
        STORED AS TEXTFILE;
        
        LOAD DATA LOCAL INPATH 'test_cases/use_case_1/movies.csv' INTO TABLE movies;
        
        DROP TABLE IF EXISTS ratings;
        
        CREATE TABLE ratings (user_id bigint, movie_id bigint, rating float, time_stamp string)
        ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' 
        STORED AS TEXTFILE;
        
        LOAD DATA LOCAL INPATH 'test_cases/use_case_1/ratings.csv' INTO TABLE ratings;
        DROP TABLE IF EXISTS output_2101186839;
        CREATE TABLE output_2101186839
        ROW FORMAT DELIMITED 
        FIELDS TERMINATED BY '\t'
        COLLECTION ITEMS TERMINATED BY '|'
        MAP KEYS TERMINATED BY '$'
        LOCATION '/tmp/beetest-test-2101186839-output_2101186839' AS 
        SELECT movie_id, title, count(*)
         FROM movies
         RIGHT OUTER JOIN ratings
         ON movies.movie_id=ratings.movie_id
         GROUP BY movies.movie_id, title;

        ...

        Aug 12, 2016 5:36:27 PM com.spotify.beetest.Utils runCommand
        INFO: OK
        Aug 12, 2016 5:36:27 PM com.spotify.beetest.Utils runCommand
        INFO: Time taken: 32.866 seconds
        Aug 12, 2016 5:36:30 PM com.spotify.beetest.TestQueryExecutor run
        INFO: Asserting: test_cases/use_case_1/expected.txt and test_cases/use_case_1/beetest-test-2101186839-output_2101186839/000000_0
        Aug 12, 2016 5:36:30 PM com.spotify.beetest.TestQueryExecutor run
        SEVERE: Test Case is passed successfully.


At the end, no exception/error is thrown! This means that the test passes! And the test took only 12 seconds! (comparing to minutes, if we want to test it on cluster!).
You can also review the output generated by your query by opening the output file - in this case /tmp/beetest-test-310486961-output_310486961/000000_0.


Test isolation
-----

Beetest will create own database (if it already does not exists) called "beetest" and create all tables there. This have two advantages:
* we can use the same name of tables as in the production system
* if we drop (accidentally or not) a table during unit testing, a testing table will be dropped and production tables will be untouched.

Local configuration
-----
We run a test locally, because we override a couple of Hive settings:

        $ cat local-config/hive-site.xml

        <property>
                <name>beetest.dir</name>
                <value>/tmp/beetest/${user.name}</value>
        </property>
        <property>
                <name>fs.default.name</name>
                <value>file://${beetest.dir}</value>
        </property>
        <property>
                <name>hive.metastore.warehouse.dir</name>
                <value>file://${beetest.dir}/warehouse</value>
        </property>
        <property>
                <name>javax.jdo.option.ConnectionURL</name>
                <value>jdbc:derby:;databaseName=${beetest.dir}/metastore_db;create=true</value>
        </property>
        <property>
                <name>javax.jdo.option.ConnectionDriverName</name>
                <value>org.apache.derby.jdbc.EmbeddedDriver</value>
        </property>
        <property>
                <name>mapreduce.framework.name</name>
                <value>local</value>
        </property>

Adding Custom UDF's in the Hive Queries:
-----
In order to test custom UDF's in Hive in the MiniDFSCluster via HiveServer2:

        * Update hive-site.xml to provide the following:

                <property>
                  <name>hive.aux.jars.path</name>
                  <value>file:///tmp/beetest-lib</value>
                </property>

        * Make sure the folder exists on local file system
        * If the above property is set, MiniDFSCluster will copy over the jars into hdfs and use it for testing UDF's

License
-----
Beetest is released under the Apache License Version 2.0.
