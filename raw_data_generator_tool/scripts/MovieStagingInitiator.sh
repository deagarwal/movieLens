#!/bin/bash
echo "Going to initiate Movie data Staging and population to Hive."
pig -x mapreduce -useHCatalog -param CurrentDate=$(date +"%d-%m-%Y") -f hdfs://EETeamJ1/user/pig/pig-scripts/MovieDataStaging.pig
echo "Completed Movie data Staging and population to Hive."
