#!/bin/bash

BASE=`pwd`
TEST_CASE=$1
JAR_FILE=${BASE}/executables/Beetest*-jar-with-dependencies.jar

hadoop jar $JAR_FILE com.spotify.beetest.TestQueryExecutor ${TEST_CASE}
