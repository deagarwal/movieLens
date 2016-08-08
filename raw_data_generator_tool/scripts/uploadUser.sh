#!/bin/bash
hdfs dfs -put $userFile $userHdfsLocation
echo "user file $userFile is uploaded on hdfs location $userHdfsLocation"
