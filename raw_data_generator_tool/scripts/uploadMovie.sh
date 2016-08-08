#!/bin/bash
hdfs dfs -put $movieFile $movieHdfsLocation
echo "movie file $movieFile is uploaded on hdfs location $movieHdfsLocation"
