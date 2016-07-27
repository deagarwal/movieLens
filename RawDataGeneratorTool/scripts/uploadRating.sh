#!/bin/bash
hdfs dfs -put $ratingFile $ratingHdfsLocation
echo "Rating file $ratingFile is uploaded on hdfs location $ratingHdfsLocation"
